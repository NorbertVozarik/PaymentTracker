

import test.*;
import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import paymenttracker.ConvertHelper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bebtu
 */
public class TestConvert {

    @Test
    public void convertTest() {
        String currencyTo = "USD";
        String currencyFrom = "EUR";
        double amount = 100;
        ConvertHelper converter = new ConvertHelper(currencyTo);
        double convertedResult = 0;
        try {
            convertedResult = converter.convertCurrencies(currencyFrom, amount);
        } catch (NumberFormatException e) {
            
        }
        Assert.assertTrue(convertedResult != 0);
    }
    
     @Test
    public void convertWrongInputTest() {
        String currencyTo = "USD";
        String currencyFrom = "AAA";
        double amount = 100;
        ConvertHelper converter = new ConvertHelper(currencyTo);
        double convertedResult = 0;
        try {
            convertedResult = converter.convertCurrencies(currencyFrom, amount);
        } catch (NumberFormatException e) {
            
        }
        Assert.assertTrue(convertedResult == 0);
    }
}
