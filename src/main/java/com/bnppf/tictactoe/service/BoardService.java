package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;
import com.bnppf.tictactoe.model.TurnRequest;
import com.bnppf.tictactoe.model.TurnResponse;

import java.util.Map;

public interface BoardService {

    TurnResponse addValueToBoard(TurnRequest turnRequest);
    TurnResponse clearBoard();
    Map<Integer, Player> getBoard();
}
