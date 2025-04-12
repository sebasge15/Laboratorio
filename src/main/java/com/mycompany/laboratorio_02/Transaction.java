/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laboratorio_02;

import java.util.Date;


public class Transaction {
    private String id;
    private String userId;
    private String fundCode;
    private String type; // "BUY" o "SELL"
    private double amount;
    private Date date;
    
    public Transaction(String id, String userId, String fundCode, String type, double amount, Date date) {
        this.id = id;
        this.userId = userId;
        this.fundCode = fundCode;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getFundCode() {
        return fundCode;
    }
    
    public String getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public Date getDate() {
        return date;
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", fundCode='" + fundCode + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
