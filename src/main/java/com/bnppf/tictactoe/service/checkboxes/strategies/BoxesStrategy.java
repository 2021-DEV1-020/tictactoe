package com.bnppf.tictactoe.service.checkboxes.strategies;

import java.util.List;
import java.util.Optional;

public interface BoxesStrategy {

    Optional<Boolean> checkForWin(List<Integer> list);
}
