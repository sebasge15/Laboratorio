package com.mycompany.laboratorio_02;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InvestmentPortfolio {
    
    private final String userId;
    private final List<Transaction> transactions;
    private final Map<String, Double> fundBalances;
    
    public InvestmentPortfolio(String userId) {
        this.userId = userId;
        this.transactions = new ArrayList<>();
        this.fundBalances = new HashMap<>();
    }
    
    public void processBuyTransaction(String fundCode, double amount) {
        processTransaction(fundCode, amount, "BUY", 
            currentBalance -> currentBalance + amount,
            "Se ha realizado una compra por $%s en el fondo %s",
            "Transacción de compra procesada exitosamente. ID: %s");
    }
    
    public void processSellTransaction(String fundCode, double amount) {
        processTransaction(fundCode, amount, "SELL", 
            currentBalance -> {
                if (currentBalance < amount) {
                    throw new IllegalArgumentException("Saldo insuficiente. Balance actual: " + currentBalance);
                }
                return currentBalance - amount;
            },
            "Se ha realizado un rescate por $%s del fondo %s",
            "Transacción de venta procesada exitosamente. ID: %s");
    }
    
    private void processTransaction(String fundCode, double amount, String transactionType,
                                  BalanceUpdater balanceUpdater, String notificationMessage,
                                  String successMessage) {
        validateTransactionInput(fundCode, amount);
        
        String transactionId = createAndRegisterTransaction(fundCode, amount, transactionType);
        
        updateFundBalance(fundCode, amount, balanceUpdater);
        
        sendNotification(String.format(notificationMessage, amount, fundCode));
        
        System.out.println(String.format(successMessage, transactionId));
    }
    
    private void validateTransactionInput(String fundCode, double amount) {
        if (fundCode == null || fundCode.isEmpty()) {
            throw new IllegalArgumentException("El código del fondo no puede estar vacío");
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        
        if (!isFundValid(fundCode)) {
            throw new IllegalArgumentException("El fondo no existe: " + fundCode);
        }
    }
    
    private String createAndRegisterTransaction(String fundCode, double amount, String transactionType) {
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(
                transactionId, 
                userId, 
                fundCode, 
                transactionType, 
                amount, 
                new Date());
        
        transactions.add(transaction);
        saveTransactionToDatabase(transaction);
        return transactionId;
    }
    
    private void updateFundBalance(String fundCode, double amount, BalanceUpdater balanceUpdater) {
        double currentBalance = fundBalances.getOrDefault(fundCode, 0.0);
        fundBalances.put(fundCode, balanceUpdater.update(currentBalance));
    }
    
    private void sendNotification(String message) {
        System.out.println("Notificación para usuario " + userId + ": " + message);
    }
    
    private boolean isFundValid(String fundCode) {
        return fundCode.startsWith("FUND");
    }
    
    private void saveTransactionToDatabase(Transaction transaction) {
        System.out.println("Guardando transacción en la base de datos: " + transaction.getId());
    }
    
    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public Map<String, Double> getFundBalances() {
        return fundBalances;
    }
    
    @FunctionalInterface
    private interface BalanceUpdater {
        double update(double currentBalance);
    }
}
