package br.com.totem.totem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.totem.totem.model.Cliente;
import br.com.totem.totem.model.DAO.ClienteDAO;

public class ClientesActivity extends AppCompatActivity {

    private ListView listaClientes;
    private List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        this.listaClientes = (ListView) findViewById(R.id.lista_cliente);
       // registerForContextMenu(this.listaClientes);

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClientesActivity.this, CadastroClienteActivity.class);
                intent.putExtra(CadastroClienteActivity.CLIENTE_SELECIONADO, (Cliente) listaClientes.getItemAtPosition(position));
                startActivity(intent);
            }});

        listaClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int  position, long id) {
            Cliente clienteSelecionado = (Cliente) parent.getItemAtPosition(position);
            ContextActionBar actionBar = new ContextActionBar(clienteSelecionado, ClientesActivity.this);
            ClientesActivity.this.startSupportActionMode(actionBar);
            return true;
        }});

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
        menu.add("Ligar");
        menu.add("Enviar SMS");
        menu.add("Achar no Mapa");
        menu.add("Navegar no Site");
        menu.add("Deletar");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // this.carreaLista();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.carregaLista();
    }

    public void carregaLista(){
        ClienteDAO cd = new ClienteDAO(this);
        List<Cliente> clientes = cd.getLista();
        cd.close();
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);

        listaClientes.setAdapter(adapter);
    }
}
