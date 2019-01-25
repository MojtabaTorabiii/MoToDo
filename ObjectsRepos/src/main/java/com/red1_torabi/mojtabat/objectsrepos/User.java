package com.red1_torabi.mojtabat.objectsrepos;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name;
    private String familyName;
    private String userName;
    private String email;
    private String password;
    private String userType;
    private boolean userLogedin;



    public User(int id, String name, String familyName, String userName, String email, String password, String userType,boolean userLogedin) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.userLogedin = userLogedin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isUserLogedin() {
        return userLogedin;
    }

    public void setUserLogedin(boolean userLogedin) {
        this.userLogedin = userLogedin;
    }
}
