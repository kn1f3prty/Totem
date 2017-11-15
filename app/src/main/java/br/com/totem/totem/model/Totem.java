package br.com.totem.totem.model;

/**
 * Created by Renato Nascimento on 11/15/2017.
 */

public class Totem {
    private Long id;
    private String nome;
    private String produto;

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

    @Override
    public String toString() {
        return id +" - "+ nome;
    }
}
