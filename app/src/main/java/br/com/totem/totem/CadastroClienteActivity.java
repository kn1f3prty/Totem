package br.com.totem.totem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.totem.totem.model.Cliente;
import br.com.totem.totem.model.DAO.ClienteDAO;
import br.com.totem.totem.model.controller.ClienteCtrl;

public class CadastroClienteActivity extends AppCompatActivity {

    private ClienteCtrl helper;
    public static final String CLIENTE_SELECIONADO = "clienteSelecionado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        this.helper = new ClienteCtrl(this);

        final Cliente clienteSelecionado = (Cliente) getIntent().getSerializableExtra(CLIENTE_SELECIONADO);
        if (clienteSelecionado != null){
            helper.colocaClienteNoFormulario(clienteSelecionado);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirma, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_confirma_ok:

                Cliente c = this.helper.pegaClienteDoFormulario();

                if (helper.temNome()) { //Inserir apenas se informou ao menos o nome
                    ClienteDAO cd = new ClienteDAO(CadastroClienteActivity.this);
                    cd.update(c);
                    cd.close();
                    Toast.makeText(this, "Operação realizada com Sucesso", Toast.LENGTH_LONG).show();
                    finish();
                    return false;
                } else {
                    helper.mostraErro();

                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

