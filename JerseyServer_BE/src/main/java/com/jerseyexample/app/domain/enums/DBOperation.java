package com.jerseyexample.app.domain.enums;

public enum DBOperation {

    INSERTED("INSERTED"),
    UPDATED("UPDATED"),
    DELETED("DELETED");

    private final String name;

    private DBOperation(String value) {
        this.name = value;
    }

    public String value() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
