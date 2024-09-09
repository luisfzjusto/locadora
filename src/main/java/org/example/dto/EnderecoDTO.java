package org.example.dto;

//Essa classe é um DTO, utilizado para transportar dados entre diferentes camadas do sistema.
// Ela não contém lógica de negócio, mas apenas métodos para acessar e manipular os dados
public class EnderecoDTO {

    // Atributos privados da classe que armazenam as informações de um endereço
    private String logradouro; // armazena logradouro
    private String numero; // armazena numero
    private String bairro; // armazena bairro
    private String cidade; // armazena cidade
    private String uf; // armazena uf
    private String cep; // armazena cep

    // Métodos GET e SET que acessam e modificam os valores dos atributos privados
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
