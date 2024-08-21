package com.intuit.marketplace.model;

public enum ProjectStatus {
    OPEN("OPEN"), CLOSED("CLOSED");

    private final String name;

    ProjectStatus(String name) {
        this.name = name;
    }
}
