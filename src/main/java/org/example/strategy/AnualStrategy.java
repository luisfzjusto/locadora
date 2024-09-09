package org.example.strategy;

// Classe que implementa a interface PricingStrategy, usada para calcular o preço de uma reserva anual
public class AnualStrategy implements PricingStrategy{

    // Implementa o método calcularPreco para calcular o preço com base em um período anual
    @Override
    public double calcularPreco(double diaria, int quantidade){
        // Calcula o preço anual, multiplicando o valor da diária por 365 dias e aplicando um desconto de 25% (0.75)
        // Multiplica pelo número de anos (quantidade) da reserva
        return ((diaria * 365) * 0.75) * quantidade;
    }
}
