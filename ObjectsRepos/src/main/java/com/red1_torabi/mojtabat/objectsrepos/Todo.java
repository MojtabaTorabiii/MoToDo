package com.red1_torabi.mojtabat.objectsrepos;

//import android.graphics.Color;

import java.awt.Color;
import java.util.Date;

enum Priority {
    LOW("low", 2, Color.blue),
    MEDIUM("medium", 1,Color.green),
    HIGH("high", 0, Color.red);

    private final int value;
    private final String name;
    private final Color color;

    Priority(final String newName, final int newValue, final Color newColor) {
        name = newName;
        value = newValue;
        color = newColor;
    }

    public int getValue() {
        return value;
    }

   /* public String getName() {
        return MainActivity.getContext().getString(MainActivity.getContext().getResources().getIdentifier(name, "string",  MainActivity.getContext().getPackageName()));
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString(){
        return MainActivity.getContext().getString(MainActivity.getContext().getResources().getIdentifier(name, "string",  MainActivity.getContext().getPackageName()));
    }*/
}
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
