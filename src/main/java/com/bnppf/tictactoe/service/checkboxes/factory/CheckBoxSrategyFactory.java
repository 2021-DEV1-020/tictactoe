package com.bnppf.tictactoe.service.checkboxes.factory;

import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesStrategy;

import java.util.List;

public interface CheckBoxSrategyFactory {

    List<BoxesStrategy> getBoxesStrategies();
}
