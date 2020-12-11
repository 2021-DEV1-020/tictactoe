package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;
import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);

    private final Map<String, Player> board;

    public BoardServiceImpl() {
        this.board = new HashMap<>();
    }

    public void addValueToBoard(String position, Player player) {
        Optional.ofNullable(board.get(position)).ifPresent(value -> {
            LOGGER.info("The position is already played");
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
