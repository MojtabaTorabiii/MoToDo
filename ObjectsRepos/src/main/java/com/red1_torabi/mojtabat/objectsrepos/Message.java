package com.red1_torabi.mojtabat.objectsrepos;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    private static final long serialVersionUID = -5399605122490343339L;

    private User user;
    private List<Todo> todo;
    private Integer id;
    private int messageType;

    public Message(User user, List<Todo> todo, Integer id, int messageType){
        this.user = user;
        this.todo = todo;
        this.id = id;
        this.messageType = messageType;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Todo> getTodo() {
        return todo;
    }

    public void setTodo(List<Todo> todo) {
        this.todo = todo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}

