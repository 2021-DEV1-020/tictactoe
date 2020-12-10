package com.bnppf.tictactoe.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BoardServiceImpTest {

    @InjectMocks
    BoardServiceImp boardServiceImp;

    @ParameterizedTest
    @MethodSource("provideData")
    void addValueToBoard(List<String> positions, Player player,  boolean exception, int size) {

        if (exception) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                positions.forEach(position -> boardServiceImp.addValueToBoard(position, player));
            });
        }
        else {
            positions.forEach(position -> boardServiceImp.addValueToBoard(position, player));
            assertThat(boardServiceImp.getBoard()).hasSize(size);
        }
    }

    private static Stream<Arguments> provideData() {
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of(Arrays.asList("1","2","3","4","5","6","7","8","9"), Player.PLAYER_1, false, 9));
        arguments.add(Arguments.of(Arrays.asList("1","2","3","4","5"), Player.PLAYER_1, false, 5));
        arguments.add(Arguments.of(Arrays.asList("1","2","3","4","5","6","7","8","9","10"), Player.PLAYER_1, false, 9));
        arguments.add(Arguments.of(Arrays.asList("1","2","3","3"), Player.PLAYER_1, true, 3));
        return arguments.stream();
    }

    @Test
    void isBoardFull() {
    }


}