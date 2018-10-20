package com.example.curso.aplicao_carlos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private String cpf;
    private int codigo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCodigo(int codigo){
        this.codigo=codigo;
    }

    public int getCodigo(){
        return this.codigo;
    }

    @Override
    public String toString() {
        return nome +" - " + cpf;
    }


}
