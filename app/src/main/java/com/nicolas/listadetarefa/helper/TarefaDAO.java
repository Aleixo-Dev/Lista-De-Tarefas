package com.nicolas.listadetarefa.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nicolas.listadetarefa.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    public SQLiteDatabase escrever;
    public SQLiteDatabase ler;

    public TarefaDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        escrever = dbHelper.getWritableDatabase();
        ler = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean inserir(Tarefa tarefa) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", tarefa.getNomeTarefa());

        try {
            escrever.insert(DbHelper.NOME_TABELA, null, contentValues);
        } catch (Exception e) {
            Log.d("ERRO", "HOUVE UM ERRO AO INSERIR OS DADOS: ");
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try {
            String[] args = {String.valueOf(tarefa.getId())};
            escrever.delete(DbHelper.NOME_TABELA, "id=?", args);
        } catch (Exception e) {
            Log.d("TAG", "HOUVE UM ERRO AO ATUALIZAR BANCO DE DADOS: ");
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", tarefa.getNomeTarefa());

        try {
            String[] args = {String.valueOf(tarefa.getId())};
            escrever.update(DbHelper.NOME_TABELA, contentValues, "id=?", args);

        } catch (Exception e) {

            Log.d("TAG", "HOUVE UM ERRO AO ATUALIZAR BANCO DE DADOS: ");
        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.NOME_TABELA + " ;";
        Cursor cursor = ler.rawQuery(sql, null);

        while (cursor.moveToNext()) {

            Tarefa tarefa = new Tarefa();

            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nomeTarefa = cursor.getString(cursor.getColumnIndex("nome"));

            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);
            tarefas.add(tarefa);

        }
        return tarefas;
    }
}
