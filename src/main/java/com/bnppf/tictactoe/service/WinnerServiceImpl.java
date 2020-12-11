package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;
import com.bnppf.tictactoe.service.checkboxes.factory.CheckBoxSrategyFactory;
import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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

        return checkWinForPlayerX() || checkWinForPlayerO();
    }

    private boolean checkWinForPlayerX() {
        List<String> player1Choices = this.boardService.getBoard().entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), Player.X))
                .map(Map.Entry::getKey).collect(Collectors.toList());
        Predicate<BoxesStrategy> winner1Check = boxesStrategy -> boxesStrategy.checkForWin(player1Choices)
                .orElse(false);
        return checkBoxSrategyFactory
                .getBoxesStrategies()
                .stream().anyMatch(winner1Check);
    }

    private boolean checkWinForPlayerO() {
        List<String> player2Choices = this.boardService.getBoard().entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), Player.O))
                .map(Map.Entry::getKey).collect(Collectors.toList());
        Predicate<BoxesStrategy> winner2Check = boxesStrategy -> boxesStrategy.checkForWin(player2Choices)
                .orElse(false);
        return checkBoxSrategyFactory
                .getBoxesStrategies()
                .stream().anyMatch(winner2Check);
    }

}
