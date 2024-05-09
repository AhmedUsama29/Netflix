package com.mycompany.netflix;

import java.util.ArrayList;
import java.util.Scanner;

public class Payment {
    static Scanner sc = new Scanner(System.in);
    private String PaymentName;
    private double PaymentAmount;
    private String CardNumber;
    private String CardExpiry;
    private int CardCV;
    private String PayPalEmail;
    private String PayPalPassword;

    ArrayList<Payment> PaymentList = new ArrayList<>();

    public Payment(String paymentName, double paymentAmount, String cardNumber, String cardExpiry, int cardCV) {
        PaymentName = paymentName;
        PaymentAmount = paymentAmount;
        CardNumber = cardNumber;
        CardExpiry = cardExpiry;
        CardCV = cardCV;
    }

    public Payment() {
    }


    public String getPaymentName() {
        return PaymentName;
    }

    public void setPaymentName(String paymentName) {
        PaymentName = paymentName;
    }

    public double getPaymentAmount() {
        return PaymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        PaymentAmount = paymentAmount;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getCardExpiry() {
        return CardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        CardExpiry = cardExpiry;
    }

    public int getCardCV() {
        return CardCV;
    }

    public void setCardCV(int cardCV) {
        CardCV = cardCV;
    }

    public String getPayPalEmail() {
        return PayPalEmail;
    }

    public void setPayPalEmail(String payPalEmail) {
        PayPalEmail = payPalEmail;
    }

    public String getPayPalPassword() {
        return PayPalPassword;
    }

    public void setPayPalPassword(String payPalPassword) {
        PayPalPassword = payPalPassword;
    }

    public void AddCard(){
        setPaymentName("Credit/Debit Card");
        System.out.println("Enter Your Card Number:");
        this.CardNumber = sc.next();
        System.out.println("Enter Your Card Expire date (mm/yy):");
        this.CardExpiry = sc.next();
        System.out.println("Enter Your Card CV:");
        this.CardCV = sc.nextInt();
//        Payment p1 = new Payment("Credit/Debit Card",amount,this.CardNumber,this.getCardExpiry(),this.getCardCV());
//        PaymentList.add(p1);
        // continue later
    }

    public void AddPayPal(){
        setPaymentName("PayPal");
        System.out.println("Enter Your PayPal Email:");
        this.PayPalEmail = sc.next();
        System.out.println("Enter Your PayPal Password:");
        this.PayPalPassword = sc.next();
    }

}
