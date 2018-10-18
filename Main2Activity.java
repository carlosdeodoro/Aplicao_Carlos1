package com.example.curso.aplicao_carlos;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    Button btVoltar;
    Button btPesquisar;
    ListView listCliente;
    String vTipo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        btPesquisar = (Button) findViewById(R.id.btPesquisar);
        listCliente = (ListView) findViewById(R.id.listCliente);
        intent = getIntent();
        vTipo = intent.getStringExtra("PARAMETRO");
        if (vTipo == null) ;
        vTipo = "";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vTipo.equals("")) {
                    finish();
                } else {

                    setResult(RESULT_OK, intent);
                    intent.putExtra("returnData", "Teste resalizado com sucesso");
                }

            }
        });

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JsonAsyncTasck()
                        .execute("http://192.168.181.134/apicliente/api/cliente/retornaclientes?tipo=json");
            }
        });
        listCliente.setOnItemClickListener(new ItemClickedListener());

    }

    class JsonAsyncTasck extends AsyncTask<String, Void, List<Pessoa>> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(Main2Activity.this, "Aguarde", "Processando Lista JASON");
        }


        @Override
        protected List<Pessoa> doInBackground(String... params) {
            String urlString = params[0];
            HttpClient httpcliente = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(urlString);
            try {
                HttpResponse response = httpcliente.execute(httpget);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputstream = entity.getContent();
                    String json = getStringFromInputStream(inputstream);
                    inputstream.close();
                    List<Pessoa> pessoas = getPessoas(json);
                    return pessoas;
                }
            } catch (IOException e) {
                Log.e("Erro ao buscar clientes", "Falha ao tentar conectar");
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Pessoa> result) {
            super.onPostExecute(result);
            dialog.dismiss();
                if (result.size() > 0) {

            ArrayAdapter<Pessoa> adapter = new ArrayAdapter<>
                    (Main2Activity.this, android.R.layout.simple_list_item_1, result);
            listCliente.setAdapter(adapter);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder
                    (Main2Activity.this)
                    .setTitle("Erro")
                    .setMessage("Não foi possivel encontrar os dados")
                    .setPositiveButton("OK", null);
            builder.create().show();
        }
    }
        private List<Pessoa> getPessoas(String jsonString) {
            List<Pessoa> pessoas = new ArrayList<Pessoa>();
            try {
                JSONArray pessoasJson = new JSONArray(jsonString);
                JSONObject objpessoa;

                for (int i = 0; i < pessoasJson.length(); i++) {
                    objpessoa = new JSONObject(pessoasJson.getString(i));
                    Log.i("Pessoa encontrada:", "nome" + objpessoa.getString("nome"));
                    Pessoa objeto = new Pessoa(null);
                    objeto.setNome(objpessoa.getString("nome"));
                    objeto.setCpf(objpessoa.getString("cpf"));
                    pessoas.add(objeto);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return pessoas;
        }


        private String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    }
    private class ItemClickedListener implements android.widget.AdapterView.OnItemClickListener {
        public void onItemClick (AdapterView<?> arg0, View arg1, int position, long id) {
            Pessoa pessoa = (Pessoa) arg0.getItemAtPosition(position);
            mensagem("Dados do cliente", pessoa.getNome() + " " + pessoa.getCpf());
        }
    }
    public void mensagem (String titulo, String mensagem) {
        android.app.AlertDialog.Builder alertamensagem = new android.app.AlertDialog.Builder(Main2Activity.this);
        alertamensagem.setMessage(mensagem);
        alertamensagem.setMessage(titulo);
        alertamensagem.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertamensagem.show();
    }
}






