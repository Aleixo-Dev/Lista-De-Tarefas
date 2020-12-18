package com.nicolas.listadetarefa.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicolas.listadetarefa.utils.ClickListener;
import com.nicolas.listadetarefa.R;
import com.nicolas.listadetarefa.model.Tarefa;

import java.util.List;

public class ListaDeTarefasAdapter extends RecyclerView.Adapter<
        ListaDeTarefasAdapter.MyViewHolder> {

    List<Tarefa> tarefas;
    ClickListener clickListener;
    Context context;

    public interface ItemLongClickListener {
        void onDeleteClick(int position);
    }

    public ListaDeTarefasAdapter(List<Tarefa> t, ClickListener clickListener, Context context) {
        this.tarefas = t;
        this.clickListener = clickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_de_tarefas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(tarefas.get(position));
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nomeTarefa;
        private Tarefa tarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTarefa = itemView.findViewById(R.id.nome_tarefa);
            itemView.setOnClickListener(this);

        }

        public void bind(Tarefa tarefa) {
            this.tarefa = tarefa;
            nomeTarefa.setText(tarefa.getNomeTarefa());

        }

        @Override
        public void onClick(View v) {
            clickListener.itemClickListener(getAdapterPosition());
        }

    }
}
