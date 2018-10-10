package com.example.curso.aplicao_carlos;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private static final long serialVersion = 1L;
    private int id;
    private String nome;
    private String cpf;


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    @Override
    public String toString() {return nome;}
}
