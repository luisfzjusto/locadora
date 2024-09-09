package org.example.dto;

import java.time.LocalDate;


//Essa classe é um DTO, utilizado para transportar dados entre diferentes camadas do sistema.
// Ela não contém lógica de negócio, mas apenas métodos para acessar e manipular os dados
public class ClienteDTO {

    // Atributos privados da classe, que armazenam informações do cliente
    private String nome; // armazena nome
    private String cpf; // armazena cpf
    private LocalDate dataNascimento; // armazena D.N.
    private String telefone; // armazena telefone
    private EnderecoDTO enderecoDTO; // armazena endereço, que está encapsulado em um objeto do tipo EndereçoDTO


    // Métodos GET e SET que acessam e modificam os valores dos atributos privados
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

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }
}
