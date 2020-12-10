package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.exceptions.TechnicalException;
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
        this.end = false;
    }

    public void addValueToBoard(String position, Player player) {

    }

    public boolean isBoardFull() {
        return board.values().stream().noneMatch(Objects::isNull);
    }

    public Map<String, Player> getBoard() {
        return board;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
