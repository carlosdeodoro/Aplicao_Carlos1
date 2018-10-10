package com.example.curso.aplicao_carlos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATA_VERSION = 1;
    public static final String DATABASE_NAME = "Aplicação Carlos";
    public DbHelper(Context context){
        super (context, DATABASE_NAME, null, DATA_VERSION);
    }

    public void onCreate (SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS CLIENTES (CODIGO_CLIENTE INTEGER PRIMARY KEY, NOME_CLIENTE TEXT, CPF TEXT )");
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        onCreate(db);
    }

    public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
}
