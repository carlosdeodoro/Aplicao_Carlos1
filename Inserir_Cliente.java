package com.example.curso.aplicao_carlos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class Inserir_Cliente extends AppCompatActivity {

    String vTipo;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir__cliente);
        Button btvoltar = (Button)findViewById(R.id.btvoltar);
        btvoltar = (Button)findViewById(R.id.btvoltar);
        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        intent = getIntent();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                EditText txtNome = (EditText) findViewById(R.id.txtNome);
                EditText txtCPF = (EditText) findViewById(R.id.txtCPF);

                DbHelper dbHelper = new DbHelper(getBaseContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor c = db.rawQuery("select max(CODIGO_CLIENTE) from CLIENTES",null);
                int idcliente = 0;
                if (c.moveToFirst())
                {
                    idcliente=c.getInt(0);
                }
                idcliente += 1;
                ContentValues contentValues = new ContentValues();
                contentValues.put("CODIGO_CLIENTE - ", idcliente);
                contentValues.put("NOME_CLIENTE", txtNome.getText().toString());
                contentValues.put("CPF", txtCPF.getText().toString());
                long newRowId;
                newRowId = db.insert("CLIENTES","CODIGO_CLIENTE" , contentValues);
                Toast.makeText(getBaseContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();



            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setResult(RESULT_OK, intent);
                    intent.putExtra("returnedData", "Teste de retorno com Sucesso!");
                    finish();

            }
        });
    }
}
