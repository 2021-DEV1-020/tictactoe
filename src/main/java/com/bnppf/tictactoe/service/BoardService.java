package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;

public interface BoardService {

    void addValueToBoard(String position, Player player);
    boolean isBoardFull();
}
