package com.bnppf.tictactoe.service.checkboxes.strategies;

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
class BoxesRowsStrategyImplTest {

    @InjectMocks
    BoxesRowsStrategyImpl boxesRowsStrategy;

    @ParameterizedTest
    @DisplayName("Verify that which player is the winner")
    @MethodSource("provideData")
    void checkForWin(List<Integer> list, boolean expected) {
        assertThat(boxesRowsStrategy.checkForWin(list)
                .orElse(false))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> provideData() {
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of(Arrays.asList(1, 2, 3), true));
        arguments.add(Arguments.of(Arrays.asList(1, 5), false));
        arguments.add(Arguments.of(Arrays.asList(7, 8, 9), true));
        arguments.add(Arguments.of(Arrays.asList(6, 4, 5), true));
        arguments.add(Arguments.of(Arrays.asList(3, 6, 9), false));
        arguments.add(Arguments.of(Arrays.asList(3, 5, 9), false));
        return arguments.stream();
    }
}