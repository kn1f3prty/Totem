package br.com.totem.totem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

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
}
