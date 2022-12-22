package com.example.courseworkcomputershop.data.Models;

import java.io.Serializable;

public class User implements Serializable
{
    private int id;
    private String login;
    private String password;
    private String fio;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFio() { return fio;}

    public void setFio(String fio) {this.fio = fio;}
}
