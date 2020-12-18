package com.nicolas.listadetarefa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.nicolas.listadetarefa.R;
import com.nicolas.listadetarefa.helper.TarefaDAO;
import com.nicolas.listadetarefa.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText textInputEditText;
    private Button button;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        textInputEditText = findViewById(R.id.editTarefa);
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("a");

        if (tarefaAtual != null) {
            textInputEditText.setText(tarefaAtual.getNomeTarefa());
        }

        button = findViewById(R.id.buttonAdicionar);
        TarefaDAO tarefaDAO = new TarefaDAO(this);

        button.setOnClickListener(v -> {

            if (tarefaAtual != null) {

                String nomeTarefa = textInputEditText.getText().toString();
                if (!nomeTarefa.isEmpty()) {

                    Tarefa tarefa = new Tarefa();
                    tarefa.setNomeTarefa(nomeTarefa);
                    tarefa.setId(tarefaAtual.getId());

                    if (tarefaDAO.atualizar(tarefa)) {
                        finish();
                        Toast.makeText(this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {

                String nomeTarefa = textInputEditText.getText().toString();
                if (!nomeTarefa.isEmpty()) {

                    Tarefa tarefa = new Tarefa();
                    tarefa.setNomeTarefa(nomeTarefa);

                    if (tarefaDAO.inserir(tarefa)) {
                        finish();
                        Toast.makeText(this,
                                "Tarefa adicionada com suceso", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(this,
                                "Houve um erro ao adicionar tarefa",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}