/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymenttracker;

import paymenttracker.exceptions.WrongInputException;
import paymenttracker.exceptions.LoadingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bebtu
 */
public class PaymentLoader {

    public static List<Payment> loadPayments(File file)throws FileNotFoundException{
       List<Payment> payments = null; 
        try {
            payments = new ArrayList<>();
            String line;
            Scanner fileScanner = new Scanner(file);
            int lineNumber = 1;
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                String[] payment = line.split("\t");
                Payment validPayment = PaymentValidator.getValidPayment(payment,lineNumber);
                payments.add(validPayment);
                lineNumber++;
            }
            return payments;
        } 
        catch (WrongInputException ex) {
             //Logger.getLogger(PaymentTracker.class.getName()).log(Level.SEVERE, null, ex);
             //System.err.println(ex.getMessage());
             payments = new ArrayList<>();
             System.out.println((new LoadingException(ex)).getMessage());
        }finally{
            return payments;
        }
      
    }
}
