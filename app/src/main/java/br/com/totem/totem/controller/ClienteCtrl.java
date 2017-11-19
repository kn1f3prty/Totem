package br.com.totem.totem.controller;

import android.widget.EditText;

//import br.com.totem.totem.CadastroVendedoresActivity;
import br.com.totem.totem.CadastroClienteActivity;
import br.com.totem.totem.R;
import br.com.totem.totem.model.Cliente;

/**
 * Created by Renato Nascimento on 11/18/2017.
 */

public class ClienteCtrl {

    private EditText nome;
    private EditText telefone;
    private EditText email;
    private EditText endereco;
    private EditText site;
    Cliente cliente;

    public ClienteCtrl (CadastroClienteActivity activity){
        this.cliente = new Cliente();
        this.nome = (EditText) activity.findViewById(R.id.cliente_nome);
        this.telefone = (EditText) activity.findViewById(R.id.cliente_telefone);
        this.email = (EditText) activity.findViewById(R.id.cliente_email);
        this.endereco = (EditText) activity.findViewById(R.id.cliente_endereco);
        this.site = (EditText) activity.findViewById(R.id.cliente_site);
        //this.cliente = (Spinner) activity.findViewById(R.id.vendedor_cliente);
    }

    public Cliente pegaClienteDoFormulario() {
        cliente.setNome(nome.getText().toString());
        cliente.setTelefone(telefone.getText().toString());
        cliente.setEmail(email.getText().toString());
        cliente.setEndereco(endereco.getText().toString());
        cliente.setSite(site.getText().toString());
        //vendedor.setCliente(cliente.getSelectedItemId());

        return cliente;
    }

    public Cliente colocaClienteNoFormulario(Cliente cliente){

        nome.setText(cliente.getNome());
        telefone.setText(cliente.getTelefone());
        email.setText(cliente.getEmail());
        endereco.setText(cliente.getEndereco());
        site.setText(cliente.getSite());
        //cliente.setAdapter(vendedor.getCliente());

        return cliente;
    }

    public boolean temNome() {
        return !nome.getText().toString().isEmpty();
    }

    public void mostraErro() {
        nome.setError("Campo não pode ser vazio");
    }
}
