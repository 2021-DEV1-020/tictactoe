package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;

import java.util.Map;

public interface WinnerService {
    boolean checkForWin(Map<Integer, Player> board);
}
