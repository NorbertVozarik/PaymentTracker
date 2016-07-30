/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymenttracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Bebtu
 */
public class PaymentTracker {

    private final List<Payment> payments;
    private final List<ExecutorService> executors;
    private final int countInputReaders = 1;
    private final int countListPaymentsSum = 1;
    private final String currencyTo = "USD";
    
    
    public PaymentTracker() {
        this.payments = new ArrayList<>();
        this.executors = new ArrayList<>();
        init();
    }

    private void init(){
        File file;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                file = fileChooser.getSelectedFile();
                List<Payment> loadedPayments;
                loadedPayments = PaymentLoader.loadPayments(file);
                addPayments(loadedPayments);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaymentTracker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(countInputReaders);
        for(int i=0;i<countInputReaders;i++){
        executors.add(executor);
        Runnable inputReader = new InputReader(this);
        executor.execute(inputReader);
        }
     
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(countListPaymentsSum);
        for(int i=0;i<countListPaymentsSum;i++){
        executors.add(scheduler);
        Runnable listPayment = new ListPaymentSum(this);
        scheduler.scheduleWithFixedDelay(listPayment, 0, 1, TimeUnit.MINUTES);
        }
    }
    
    public void shutDownAllThreads(){
        for(ExecutorService executor: executors){
            executor.shutdown();
        }
    }

    public void addPayments(List<Payment> payments) {
        synchronized(this.payments){
            this.payments.addAll(payments);
        }
    }
    public void addPayments(Payment payment){
        synchronized(this.payments){
            this.payments.add(payment);
        }
    }

    public List<Payment> getMirrorPaymentList() {
        synchronized(this.payments){
            return new ArrayList<>(this.payments);
        }
    }

    public String getCurrencyTo(){
        return currencyTo;
    }
    
    public static void main(String[] args) {
        PaymentTracker paymentTacker = new PaymentTracker();
    }

}
