package com.bnppf.tictactoe.service;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WinnerServiceTest {

    @InjectMocks
    WinnerService winnerService;

    @BeforeEach
    void setUp() {


    }


    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void checkForWin(String input, boolean expected) {
        Assert.isTrue(winnerService.checkForWin(), "cool");
        assertEquals(expected, Strings.isBlank(input));
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not blank", false)
        );
    }
}