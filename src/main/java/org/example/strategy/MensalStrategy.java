package org.example.strategy;

// Classe que implementa a interface PricingStrategy, usada para calcular o preço de uma reserva mensal
public class MensalStrategy implements PricingStrategy{

    // Implementa o método calcularPreco para calcular o preço com base em um período mensal
    @Override
    public double calcularPreco(double diaria, int quantidade){
        // Calcula o preço mensal, multiplicando o valor da diária por 30 dias e aplicando um desconto de 15% (0.85).
        // Multiplica pelo número de meses (quantidade) da reserva
        return ((diaria * 30) * 0.85) * quantidade;
    }
}
