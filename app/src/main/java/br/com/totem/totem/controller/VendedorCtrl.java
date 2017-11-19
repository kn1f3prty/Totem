package br.com.totem.totem.controller;

import android.widget.EditText;

import br.com.totem.totem.CadastroVendedoresActivity;
import br.com.totem.totem.R;
import br.com.totem.totem.model.Vendedor;

/**
 * Created by Renato Nascimento on 11/18/2017.
 */

public class VendedorCtrl {

    private EditText nome;
    private EditText telefone;
    private EditText email;
    //private Spinner cliente;
    private Vendedor vendedor;

    public VendedorCtrl (CadastroVendedoresActivity activity){
        this.nome = (EditText) activity.findViewById(R.id.vendedor_nome);
        this.telefone = (EditText) activity.findViewById(R.id.vendedor_telefone);
        this.email = (EditText) activity.findViewById(R.id.vendedor_email);
        //this.cliente = (Spinner) activity.findViewById(R.id.vendedor_cliente);
    }

    public Vendedor pegaVendedorDoFormulario() {
        vendedor.setNome(nome.getText().toString());
        vendedor.setTelefone(telefone.getText().toString());
        vendedor.setEmail(email.getText().toString());
        //vendedor.setCliente(cliente.getSelectedItemId());

        return vendedor;
    }

    public Vendedor colocaVendedorNoFormulario(Vendedor vendedor){


        nome.setText(vendedor.getNome());
        telefone.setText(vendedor.getTelefone());
        email.setText(vendedor.getEmail());
        //cliente.setAdapter(vendedor.getCliente());

        return vendedor;
    }
}
