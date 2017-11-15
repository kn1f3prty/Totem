package br.com.totem.totem.model;

/**
 * Created by Renato Nascimento on 11/15/2017.
 */

public class Vendedor {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String loja;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoja(String loja){
        this.loja = loja;
    }

    public String getLoja(){
        return loja;
    }

    @Override
    public String toString() {
        return id +" - "+ nome;
    }
}
