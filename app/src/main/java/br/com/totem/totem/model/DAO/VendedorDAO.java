package br.com.totem.totem.model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.totem.totem.model.Vendedor;

/**
 * Created by Renato Nascimento on 11/15/2017.
 */

public class VendedorDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "Vendedor";
    private static final String BANCO = "TotemDB";

    public VendedorDAO(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY, "
                + " nome TEXT NOT NULL, "
                + " telefone TEXT, "
                + " email TEXT, "
                + " id_cliente INTEGER, "
                + " FOREIGN KEY (id_loja) REFERENCES Cliente(id)) ";

        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public List<Vendedor> getLista() {
        List<Vendedor> vendedores = new ArrayList<Vendedor>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABELA + ";", null);
        while (c.moveToNext()) {
            Vendedor vendedor = new Vendedor();
            vendedor.setId(c.getLong(c.getColumnIndex("id")));
            vendedor.setNome(c.getString(c.getColumnIndex("nome")));
            vendedor.setTelefone(c.getString(c.getColumnIndex("telefone")));
            vendedor.setTelefone(c.getString(c.getColumnIndex("email")));
            vendedor.setCliente(c.getLong(c.getColumnIndex("id_cliente")));


            vendedores.add(vendedor);
        }
        c.close();
        return vendedores;
    }

    public Vendedor getVendedorPorId(Long id){

        String[] args = {id.toString()};
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + " WHERE id=?", args);
        c.moveToFirst();

        Vendedor vendedor = new Vendedor();
        vendedor.setId(c.getLong(c.getColumnIndex("id")));
        vendedor.setNome(c.getString(c.getColumnIndex("nome")));
        vendedor.setTelefone(c.getString(c.getColumnIndex("telefone")));
        vendedor.setEmail(c.getString(c.getColumnIndex("email")));
        vendedor.setCliente(c.getLong(c.getColumnIndex("id_cliente")));

        c.close();
        return  vendedor;
    }

    public void update(Vendedor vendedor){
        ContentValues values = new ContentValues();
        values.put("nome", vendedor.getNome());
        values.put("telefone", vendedor.getTelefone());
        values.put("email", vendedor.getEmail());
        values.put("id_cliente", vendedor.getCliente());

        String[] args = {vendedor.getId().toString()};
        getWritableDatabase().update(TABELA, values,"id=?",args);
    }

    public void insert(Vendedor vendedores) {
        ContentValues values = new ContentValues();
        values.put("nome", vendedores.getNome());
        values.put("telefone", vendedores.getTelefone());
        values.put("email", vendedores.getEmail());
        values.put("id_cliente", vendedores.getCliente());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void updateOrInsert(Vendedor vendedor){
        ContentValues values = new ContentValues();
        values.put("nome", vendedor.getNome());
        values.put("telefone", vendedor.getTelefone());
        values.put("email", vendedor.getEmail());
        values.put("id_cliente", vendedor.getCliente());

        if (vendedor.getId() != null){
            String[] args = {vendedor.getId().toString()};
            getWritableDatabase().update(TABELA, values,"id=?",args);
        }else{
            getWritableDatabase().insert(TABELA, null, values);
        }
    }

    public void delete(Vendedor vendedor){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {vendedor.getId().toString()};
        db.delete(TABELA,"id=?",args);
    }

}
