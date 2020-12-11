package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;

import java.util.Map;

public interface BoardService {

    void addValueToBoard(String position, Player player);
    boolean isBoardFull();
    Map<String, Player> getBoard();
}
