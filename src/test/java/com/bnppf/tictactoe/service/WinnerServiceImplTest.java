package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;
import com.bnppf.tictactoe.service.checkboxes.factory.CheckBoxSrategyFactory;
import com.bnppf.tictactoe.service.checkboxes.factory.CheckBoxSrategyFactoryImpl;
import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesColumnsStrategyImpl;
import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesDiagonalsStrategyImpl;
import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesRowsStrategyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class WinnerServiceImplTest {

    WinnerServiceImpl winnerService;
    CheckBoxSrategyFactory checkBoxSrategyFactory;

    @BeforeEach
    void setUp() {
        checkBoxSrategyFactory = new CheckBoxSrategyFactoryImpl(Arrays.asList(new BoxesColumnsStrategyImpl(),
                new BoxesRowsStrategyImpl(),
                new BoxesDiagonalsStrategyImpl()));
    }

    @ParameterizedTest
    @DisplayName("Verify that which player is the winner")
    @MethodSource("provideData")
    void checkForWin(Map<Integer, Player> board, boolean expected) {

        winnerService = new WinnerServiceImpl(checkBoxSrategyFactory);
        assertThat(winnerService.checkForWin(board)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideData() {

        Map<Integer, Player> board = new HashMap<>();
        List<Arguments> arguments = new ArrayList<>();
        board.put(1, Player.X);
        board.put(2, Player.X);
        board.put(3, Player.X);
        board.put(4, Player.O);
        board.put(5, Player.O);
        arguments.add(Arguments.of(board, true));
        board = new HashMap<>();
        board.put(1, Player.X);
        board.put(5, Player.X);
        board.put(9, Player.X);
        board.put(2, Player.O);
        board.put(4, Player.O);
        arguments.add(Arguments.of(board, true));
        board = new HashMap<>();
        board.put(7, Player.X);
        board.put(5, Player.X);
        board.put(3, Player.X);
        board.put(4, Player.O);
        board.put(6, Player.O);
        arguments.add(Arguments.of(board, true));
        board = new HashMap<>();
        board.put(7, Player.X);
        board.put(5, Player.O);
        board.put(3, Player.X);
        board.put(4, Player.O);
        board.put(6, Player.O);
        arguments.add(Arguments.of(board, true));
        board = new HashMap<>();
        board.put(1, Player.X);
        board.put(3, Player.O);
        board.put(2, Player.X);
        board.put(4, Player.O);
        board.put(6, Player.X);
        board.put(5, Player.O);
        board.put(7, Player.X);
        board.put(9, Player.O);
        board.put(8, Player.X);
        arguments.add(Arguments.of(board, false));

        return arguments.stream();
    }
}