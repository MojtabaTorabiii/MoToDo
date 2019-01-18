package com.red1_torabi.mojtabat.motodo.Model;

import java.util.Date;

public class Todo  implements Comparable<Todo> {

    public String subject;
    public Date dueDate;
    public String dueTime;
    public Priority priority;

    @SuppressWarnings("unused")
    public Todo(){
        super();
    }

    public Todo(String subject){
        super();
        this.subject = subject;
    }

    @Override
    public int compareTo(Todo todo) {

        if(todo.dueDate != null && this.dueDate == null) {
            return 1;
        }
        if(todo.dueDate == null && this.dueDate != null) {
            return -1;
        }

        if(todo.dueDate != null && this.dueDate != null && todo.dueDate.compareTo(this.dueDate) > 0) {
            return -1;
        } else if(todo.dueDate != null && this.dueDate != null && todo.dueDate.compareTo(this.dueDate) < 0) {
            return 1;
        } else {
            return this.priority.getValue() - todo.priority.getValue();
        }
    }
}