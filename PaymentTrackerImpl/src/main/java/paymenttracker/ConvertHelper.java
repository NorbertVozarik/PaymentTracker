/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymenttracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Bebtu
 */
public class ConvertHelper {

    public static Map<String, Double> currencyExchangeRate;
    private final String currencyTo;
    private final Map<String, Double> convertRateMap = new HashMap<>();

    public ConvertHelper(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public double convertCurrencies(String currencyFrom, double amount)throws NumberFormatException {
        return getCurrencyConvertRate(currencyFrom) * amount;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    private double getCurrencyConvertRate(String currencyFrom)throws  NumberFormatException{

        Double convertRate = convertRateMap.get(currencyFrom);
        if (convertRate != null) {
            return convertRate;
        }

        try {
            URL url = new URL("http://quote.yahoo.com/d/quotes.csv?f=l1&s=" + currencyFrom + currencyTo + "=X");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line.length() > 0) {
                convertRate = Double.parseDouble(line);
            }
            reader.close();
            convertRateMap.put(currencyFrom, convertRate);
            return convertRate;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return 0;
    }
}
