package org.example.model;

import org.example.strategy.PricingStrategy;

public class Reserva {

    // Essa classe representa um cliente com os atributos abaixo, sendo que o id é gerado pelo BD e
    // pricingStrategy é um objeto criado a partir do Design Pattern Strategy, onde definimos uma Interface para cálculo do preço das reservas

    // Os métodos get e set acessam e modificam os valores dos atributos privados

    private int id; // identificador único da reserva
    private Cliente cliente; // Atributos do cliente, que estão encapsuladas em um objeto da classe Cliente
    private Veiculo veiculo; // Atributos do veículo, que estão encapsulados em um objeto da classe Veiculo
    private String tipoReserva; // se diária, mensal ou anual
    private int quantidade; // quantidade de dias, meses ou anos da reserva
    private PricingStrategy pricingStrategy; // Política de precificação, encapsulada em um objeto da classe PricingStrategy, conforme o padrão de projeto "STRATEGY"

    // Método construtor que define a estratégia de precificação
    public Reserva(PricingStrategy pricingStrategy){
        this.pricingStrategy = pricingStrategy;
    }

    // Método que calcula o valor de uma reserva com base na estratégia de precificação definida
    public double calcularValorTotal(){
        return pricingStrategy.calcularPreco(veiculo.getDiaria(), quantidade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


}
