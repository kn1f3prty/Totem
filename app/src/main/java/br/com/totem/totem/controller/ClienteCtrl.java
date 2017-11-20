package br.com.totem.totem.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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
    private ImageView fotoCliente;
    private Button fotoButton;
    Cliente cliente;

    public ClienteCtrl(CadastroClienteActivity activity) {
        this.cliente = new Cliente();
        this.nome = (EditText) activity.findViewById(R.id.cliente_nome);
        this.telefone = (EditText) activity.findViewById(R.id.cliente_telefone);
        this.email = (EditText) activity.findViewById(R.id.cliente_email);
        this.endereco = (EditText) activity.findViewById(R.id.cliente_endereco);
        this.site = (EditText) activity.findViewById(R.id.cliente_site);
        this.fotoCliente = (ImageView) activity.findViewById(R.id.cliente_foto);
        this.fotoButton = (Button) activity.findViewById(R.id.cliente_foto_button);
    }

    public Cliente pegaClienteDoFormulario() {
        cliente.setNome(nome.getText().toString());
        cliente.setTelefone(telefone.getText().toString());
        cliente.setEmail(email.getText().toString());
        cliente.setEndereco(endereco.getText().toString());
        cliente.setSite(site.getText().toString());
        cliente.setCaminhoFoto((String) fotoCliente.getTag());
        return cliente;
    }

    public void colocaClienteNoFormulario(Cliente cliente) {
        nome.setText(cliente.getNome());
        telefone.setText(cliente.getTelefone());
        email.setText(cliente.getEmail());
        endereco.setText(cliente.getEndereco());
        site.setText(cliente.getSite());
        if (cliente.getCaminhoFoto() != null) {
            this.carregaImagem(cliente.getCaminhoFoto());
        }
        this.cliente = cliente;
    }

    public boolean temNome() {
        return !nome.getText().toString().isEmpty();
    }

    public boolean temEndereco() {
        return !endereco.getText().toString().isEmpty();
    }

    public void mostraErro() {
        nome.setError("Nome não pode ser vazio!");
        endereco.setError("Endereço não pode ser vazio!");
    }

    public Button getFotoButton() {
        return fotoButton;
    }

    public void carregaImagem(String localArquivoFoto) {
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);
        fotoCliente.setImageBitmap(imagemFotoReduzida);
        fotoCliente.setTag(localArquivoFoto);
        fotoCliente.setScaleType(ImageView.ScaleType.FIT_XY);
        imagemFoto.recycle();

    }


}
