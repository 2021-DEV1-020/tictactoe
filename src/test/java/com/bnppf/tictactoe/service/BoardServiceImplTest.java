package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;
import com.bnppf.tictactoe.model.TurnRequest;
import com.bnppf.tictactoe.model.TurnResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    TurnRequest turnRequest;

    @Mock
    WinnerService winnerService;

    @InjectMocks
    BoardServiceImpl boardServiceImpl;

    @DisplayName("Verify that adding data to board is successful")
    @ParameterizedTest
    @MethodSource("provideData")
    void addValueToBoard(List<Integer> positions, Player player, boolean exception, int size) {

        if (exception) {
            Assertions.assertThrows(IllegalArgumentException.class, () ->
                    positions.forEach(position -> boardServiceImpl.addValueToBoard(position, player)));
        }
        else {

            positions.forEach(position -> boardServiceImpl.addValueToBoard(position, player));
            assertThat(boardServiceImpl.getBoard())
                    .hasSize(size)
                    .containsValues(player);
        }
    }

    private static Stream<Arguments> provideData() {
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of(Arrays.asList(1,2,3,4,5,6,7,8,9), Player.X, false, 9));
        arguments.add(Arguments.of(Arrays.asList(1,2,3,4,5), Player.X, false, 5));
        arguments.add(Arguments.of(Arrays.asList(1,2,3,3), Player.O, true, 3));
        return arguments.stream();
    }

    @DisplayName("Verify that the board is full")
    @Test
    void isBoardFull() {
        Arrays.asList(1,2,6,7,8)
                .forEach(position -> boardServiceImpl.addValueToBoard(position, Player.X));
        Arrays.asList(3,4,5,9)
                .forEach(position -> boardServiceImpl.addValueToBoard(position, Player.O));

        assertThat(boardServiceImpl.isBoardFull()).isEqualTo(true);
    }

    @DisplayName("Verify that the board is not full")
    @Test
    void isBoardFullNot() {
        Arrays.asList(1,2,6)
                .forEach(position -> boardServiceImpl.addValueToBoard(position, Player.X));
        Arrays.asList(3,4,5)
                .forEach(position -> boardServiceImpl.addValueToBoard(position, Player.O));

        assertThat(boardServiceImpl.isBoardFull()).isEqualTo(false);
    }

    @DisplayName("Verify that the first value should be X")
    @Test
    void testAddValueToBoardWithMessage1() {
        turnRequest = TurnRequest.builder().player(Player.O).position(1).build();
        assertThat(boardServiceImpl.addValueToBoard(turnRequest)).isEqualTo(TurnResponse.builder().message("X always goes first").build());
    }

    @DisplayName("Verify the add of the element and the winner")
    @Test
    void testAddValueToBoard() {
        boardServiceImpl = new BoardServiceImpl(winnerService);
        when(winnerService.checkForWin(anyMap())).thenReturn(false);
        turnRequest = TurnRequest.builder().player(Player.X).position(1).build();
        assertThat(boardServiceImpl.addValueToBoard(turnRequest)).isEqualTo(TurnResponse.builder()
                .board(boardServiceImpl.getBoard())
                .message("The game is not finished yet")
                .build());
        turnRequest = TurnRequest.builder().player(Player.O).position(5).build();
        assertThat(boardServiceImpl.addValueToBoard(turnRequest)).isEqualTo(TurnResponse.builder()
                .board(boardServiceImpl.getBoard())
                .message("The game is not finished yet")
                .build());
        turnRequest = TurnRequest.builder().player(Player.X).position(2).build();
        assertThat(boardServiceImpl.addValueToBoard(turnRequest)).isEqualTo(TurnResponse.builder()
                .board(boardServiceImpl.getBoard())
                .message("The game is not finished yet")
                .build());
        turnRequest = TurnRequest.builder().player(Player.O).position(7).build();
        assertThat(boardServiceImpl.addValueToBoard(turnRequest)).isEqualTo(TurnResponse.builder()
                .board(boardServiceImpl.getBoard())
                .message("The game is not finished yet")
                .build());
        turnRequest = TurnRequest.builder().player(Player.X).position(3).build();
        assertThat(boardServiceImpl.addValueToBoard(turnRequest)).isEqualTo(TurnResponse.builder()
                .board(boardServiceImpl.getBoard())
                .message("The game is not finished yet")
                .build());
        when(winnerService.checkForWin(anyMap())).thenReturn(true);
        turnRequest = TurnRequest.builder().player(Player.X).position(9).build();
        assertThat(boardServiceImpl.addValueToBoard(turnRequest)).isEqualTo(TurnResponse.builder()
                .board(boardServiceImpl.getBoard())
                .message(String.format("Player %s is the winner", turnRequest.getPlayer()))
                .build());
    }

    @DisplayName("Verify the game is a draw when no winner")
    @Test
    void testAddValueToBoardWithBoardFullAndWinner() {
        when(winnerService.checkForWin(anyMap())).thenReturn(false);
        boardServiceImpl = new BoardServiceImpl(winnerService);
        turnRequest = TurnRequest.builder().player(Player.X).position(1).build();
        boardServiceImpl.addValueToBoard(turnRequest);
        turnRequest = TurnRequest.builder().player(Player.O).position(2).build();
        boardServiceImpl.addValueToBoard(turnRequest);
        turnRequest = TurnRequest.builder().player(Player.X).position(3).build();
        boardServiceImpl.addValueToBoard(turnRequest);
        turnRequest = TurnRequest.builder().player(Player.O).position(4).build();
        boardServiceImpl.addValueToBoard(turnRequest);
        turnRequest = TurnRequest.builder().player(Player.X).position(5).build();
        boardServiceImpl.addValueToBoard(turnRequest);
        turnRequest = TurnRequest.builder().player(Player.O).position(6).build();
        boardServiceImpl.addValueToBoard(turnRequest);
        turnRequest = TurnRequest.builder().player(Player.X).position(7).build();
        boardServiceImpl.addValueToBoard(turnRequest);
        turnRequest = TurnRequest.builder().player(Player.O).position(8).build();
        boardServiceImpl.addValueToBoard(turnRequest);
        turnRequest = TurnRequest.builder().player(Player.X).position(9).build();

        assertThat(boardServiceImpl.addValueToBoard(turnRequest)).isEqualTo(TurnResponse.builder()
                .board(boardServiceImpl.getBoard())
                .message("The game is a draw")
                .build());
    }

    @Test
    void clearBoard() {
        Arrays.asList(1,2,6)
                .forEach(position -> boardServiceImpl.addValueToBoard(position, Player.X));
        boardServiceImpl.clearBoard();
        assertThat(boardServiceImpl.getBoard()).hasSize(0);
    }
}