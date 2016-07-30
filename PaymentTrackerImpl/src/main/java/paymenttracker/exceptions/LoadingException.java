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
public class LoadingException extends Exception {
    public static String LOADING_EXCEPTION = "Loading exception. ";
    
    private final Exception subException;
    public LoadingException(Exception subException){
        this.subException= subException;
    }
    
     @Override
    public String getMessage() {
        return LOADING_EXCEPTION+subException.getMessage();
    }
    
}
