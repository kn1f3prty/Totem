package br.com.totem.totem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;

import br.com.totem.totem.model.Cliente;
import br.com.totem.totem.DAO.ClienteDAO;

/**
 * Created by Renato Nascimento on 11/19/2017.
 */

public class ContextActionBar implements android.support.v7.view.ActionMode.Callback {

    private Cliente clienteSelecionado;
    private ClientesActivity listaClientesActivity;

    /*Construtor da classe*/
    public ContextActionBar(Cliente clienteSelecionado, ClientesActivity
            activity) {
        this.clienteSelecionado = clienteSelecionado;
        this.listaClientesActivity = activity;
    }

    @Override
    public boolean onCreateActionMode(final android.support.v7.view.ActionMode mode, Menu menu) {


        MenuItem ligar = menu.add("Ligar");
        MenuItem mapa = menu.add("Achar no Mapa");
        MenuItem site = menu.add("Navegar no Site");

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                // usar um alert dialog para confirmar a exclusão
                new AlertDialog.Builder(listaClientesActivity)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja Realmente Deletar")
                        .setPositiveButton("Quero", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int
                                            which) {
                                        ClienteDAO dao = new ClienteDAO(listaClientesActivity);
                                        dao.delete(clienteSelecionado);
                                        dao.close();
                                        listaClientesActivity.carregaLista(); //atualizar e exibir a lista apos a deleção
                                    }}).setNegativeButton("Não", null).show();

                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onPrepareActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(android.support.v7.view.ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(android.support.v7.view.ActionMode mode) {

    }
}
