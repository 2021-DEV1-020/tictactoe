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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    void checkForWin(BoardService boardService, boolean expected) {

        winnerService = new WinnerServiceImpl(boardService, checkBoxSrategyFactory);
        assertThat(winnerService.checkForWin()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideData() {

        BoardService boardServiceImpl = new BoardServiceImpl();
        List<Arguments> arguments = new ArrayList<>();
        boardServiceImpl.addValueToBoard("1", Player.X);
        boardServiceImpl.addValueToBoard("2", Player.X);
        boardServiceImpl.addValueToBoard("3", Player.X);
        boardServiceImpl.addValueToBoard("4", Player.O);
        boardServiceImpl.addValueToBoard("5", Player.O);
        arguments.add(Arguments.of(boardServiceImpl, true));
        boardServiceImpl = new BoardServiceImpl();
        boardServiceImpl.addValueToBoard("1", Player.X);
        boardServiceImpl.addValueToBoard("5", Player.X);
        boardServiceImpl.addValueToBoard("9", Player.X);
        boardServiceImpl.addValueToBoard("2", Player.O);
        boardServiceImpl.addValueToBoard("4", Player.O);
        arguments.add(Arguments.of(boardServiceImpl, true));
        boardServiceImpl = new BoardServiceImpl();
        boardServiceImpl.addValueToBoard("7", Player.X);
        boardServiceImpl.addValueToBoard("5", Player.X);
        boardServiceImpl.addValueToBoard("3", Player.X);
        boardServiceImpl.addValueToBoard("4", Player.O);
        boardServiceImpl.addValueToBoard("6", Player.O);
        arguments.add(Arguments.of(boardServiceImpl, true));
        boardServiceImpl = new BoardServiceImpl();
        boardServiceImpl.addValueToBoard("7", Player.X);
        boardServiceImpl.addValueToBoard("5", Player.O);
        boardServiceImpl.addValueToBoard("3", Player.X);
        boardServiceImpl.addValueToBoard("4", Player.O);
        boardServiceImpl.addValueToBoard("6", Player.O);
        arguments.add(Arguments.of(boardServiceImpl, true));
        boardServiceImpl = new BoardServiceImpl();
        boardServiceImpl.addValueToBoard("1", Player.X);
        boardServiceImpl.addValueToBoard("3", Player.O);
        boardServiceImpl.addValueToBoard("2", Player.X);
        boardServiceImpl.addValueToBoard("4", Player.O);
        boardServiceImpl.addValueToBoard("6", Player.X);
        boardServiceImpl.addValueToBoard("5", Player.O);
        boardServiceImpl.addValueToBoard("7", Player.X);
        boardServiceImpl.addValueToBoard("9", Player.O);
        boardServiceImpl.addValueToBoard("8", Player.X);
        arguments.add(Arguments.of(boardServiceImpl, false));

        return arguments.stream();
    }
}