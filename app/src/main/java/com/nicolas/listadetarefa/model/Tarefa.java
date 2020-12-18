package com.nicolas.listadetarefa.model;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private int id;
    private String nomeTarefa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }
}
