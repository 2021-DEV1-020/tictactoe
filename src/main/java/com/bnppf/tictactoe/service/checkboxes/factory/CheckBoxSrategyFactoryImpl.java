package com.bnppf.tictactoe.service.checkboxes.factory;

import com.bnppf.tictactoe.service.checkboxes.strategies.BoxesStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckBoxSrategyFactoryImpl implements CheckBoxSrategyFactory {

    List<BoxesStrategy> boxesStrategies;

    public CheckBoxSrategyFactoryImpl(List<BoxesStrategy> boxesStrategies) {
        this.boxesStrategies = boxesStrategies;
    }

    @Override
    public List<BoxesStrategy> getBoxesStrategies() {
        return boxesStrategies;
    }
}
