/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymenttracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Bebtu
 */
public class ListPaymentSum implements Runnable {

    PaymentTracker paymentTracker;
    ConvertHelper convertHelper;

    ListPaymentSum() {
    }

    ListPaymentSum(PaymentTracker paymentTracker) {
        this.paymentTracker = paymentTracker;
        this.convertHelper = new ConvertHelper(paymentTracker.getCurrencyTo());
    }

    @Override
    public void run() {
        List<Payment> payments = paymentTracker.getMirrorPaymentList();
        Map<String, Double> currencySum = new HashMap<>();
        for (Payment payment : payments) {
            String currency = payment.getCurrency();
            double sum = payment.getAmount();
            if (currencySum.containsKey(currency)) {
                sum += currencySum.get(currency);
                currencySum.remove(currency);
            }
            currencySum.put(currency, sum);
        }

        for (Entry entry : currencySum.entrySet()) {
            double sum = (double) entry.getValue();
            String currency = (String) entry.getKey();
            try {
                double convertedSum = convertHelper.convertCurrencies(currency, sum);
                if (sum != 0) {
                    String write = currency + "\t" + sum;
                    if (!currency.equals(convertHelper.getCurrencyTo())) {
                        write += " (" + convertHelper.getCurrencyTo() + " " + convertedSum + ")";
                    }
                    System.out.println(write);
                }
            } catch (NumberFormatException e) {
                System.out.println("Currency is not valid: " + currency);
            }

        }
    }

}
