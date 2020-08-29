package com.pawel.wojtanka.service;

public enum MovieInfoCriteria {

    TITLE("title"),
    GENRE("genre"),
    DESCRIPTION("description"),
    RELEASE_DATE_FROM("releaseDate"),
    RELEASE_DATE_TO("relaseDateTo");

    private final String name;

    MovieInfoCriteria(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
