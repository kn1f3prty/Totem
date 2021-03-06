package br.com.totem.totem.model;

/**
 * Created by Renato Nascimento on 11/15/2017.
 */

public class Totem {
    private Long id;
    private String nome;
    private String produto;
    private String colecao;

    public Totem(Long id, String nome, String produto, String colecao) {
        this.id = id;
        this.nome = nome;
        this.produto = produto;
        this.colecao = colecao;
    }

    public Totem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getColecao() {
        return colecao;
    }

    public void setColecao(String colecao) {
        this.colecao = colecao;
    }

    @Override
    public String toString() {
        return id +" - "+ nome;
    }
}
