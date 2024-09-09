package org.example.strategy;

// Classe que implementa a interface PricingStrategy, usada para calcular o preço de uma reserva diária
public class DiariaStrategy implements PricingStrategy{

    // Implementa o método calcularPreco para calcular o preço com base em um período diário
    @Override
    public double calcularPreco(double diaria, int quantidade){
        // Calcula o preço total multiplicando a diária pelo número de dias (quantidade)
        return diaria * quantidade;
    }
}
