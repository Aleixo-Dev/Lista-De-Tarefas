package com.nicolas.listadetarefa.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nicolas.listadetarefa.utils.ClickListener;
import com.nicolas.listadetarefa.R;
import com.nicolas.listadetarefa.ui.adapter.ListaDeTarefasAdapter;
import com.nicolas.listadetarefa.helper.TarefaDAO;
import com.nicolas.listadetarefa.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClickListener {

    private RecyclerView recyclerView;
    private ListaDeTarefasAdapter listaDeTarefasAdapter;
    private List<Tarefa> tarefas = new ArrayList<>();
    private FloatingActionButton fab;
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();
        configurarRecyclerView();

        fab.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AdicionarTarefaActivity.class));
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Tarefa tarefa = new Tarefa();
                tarefa = tarefas.remove(viewHolder.getAdapterPosition());

                listaDeTarefasAdapter.notifyDataSetChanged();
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                if (tarefaDAO.deletar(tarefa)) {
                    configurarRecyclerView();
                    Toast.makeText(MainActivity.this,
                            "Tarefa removida com sucesso", Toast.LENGTH_SHORT).show();
                }

            }
        }).attachToRecyclerView(recyclerView);

    }

    public void inicializarComponentes() {
        recyclerView = findViewById(R.id.my_recycler_view);
        fab = findViewById(R.id.fab);
    }

    public void configurarRecyclerView() {

        TarefaDAO tarefaDAO = new TarefaDAO(this);
        tarefas = tarefaDAO.listar();
        listaDeTarefasAdapter = new ListaDeTarefasAdapter(tarefas, this, MainActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listaDeTarefasAdapter);
    }

    @Override
    protected void onStart() {
        configurarRecyclerView();
        super.onStart();
    }

    @Override
    public void itemClickListener(int position) {

        tarefaSelecionada = tarefas.get(position);
        Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
        intent.putExtra("a", tarefaSelecionada);
        startActivity(intent);
    }

}