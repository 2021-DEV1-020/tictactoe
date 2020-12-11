package com.bnppf.tictactoe.service.checkboxes.strategies;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BoxesRowsStrategyImpl implements BoxesStrategy {

    @Override
    public Optional<Boolean> checkForWin(List<String> list) {
        return Optional.of(list.containsAll(Arrays.asList("1", "2", "3"))
                || list.containsAll(Arrays.asList("4", "5", "6"))
                || list.containsAll(Arrays.asList("7", "8", "9")));
    }
}
