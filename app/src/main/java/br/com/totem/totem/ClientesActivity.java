package br.com.totem.totem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.totem.totem.model.Cliente;
import br.com.totem.totem.DAO.ClienteDAO;

public class ClientesActivity extends AppCompatActivity {

    private ListView listaClientes;
    private List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        this.listaClientes = (ListView) findViewById(R.id.lista_cliente);
        registerForContextMenu(listaClientes);

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClientesActivity.this, CadastroClienteActivity.class);
                intent.putExtra(CadastroClienteActivity.CLIENTE_SELECIONADO, (Cliente) listaClientes.getItemAtPosition(position));
                startActivity(intent);
            }
        });

        Button botaoAdd = (Button) findViewById(R.id.lista_clientes_floating_button);
        assert botaoAdd != null;

        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientesActivity.this, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;

        // Guardar o cliente selecionado
        final Cliente clienteSelecionado = (Cliente) listaClientes.getAdapter().getItem(info.position);


        menu.add("Ligar");
        menu.add("Enviar SMS");

        MenuItem acharNoMapa = menu.add("Achar no Mapa");
        Intent intentMapa = new Intent(ClientesActivity.this, MapsActivity.class);
        intentMapa.putExtra(MapsActivity.CLIENTE_SELECIONADO, clienteSelecionado);
        acharNoMapa.setIntent(intentMapa);



        menu.add("Navegar no Site");

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                new AlertDialog.Builder(ClientesActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Deletar")
                .setMessage("Deseja Realmente Deletar")
                .setPositiveButton("Quero", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int
                            which) {
                        ClienteDAO dao = new ClienteDAO(ClientesActivity.this);
                        dao.delete(clienteSelecionado);
                        dao.close();
                        carregaLista(); //atualizar e exibir a lista apos a deleção
                    }}).setNegativeButton("Não", null).show();
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.carregaLista();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.carregaLista();
    }

    public void carregaLista() {
        ClienteDAO cd = new ClienteDAO(this);
        List<Cliente> clientes = cd.getLista();
        cd.close();
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);

        listaClientes.setAdapter(adapter);
    }
}
