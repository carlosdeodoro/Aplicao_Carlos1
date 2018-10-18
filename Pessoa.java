package com.example.curso.aplicao_carlos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Pessoa implements Parcelable{

    private static final long serialVersion = 1L;
    private int id;
    private String nome;
    private String cpf;
    private String resultado;


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    public void Resultado(String nome, String cpf, int id){
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
        this.resultado = " ID:" +id + " Nome: " + nome + " CPF: " + cpf;
    }

    @Override
    public String toString() {return resultado;}


    @Override
    public int describeContents() {
        return 0;
    }

    protected Pessoa(Parcel in) {
        if (in != null){
            this.nome = in.readString();
            this.cpf = in.readString();
        }

    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(cpf);
    }

    public static final Creator<Pessoa> CREATOR = new Creator<Pessoa>() {
        @Override
        public Pessoa createFromParcel(Parcel in) {
            return new Pessoa(in);
        }

        @Override
        public Pessoa[] newArray(int size) {
            return new Pessoa[size];
        }
    };
}
