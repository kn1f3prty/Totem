package br.com.totem.totem.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import br.com.totem.totem.model.Totem;


/**
 * Created by Renato Nascimento on 11/15/2017.
 */

public class TotemDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "Totem";
    private static final String BANCO = "TotemDB";

    public TotemDAO(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY, "
                + " nome TEXT NOT NULL, "
                + " colecao TEXT, "
                + " produto TEXT, ";

        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public List<Totem> getLista() {
        List<Totem> totens = new ArrayList<Totem>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABELA + ";", null);
        while (c.moveToNext()) {
            Totem totem = new Totem();
            totem.setId(c.getLong(c.getColumnIndex("id")));
            totem.setNome(c.getString(c.getColumnIndex("nome")));
            totem.setColecao(c.getString(c.getColumnIndex("colecao")));
            totem.setProduto(c.getString(c.getColumnIndex("produto")));

            totens.add(totem);
        }
        c.close();
        return totens;
    }

    public Totem getTotensPorId(Long id){

        String[] args = {id.toString()};
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + " WHERE id=?", args);
        c.moveToFirst();

        Totem totem = new Totem();
        totem.setId(c.getLong(c.getColumnIndex("id")));
        totem.setNome(c.getString(c.getColumnIndex("nome")));
        totem.setColecao(c.getString(c.getColumnIndex("colecao")));
        totem.setProduto(c.getString(c.getColumnIndex("produto")));

        c.close();
        return  totem;
    }

    public void update(Totem totem){
        ContentValues values = new ContentValues();
        values.put("nome", totem.getNome());
        values.put("colecao", totem.getColecao());
        values.put("produto", totem.getProduto());


        String[] args = {totem.getId().toString()};
        getWritableDatabase().update(TABELA, values,"id=?",args);
    }

    public void insert(Totem totem) {
        ContentValues values = new ContentValues();
        values.put("nome", totem.getNome());
        values.put("colecao", totem.getColecao());
        values.put("produto", totem.getProduto());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void updateOrInsert(Totem totem){
        ContentValues values = new ContentValues();
        values.put("nome", totem.getNome());
        values.put("colecao", totem.getColecao());
        values.put("produto", totem.getProduto());

        if (totem.getId() != null){
            String[] args = {totem.getId().toString()};
            getWritableDatabase().update(TABELA, values,"id=?",args);
        }else{
            getWritableDatabase().insert(TABELA, null, values);
        }
    }

    public void delete(Totem totem){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {totem.getId().toString()};
        db.delete(TABELA,"id=?",args);
    }

}
