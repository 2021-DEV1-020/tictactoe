package com.bnppf.tictactoe.service;

public interface BoardService {

    void addValueToBoard(String position, Player player);
    boolean isBoardFull();
}
