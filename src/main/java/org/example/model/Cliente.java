package org.example.model;

import java.time.LocalDate;

public class Cliente {

    // Essa classe representa um cliente com os atributos abaixo, sendo que endereco é um objeto e que o id é gerado pelo BD.

    private int id; // identificador único do cliente
    private String nome; // Nome do cliente
    private String cpf; // CPF do cliente
    private LocalDate dataNascimento; // Data de nascimento do cliente
    private String telefone; // Telefone do cliente
    private Endereco endereco; // Endereco do cliente encapsulado em um objeto

    // getters e setters que acessam e modificam os valores dos atributos privados
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
