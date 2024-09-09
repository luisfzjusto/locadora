package org.example.dto;

//Essa classe é um DTO, utilizado para transportar dados entre diferentes camadas do sistema.
// Ela não contém lógica de negócio, mas apenas métodos para acessar e manipular os dados
public class VeiculoDTO {

    // Atributos privados da classe, que armazenam informações do veículo
    private String marca; // armazena marca
    private String modelo; // armazena model
    private int ano; // armazena ano
    private String placa; // armazena placa
    private double diaria; // armazena diaria
    private String categoria; // armazena categoria
    private String status; // armazena status

    // Métodos GET e SET que acessam e modificam os valores dos atributos privados
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        this.diaria = diaria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
