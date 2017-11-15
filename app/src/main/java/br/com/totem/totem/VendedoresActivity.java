package br.com.totem.totem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VendedoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedores);

        Button botaoAdd = (Button) findViewById(R.id.lista_vendedores_floating_button);
        assert botaoAdd != null;

        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendedoresActivity.this, CadastroVendedoresActivity.class);
                startActivity(intent);
            }
        });

    }
}
