package com.example.atpdevmobilejava.model;

public class Finance {
    private long id;
    private String name, value, status;

    public Finance (long id, String name, String value, String status) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.status = status;
    }

    public Finance (String name, String value, String status) {
        this.name = name;
        this.value = value;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
