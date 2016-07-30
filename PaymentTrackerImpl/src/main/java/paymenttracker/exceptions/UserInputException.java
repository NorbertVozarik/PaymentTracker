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
public class UserInputException extends Exception {
    public static String USER_INPUT_EXCEPTION = "Wrong user exception. ";
    private final Exception subException;
    public UserInputException(Exception subException){
        this.subException= subException;
    }
    
     @Override
    public String getMessage() {
        return USER_INPUT_EXCEPTION+subException.getMessage();
    } 
    
}
