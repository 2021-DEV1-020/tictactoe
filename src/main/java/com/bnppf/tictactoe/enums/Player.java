package com.bnppf.tictactoe.enums;

public enum Player {

    X("X"),
    O("O");

    private final String value;

    Player(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
