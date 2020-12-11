package com.bnppf.tictactoe.service;

import com.bnppf.tictactoe.enums.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
class BoardServiceImplTest {

    @InjectMocks
    BoardServiceImpl boardServiceImpl;

    @DisplayName("Verify that adding data to board is successful")
    @ParameterizedTest
    @MethodSource("provideData")
    void addValueToBoard(List<String> positions, Player player, boolean exception, int size) {

        if (exception) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                positions.forEach(position -> boardServiceImpl.addValueToBoard(position, player));
            });
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
        arguments.add(Arguments.of(Arrays.asList("1","2","3","4","5","6","7","8","9"), Player.PLAYER_1, false, 9));
        arguments.add(Arguments.of(Arrays.asList("1","2","3","4","5"), Player.PLAYER_1, false, 5));
        arguments.add(Arguments.of(Arrays.asList("1","2","3","3"), Player.PLAYER_2, true, 3));
        return arguments.stream();
    }

    @DisplayName("Verify that the board is full")
    @Test
    void isBoardFull() {
        Arrays.asList("1","2","6","7","8")
                .forEach(position -> boardServiceImpl.addValueToBoard(position, Player.PLAYER_1));
        Arrays.asList("3","4","5","9")
                .forEach(position -> boardServiceImpl.addValueToBoard(position, Player.PLAYER_2));

        assertThat(boardServiceImpl.isBoardFull()).isEqualTo(true);
    }

    @DisplayName("Verify that the board is not full")
    @Test
    void isBoardFullNot() {
        Arrays.asList("1","2","6")
                .forEach(position -> boardServiceImpl.addValueToBoard(position, Player.PLAYER_1));
        Arrays.asList("3","4","5")
                .forEach(position -> boardServiceImpl.addValueToBoard(position, Player.PLAYER_2));

        assertThat(boardServiceImpl.isBoardFull()).isEqualTo(false);
    }

}