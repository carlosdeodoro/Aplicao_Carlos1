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
    Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir__cliente);
        Button btvoltar = (Button)findViewById(R.id.btvoltar);
        btvoltar = (Button)findViewById(R.id.btvoltar);
        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        intent = getIntent();

        final EditText txtNome = (EditText) findViewById(R.id.txtNome);
        final EditText txtCPF = (EditText) findViewById(R.id.txtCPF);



        intent = getIntent();
        if(intent != null) {
            pessoa = intent.getExtras().getParcelable("pessoa");

            if (pessoa != null) {
                txtNome.setText(pessoa.getNome());
                txtCPF.setText(pessoa.getCpf());
            }
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(pessoa == null) {
                    DbHelper dbhelper = new DbHelper(getBaseContext());
                    Pessoa p = new Pessoa(null);
                    p.setNome(txtNome.getText().toString());
                    p.setCpf(txtCPF.getText().toString());
                    dbhelper.SalvarPessoa(p);
                }
                else {
                    DbHelper dbHelper = new DbHelper(getBaseContext());
                    Intent intent = getIntent();
                    Integer id = intent.getIntExtra("ID",0);
                    pessoa.setNome(txtNome.getText().toString());
                    pessoa.setCpf(txtCPF.getText().toString());
                    dbHelper.Alterar(pessoa, id);

                }


                /*nt idcliente = 0;
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
                newRowId = db.insert("CLIENTES","CODIGO_CLIENTE" , contentValues);*/
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
