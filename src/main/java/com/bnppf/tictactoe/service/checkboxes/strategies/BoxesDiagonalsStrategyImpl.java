package com.bnppf.tictactoe.service.checkboxes.strategies;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BoxesDiagonalsStrategyImpl implements BoxesStrategy {

    @Override
    public Optional<Boolean> checkForWin(List<Integer> list) {
        return Optional.of(list.containsAll(Arrays.asList(1, 5, 9))
                || list.containsAll(Arrays.asList(3, 5, 7)));
    }
}
