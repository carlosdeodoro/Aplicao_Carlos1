package com.example.curso.aplicao_carlos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class Inserir_Cliente extends AppCompatActivity {

    private EditText edNome;
    private EditText edCPF;
    private Pessoa pessoaclicada;
    Button btnCadastrar;
    Button btvoltar;
    Button btexcluir;
    boolean ehedicao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir__cliente);

        edNome = (EditText) findViewById(R.id.txtNome);
        edCPF = (EditText) findViewById(R.id.txtCPF);
        pessoaclicada = (Pessoa) getIntent().getSerializableExtra( "pessoaclicada" );
        btvoltar = (Button)findViewById(R.id.btvoltar);
        btexcluir = (Button)findViewById(R.id.btnExcluir);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        if ( pessoaclicada != null ) {
            edNome.setText( pessoaclicada.getNome() );
            edCPF.setText( pessoaclicada.getCpf() );
            ehedicao = true;
        }
        else {
            ehedicao = false;
        }

        btexcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper mDbHelper = new DbHelper(getBaseContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                String ids = Integer.toString(pessoaclicada.getCodigo());
                String[] args = { ids };
                db.delete("CLIENTES","COD_CLIENTE=?", args  );
                finish();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (  ehedicao  ) {
                    DbHelper mDbHelper = new DbHelper(getBaseContext());
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("NOME", edNome.getText().toString());
                    values.put("CPFCGC", edCPF.getText().toString());
                    String ids = Integer.toString(pessoaclicada.getCodigo());
                    String[] args = { ids };
                    db.update("CLIENTES", values,"COD_CLIENTE=?", args  );
                  finish();

                }
                else{
                    DbHelper mDbHelper = new DbHelper(getBaseContext());
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("NOME", edNome.getText().toString());
                    values.put("CPFCGC", edCPF.getText().toString());
                    db.insert( "CLIENTES", null, values );
                   finish();

                }
            }
        });
        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




}
