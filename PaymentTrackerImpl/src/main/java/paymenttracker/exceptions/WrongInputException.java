/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymenttracker.exceptions;

/**
 *
 * @author Bebtu
 */
public class WrongInputException extends Exception {

    public static final int WRONG_PARAMETERS_NUMBER = 0;
    public static final int CURRENCY_NOT_CONVERTIBLE = 1;
    public static final int AMOUNT_NOT_C0NVERTIBLE = 2;
    private static final String WRONG_PARAMETERS_NUMBER_TEXT = "Input contains wrong number of parameters.";
    private static final String CURRENCY_NOT_CONVERTIBLE_TEXT = "Currency is not convertible for input: ";
    private static final String AMOUNT_NOT_C0NVERTIBLE_TEXT = "Amount is not convertible for input: ";

    private final int exceptionCode;
    private final String input;
    private final Integer lineNumber;
    public WrongInputException(int exceptionCode,String input, Integer lineNumber) {
        this.exceptionCode = exceptionCode;
        this.input = input;
        this.lineNumber = lineNumber;
    }

    @Override
    public String getMessage() {
        String message = null;
        switch (exceptionCode) {
            case WrongInputException.WRONG_PARAMETERS_NUMBER:
                message = WRONG_PARAMETERS_NUMBER_TEXT;
                if(lineNumber != null){
                    message+= "Error on line: "+lineNumber;
                }
                break;
            case WrongInputException.AMOUNT_NOT_C0NVERTIBLE:
                message = AMOUNT_NOT_C0NVERTIBLE_TEXT+input;
                  if(lineNumber != null){
                    message+= "Error on line: "+lineNumber;
                }
                break;
            case WrongInputException.CURRENCY_NOT_CONVERTIBLE:
                message = CURRENCY_NOT_CONVERTIBLE_TEXT+input;
                  if(lineNumber != null){
                    message+= "Error on line: "+lineNumber;
                }
                break;
        }
        return message;
    }
}
