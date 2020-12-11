package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;
import com.bnppf.tictactoe.service.checkboxes.factory.CheckBoxSrategyFactory;
import com.bnppf.tictactoe.service.checkboxes.factory.CheckBoxSrategyFactoryImpl;
import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesColumnsStrategyImpl;
import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesDiagonalsStrategyImpl;
import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesRowsStrategyImpl;
import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
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
        boardServiceImpl.addValueToBoard("1", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("2", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("3", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("4", Player.PLAYER_2);
        boardServiceImpl.addValueToBoard("5", Player.PLAYER_2);
        arguments.add(Arguments.of(boardServiceImpl, true));
        boardServiceImpl = new BoardServiceImpl();
        boardServiceImpl.addValueToBoard("1", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("5", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("9", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("2", Player.PLAYER_2);
        boardServiceImpl.addValueToBoard("4", Player.PLAYER_2);
        arguments.add(Arguments.of(boardServiceImpl, true));
        boardServiceImpl = new BoardServiceImpl();
        boardServiceImpl.addValueToBoard("7", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("5", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("3", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("4", Player.PLAYER_2);
        boardServiceImpl.addValueToBoard("6", Player.PLAYER_2);
        arguments.add(Arguments.of(boardServiceImpl, true));
        boardServiceImpl = new BoardServiceImpl();
        boardServiceImpl.addValueToBoard("7", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("5", Player.PLAYER_2);
        boardServiceImpl.addValueToBoard("3", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("4", Player.PLAYER_2);
        boardServiceImpl.addValueToBoard("6", Player.PLAYER_2);
        arguments.add(Arguments.of(boardServiceImpl, true));
        boardServiceImpl = new BoardServiceImpl();
        boardServiceImpl.addValueToBoard("1", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("3", Player.PLAYER_2);
        boardServiceImpl.addValueToBoard("2", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("4", Player.PLAYER_2);
        boardServiceImpl.addValueToBoard("6", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("5", Player.PLAYER_2);
        boardServiceImpl.addValueToBoard("7", Player.PLAYER_1);
        boardServiceImpl.addValueToBoard("9", Player.PLAYER_2);
        boardServiceImpl.addValueToBoard("8", Player.PLAYER_1);
        arguments.add(Arguments.of(boardServiceImpl, false));

        return arguments.stream();
    }
}