package br.com.totem.totem.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.totem.totem.model.Cliente;

/**
 * Created by Renato Nascimento on 11/15/2017.
 */

public class ClienteDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 2;
    private static final String TABELA = "Cliente";
    private static final String BANCO = "TotemDataBase";

    public ClienteDAO(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY, "
                + " nome TEXT NOT NULL, "
                + " telefone TEXT, "
                + " endereco TEXT, "
                + " email TEXT, "
                + " site TEXT, "
                + " caminhoFoto TEXT);";
        //+ " id_vendedor INTEGER, "
        //+ " FOREIGN KEY (id_vendedor) REFERENCES Vendedor(id), "
        //+ " id_totem INTEGER, "
        //+ " FOREIGN KEY (id_totem) REFERENCES Totem(id), "
        //+ " cep TEXT);";

        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public List<Cliente> getLista() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABELA + ";", null);
        while (c.moveToNext()) {
            Cliente cliente = new Cliente();
            cliente.setId(c.getLong(c.getColumnIndex("id")));
            cliente.setNome(c.getString(c.getColumnIndex("nome")));
            cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));
            cliente.setEndereco(c.getString(c.getColumnIndex("endereco")));
            cliente.setSite(c.getString(c.getColumnIndex("site")));
            cliente.setEmail(c.getString(c.getColumnIndex("email")));
            cliente.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            clientes.add(cliente);
        }
        c.close();
        return clientes;
    }

    public Cliente getClientePorId(Long id) {

        String[] args = {id.toString()};
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + " WHERE id=?", args);
        c.moveToFirst();

        Cliente cliente = new Cliente();
        cliente.setId(c.getLong(c.getColumnIndex("id")));
        cliente.setNome(c.getString(c.getColumnIndex("nome")));
        cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));
        cliente.setEndereco(c.getString(c.getColumnIndex("endereco")));
        //cliente.setEndereco(c.getString(c.getColumnIndex("cep")));
        cliente.setSite(c.getString(c.getColumnIndex("site")));
        cliente.setEmail(c.getString(c.getColumnIndex("email")));
        //cliente.setVendedor(c.getString(c.getColumnIndex("id_vendedor")));
        //cliente.setTotem(c.getString(c.getColumnIndex("id_totem")));

        c.close();
        return cliente;
    }

    public void update(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("endereco", cliente.getEndereco());
        values.put("email", cliente.getEmail());
        values.put("site", cliente.getSite());

        String[] args = {cliente.getId().toString()};
        getWritableDatabase().update(TABELA, values, "id=?", args);
    }

    public void insert(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("endereco", cliente.getEndereco());
        values.put("email", cliente.getEmail());
        values.put("site", cliente.getSite());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void updateOrInsert(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("endereco", cliente.getEndereco());
        values.put("email", cliente.getEmail());
        values.put("site", cliente.getSite());
        values.put("caminhoFoto",cliente.getCaminhoFoto());

        if (cliente.getId() != null) {
            String[] args = {cliente.getId().toString()};
            getWritableDatabase().update(TABELA, values, "id=?", args);
        } else {
            getWritableDatabase().insert(TABELA, null, values);
        }
    }

    public void delete(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {cliente.getId().toString()};
        db.delete(TABELA, "id=?", args);
    }
}