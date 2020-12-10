package com.bnppf.tictactoe.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class BoardServiceImp implements BoardService {

    private final Map<String, Player> board;
    private boolean end;

    public BoardServiceImp() {
        this.board = new HashMap<>();
    }

    public void addValueToBoard(String position, Player player) {
        Optional.ofNullable(board.get(position)).ifPresent(value -> {
            throw new IllegalArgumentException("The position is already played");
        });
        board.put(position, player);
    }

    public boolean isBoardFull() {
        return board.values().size() == 9;
    }

    public Map<String, Player> getBoard() {
        return board;
    }

}
