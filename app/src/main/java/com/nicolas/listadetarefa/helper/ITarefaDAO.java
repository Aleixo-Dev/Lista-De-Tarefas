package com.nicolas.listadetarefa.helper;

import com.nicolas.listadetarefa.model.Tarefa;

import java.util.List;

public interface ITarefaDAO {

    boolean inserir(Tarefa tarefa);

    boolean deletar(Tarefa tarefa);

    boolean atualizar(Tarefa tarefa);

    List<Tarefa> listar();
}
