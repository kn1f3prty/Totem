package br.com.totem.totem.model;

import java.io.Serializable;

/**
 * Created by Renato Nascimento on 11/15/2017.
 */

public class Cliente implements Serializable {

    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    //private String CEP;
    //private String totem;
    //private String vendedor;
    private String site;
    private String email;
    private String caminhoFoto;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String telefone, String endereco, String site) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        //this.CEP = CEP;
        //this.totem = totem;
        //this.vendedor = vendedor;
        this.site = site;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /*public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getTotem() {
        return totem;
    }

    public void setTotem(String totem) {
        this.totem = totem;
    }

    //public String getVendedor() {
        return vendedor;
    }

    //public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }*/

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    @Override
    public String toString() {
        return id +" - "+ nome;
    }
}