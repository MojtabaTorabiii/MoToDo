package com.red1_torabi.mojtabat.motodo.Adapters;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.red1_torabi.mojtabat.motodo.Model.Todo;
import com.red1_torabi.mojtabat.motodo.R;

import java.util.List;
public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.MyViewHolder> {

    List<Todo> todoList;
    private ViewGroup parent;
    public TodoRecyclerAdapter(List<Todo> todoList) {
        this.todoList = todoList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todo_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Todo todo = todoList.get(i);
        myViewHolder.title.setText(todo.subject);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    /*@Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Todo todo = todoList.get(i);
        myViewHolder.title.setText(todo.subject);
//        myViewHolder.priority.setText(todo.priority.getValue());

    }
    @Override
    public int getItemCount() {
        return todoList.size();
    }*/

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView priority, title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.todo_row_title);
            priority = itemView.findViewById(R.id.todo_row_priority);

        }
    }
}