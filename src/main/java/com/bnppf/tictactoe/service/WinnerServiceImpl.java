package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;
import com.bnppf.tictactoe.service.checkboxes.factory.CheckBoxSrategyFactory;
import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesStrategy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class WinnerServiceImpl implements WinnerService {

    private final BoardService boardService;
    private final CheckBoxSrategyFactory checkBoxSrategyFactory;

    public WinnerServiceImpl(BoardService boardService, CheckBoxSrategyFactory checkBoxSrategyFactory) {
        this.boardService = boardService;
        this.checkBoxSrategyFactory = checkBoxSrategyFactory;
    }

    public boolean checkForWin() {

        List<String> player1Choices = this.boardService.getBoard().entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), Player.PLAYER_1))
                .map(Map.Entry::getKey).collect(Collectors.toList());
        List<String> player2Choices = this.boardService.getBoard().entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), Player.PLAYER_2))
                .map(Map.Entry::getKey).collect(Collectors.toList());

        Predicate<BoxesStrategy> winner1Check = boxesStrategy -> boxesStrategy.checkForWin(player1Choices)
                .orElse(false);
        Predicate<BoxesStrategy> winner2Check = boxesStrategy -> boxesStrategy.checkForWin(player2Choices)
                .orElse(false);
        Predicate<BoxesStrategy> winner = winner1Check.or(winner2Check);
        return checkBoxSrategyFactory
                .getBoxesStrategies()
                .stream()
                .anyMatch(winner);
    }


}
