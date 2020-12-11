package com.bnppf.tictactoe.enums;

public enum Player {

    PLAYER_1("X"),
    PLAYER_2("O");

    private final String value;

    Player(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
