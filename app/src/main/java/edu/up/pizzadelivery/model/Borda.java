package edu.up.pizzadelivery.model;

import java.io.Serializable;

public class Borda implements Serializable {

    private int idBorda;
    private int nome;

    public int getIdBorda() {
        return idBorda;
    }

    public void setIdBorda(int idBorda) {
        this.idBorda = idBorda;
    }

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

}