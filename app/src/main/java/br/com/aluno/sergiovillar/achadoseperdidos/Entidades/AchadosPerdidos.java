package br.com.aluno.sergiovillar.achadoseperdidos.Entidades;

public class AchadosPerdidos {
    String nome, descricao,  endereco, emailcontato, telefonecontato, tipo;

    public AchadosPerdidos() {
    }

    public AchadosPerdidos(String nome, String descricao, String endereco, String emailcontato, String telefonecontato, String tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.emailcontato = emailcontato;
        this.telefonecontato = telefonecontato;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmailcontato() {
        return emailcontato;
    }

    public void setEmailcontato(String emailcontato) {
        this.emailcontato = emailcontato;
    }

    public String getTelefonecontato() {
        return telefonecontato;
    }

    public void setTelefonecontato(String telefonecontato) {
        this.telefonecontato = telefonecontato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
