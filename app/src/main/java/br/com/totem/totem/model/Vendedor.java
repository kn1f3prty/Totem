package br.com.totem.totem.model;

/**
 * Created by Renato Nascimento on 11/15/2017.
 */

public class Vendedor {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cliente;

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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Vendedor(Long id, String nome, String telefone, String email, String cliente) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cliente = cliente;
    }

    public Vendedor() {
    }

    @Override
    public String toString() {
        return id +" - "+ nome;
    }
}
