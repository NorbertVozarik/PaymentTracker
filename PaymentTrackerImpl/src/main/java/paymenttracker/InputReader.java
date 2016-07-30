/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymenttracker;

import paymenttracker.exceptions.WrongInputException;
import paymenttracker.exceptions.UserInputException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bebtu
 */
public class InputReader implements Runnable{

    private final String quitCommand = "quit";
    private final PaymentTracker paymentTracker;
    InputReader(PaymentTracker paymentTracker) {
      this.paymentTracker = paymentTracker;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
            System.in));
        while(true){
            try {
                String line = reader.readLine();
                if(line.trim().equals(quitCommand)){
                    paymentTracker.shutDownAllThreads();
                    return;
                }
                String[] payment = line.split("\t");
                Payment validPayment = PaymentValidator.getValidPayment(payment);
                paymentTracker.addPayments(validPayment);
            } catch (IOException ex) {
                Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WrongInputException ex) {
                //Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
              //  System.err.println((new UserInputException(ex)).getMessage());
                System.out.println((new UserInputException(ex)).getMessage());
            }
        }
    }
    
}
