package com.nicolas.listadetarefa.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_BANCO = "DB_TAREFAS";
    public static String NOME_TABELA = "db_tarefas";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + NOME_BANCO +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL); ";

        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Log.d("ERRO_DB", "HOUVE UM ERRO AO CRIAR O BANCO DE DADOS!: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + NOME_TABELA + " ;";
        db.execSQL(sql);

    }
}
