package org.example.model;

public class Veiculo {

    // Essa classe representa um veículo com os atributos abaixo, sendo que o id é gerado pelo BD.

    // Os métodos get e set acessam e modificam os valores dos atributos privados

    private int id; // identificador único do veículo
    private String marca; // Marca do veículo
    private String modelo; // Modelo do veículo
    private int ano; // ano de fabricação do veículo
    private String placa; // placa do veículo
    private double diaria; // valor da diária do veículo
    private String categoria; // categoria do veículo (POPULAR, SUPERIOR, UTILITARIO OU PREMIUM)
    private String status; // status do veículo (disponível ou alugado)

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
