/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymenttracker;

import paymenttracker.exceptions.WrongInputException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Bebtu
 */
public class PaymentValidator {

    public static final int LOADING_INPUT = 0;
    public static final int USER_INPUT = 1;
    
    public static Payment getValidPayment(String[] payment) throws WrongInputException{
        return getValidPayment(payment,null);
    }
    
    public static Payment getValidPayment(String[] payment, Integer lineNumber) throws WrongInputException {
        if (payment.length != 2) {
            throw new WrongInputException(WrongInputException.WRONG_PARAMETERS_NUMBER,null,lineNumber);
        } else if (!isCurrencyValid(payment[0])) {
            throw new WrongInputException(WrongInputException.CURRENCY_NOT_CONVERTIBLE,payment[0],lineNumber);
        } else if (!isAmountValid(payment[1])) {
            throw new WrongInputException(WrongInputException.AMOUNT_NOT_C0NVERTIBLE,payment[1],lineNumber);
        }

        String currency = payment[0];
        double amount = Double.parseDouble(payment[1]);

        return new Payment(currency, amount);
    }


    private static boolean isCurrencyValid(String currency) {
        Pattern pattern = Pattern.compile("[A-Z][A-Z][A-Z]");
        Matcher matcher = pattern.matcher(currency);
        return matcher.matches();
    }

    private static boolean isAmountValid(String amount) {
        try {
            Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
