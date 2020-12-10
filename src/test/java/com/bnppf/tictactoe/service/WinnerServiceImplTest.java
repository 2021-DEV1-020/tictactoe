package com.bnppf.tictactoe.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WinnerServiceImplTest {

    @InjectMocks
    WinnerServiceImpl winnerServiceImpl;

    @InjectMocks
    BoardServiceImp boardServiceImp;

    @BeforeEach
    void setUp() {

    }


    @ParameterizedTest
    @MethodSource("provideData")
    void checkForWin(BoardServiceImp boardServiceImp, boolean expected) {
        Assert.isTrue(winnerServiceImpl.checkForWin(), "cool");
        assertEquals(expected, boardServiceImp.isBoardFull());
    }

    private static Stream<Arguments> provideData() {

        BoardServiceImp boardServiceImp = new BoardServiceImp();
        List<Arguments> arguments = new ArrayList<>();
        boardServiceImp.addValueToBoard("1", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("2", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("3", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("4", Player.PLAYER_2);
        boardServiceImp.addValueToBoard("5", Player.PLAYER_2);
        arguments.add(Arguments.of(boardServiceImp, true));
        boardServiceImp = new BoardServiceImp();
        boardServiceImp.addValueToBoard("1", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("5", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("9", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("2", Player.PLAYER_2);
        boardServiceImp.addValueToBoard("4", Player.PLAYER_2);
        arguments.add(Arguments.of(boardServiceImp, true));
        boardServiceImp = new BoardServiceImp();
        boardServiceImp.addValueToBoard("7", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("5", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("3", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("4", Player.PLAYER_2);
        boardServiceImp.addValueToBoard("6", Player.PLAYER_2);
        arguments.add(Arguments.of(boardServiceImp, true));
        boardServiceImp = new BoardServiceImp();
        boardServiceImp.addValueToBoard("7", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("5", Player.PLAYER_2);
        boardServiceImp.addValueToBoard("3", Player.PLAYER_1);
        boardServiceImp.addValueToBoard("4", Player.PLAYER_2);
        boardServiceImp.addValueToBoard("6", Player.PLAYER_2);
        arguments.add(Arguments.of(boardServiceImp, true));

        return arguments.stream();
    }
}