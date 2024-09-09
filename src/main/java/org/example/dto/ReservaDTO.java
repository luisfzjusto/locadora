package org.example.dto;

import org.example.dto.ClienteDTO;
import org.example.dto.VeiculoDTO;

//Essa classe é um DTO, utilizado para transportar dados entre diferentes camadas do sistema.
// Ela não contém lógica de negócio, mas apenas métodos para acessar e manipular os dados
// No momento, ela não está sendo usada pois não foi implementada nenhuma funcionalidade que a utilize.
public class ReservaDTO {

    // Atributos privados que representam uma reserva
    private ClienteDTO clienteDTO; // cliente que fez a reserva (encapsulado em um clienteDTO)
    private VeiculoDTO veiculoDTO; // veiculo reservado (encapsulado em um veiculoDTO)
    private String tipoReserva; // tipo de reserva (diaria, mensal, anual)
    private int quantidade; // quantidade de dias, meses ou anos
    private double valorTotal; // valor total

    // Métodos GET e SET que acessam e modificam os valores dos atributos privados
    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public VeiculoDTO getVeiculoDTO() {
        return veiculoDTO;
    }

    public void setVeiculoDTO(VeiculoDTO veiculoDTO) {
        this.veiculoDTO = veiculoDTO;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}

