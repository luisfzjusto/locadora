package org.example.strategy;

// Interface que define a estratégia de cálculo de preços para aluguel de veículos
public interface PricingStrategy {

    // Método que precisa ser implementado pelas classes que implementam essa interface.
    // Aceita dois parâmetros: "diária" (preço por dia do veículo) e "quantidade" (número de dias, meses ou anos)
    double calcularPreco(double diaria, int quantidade);
}
