package com.bnppf.tictactoe.service.checkboxes.strategies;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BoxesColumnsStrategyImpl implements BoxesStrategy {
    @Override
    public Optional<Boolean> checkForWin(List<String> list) {
        return Optional.of(list.containsAll(Arrays.asList("1", "4", "7"))
                || list.containsAll(Arrays.asList("2", "5", "8"))
                || list.containsAll(Arrays.asList("3", "6", "9")));
    }
}
