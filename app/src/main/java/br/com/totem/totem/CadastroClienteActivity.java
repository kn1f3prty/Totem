package br.com.totem.totem;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import br.com.totem.totem.model.Cliente;
import br.com.totem.totem.DAO.ClienteDAO;
import br.com.totem.totem.controller.ClienteCtrl;

public class CadastroClienteActivity extends AppCompatActivity {

    private ClienteCtrl helper;
    public static final String CLIENTE_SELECIONADO = "clienteSelecionado";
    private String localArquivoFoto;
    private static final int TIRA_FOTO = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        this.helper = new ClienteCtrl(this);

        final Cliente clienteSelecionado = (Cliente) getIntent().getSerializableExtra(CLIENTE_SELECIONADO);
        if (clienteSelecionado != null){
            helper.colocaClienteNoFormulario(clienteSelecionado);
        }

        Button foto = helper.getFotoButton();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) +
                        "/" + System.currentTimeMillis()+ ".jpg";
                File arquivo = new File(localArquivoFoto);
                Uri localFoto = Uri.fromFile(arquivo);
                Intent irParaCamera = new
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT,localFoto);
                startActivityForResult(irParaCamera,TIRA_FOTO);

            }
        });

        Button agendar = (Button) findViewById(R.id.cliente_agenda_button);
        agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                startActivity(intent);
            }
        });

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

                if (helper.temNome() && helper.temEndereco()) { //Inserir apenas se informou ao menos o nome

                    ClienteDAO cd = new ClienteDAO(CadastroClienteActivity.this);
                    cd.updateOrInsert(c);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if(requestCode == TIRA_FOTO){
            if(resultCode == Activity.RESULT_OK){
                helper.carregaImagem(this.localArquivoFoto);
            }else{
                this.localArquivoFoto = null;
            }
        }
    }


}

