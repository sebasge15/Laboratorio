/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.laboratorio_02;
import java.util.Map;

/**
 * Ejercicio 1: Principio DRY (Don't Repeat Yourself)
 * 
 * Este código presenta múltiples violaciones al principio DRY con lógica duplicada
 * en el manejo de transacciones BUY y SELL de fondos mutuos.
 * 
 * Instrucciones:
 * 1. Identifica y señala todas las duplicaciones de código
 * 2. Refactoriza aplicando el principio DRY
 * 3. Mantén la funcionalidad original
 */
public class Laboratorio_02 {

    public static void main(String[] args) {
 InvestmentPortfolio portfolio = new InvestmentPortfolio("123456");
        portfolio.processBuyTransaction("FUND001", 1000.0);
        portfolio.processSellTransaction("FUND001", 500.0);
        
        System.out.println("Transacciones en el portafolio:");
        for (Transaction transaction : portfolio.getTransactions()) {
            System.out.println(transaction);
        }
        
        System.out.println("\nResumen del portafolio:");
        for (Map.Entry<String, Double> entry : portfolio.getFundBalances().entrySet()) {
            System.out.println("Fondo: " + entry.getKey() + ", Balance: " + entry.getValue());
        }    
    
    }
}
