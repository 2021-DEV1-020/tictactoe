package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;
import com.bnppf.tictactoe.model.TurnRequest;
import com.bnppf.tictactoe.model.TurnResponse;
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
    private static final String MESSAGE_1 = "X always goes first";
    private static final String MESSAGE_2 = "Player %s is the winner";
    private static final String MESSAGE_3 = "The game is a draw";
    private static final String MESSAGE_4 = "The game is not finished yet";
    private static final String MESSAGE_5 = "The board is clear";

    private final Map<Integer, Player> board;
    private final WinnerService winnerService;

    public BoardServiceImpl(WinnerService winnerService) {
        this.winnerService = winnerService;
        this.board = new HashMap<>();
    }

    @Override
    public TurnResponse addValueToBoard(TurnRequest turnRequest) {

        if (this.board.isEmpty()
                && Player.O.equals(turnRequest.getPlayer())) {
            return TurnResponse.builder().message(MESSAGE_1).build();
        }

        LOGGER.info("add value to board  {} ", turnRequest);

        addValueToBoard(turnRequest.getPosition(), turnRequest.getPlayer());
        if (winnerService.checkForWin(board)) {
            return TurnResponse.builder()
                    .board(board)
                    .message(String.format(MESSAGE_2, turnRequest.getPlayer()))
                    .build();
        } else {
            if (isBoardFull()) {
                return TurnResponse.builder()
                        .board(board)
                        .message(MESSAGE_3)
                        .build();
            }
            return TurnResponse.builder()
                    .board(board)
                    .message(MESSAGE_4)
                    .build();
        }
    }

    @Override
    public TurnResponse clearBoard() {
        board.clear();
        return TurnResponse.builder()
                .board(board)
                .message(MESSAGE_5)
                .build();
    }

    @VisibleForTesting
    void addValueToBoard(Integer position, Player player) {

        Optional.ofNullable(board.get(position)).ifPresent(value -> {
            LOGGER.info("The position is already played");
            throw new IllegalArgumentException("The position is already played");
        });
        board.put(position, player);
    }

    @VisibleForTesting
    boolean isBoardFull() {
        return board.values().size() == 9;
    }

    public Map<Integer, Player> getBoard() {
        return board;
    }

}
