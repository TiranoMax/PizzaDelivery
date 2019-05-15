package edu.up.pizzadelivery.model;

import java.io.Serializable;
import java.util.List;

public class Sabor implements Serializable {

    private int idSabor;
    private String nome;
    private List<Ingrediente> ingredientes;

    public int getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(int idSabor) {
        this.idSabor = idSabor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

}
