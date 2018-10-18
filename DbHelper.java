package com.example.curso.aplicao_carlos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.ContextMenu;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATA_VERSION = 1;
    public static final String DATABASE_NAME = "Aplicação Carlos";
    public DbHelper(Context context){
        super (context, DATABASE_NAME, null, DATA_VERSION);
    }

    public void onCreate (SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS CLIENTES (CODIGO_CLIENTE INTEGER PRIMARY KEY AUTOINCREMENT, NOME_CLIENTE TEXT, CPF TEXT )");
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        onCreate(db);
    }

    public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void SalvarPessoa(Pessoa p){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME_CLIENTE", p.getNome());
        contentValues.put("CPF", p.getCpf());
        long newRowId;
        getWritableDatabase().insert("CLIENTES","CODIGO_CLIENTE" , contentValues);
    }

    public void Alterar (Pessoa p, int id){
        ContentValues values = new ContentValues();
        values.put("NOME_CLIENTE", p.getNome());
        values.put("CPF", p.getCpf());

        getWritableDatabase().update("CLIENTES", values, "CODIGO_CLIENTE: " + id, null);
    }
}
