package com.app.jsonmodel;

public class AuthorCreateJsonModel {
    private String name;

    public AuthorCreateJsonModel() {
    }

    public AuthorCreateJsonModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
