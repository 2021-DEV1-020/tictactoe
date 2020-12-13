package com.bnppf.tictactoe.model;

import com.bnppf.tictactoe.enums.Player;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.util.Map;

@Builder
@ToString
@Value
public class TurnResponse {

    Player winner;
    Map<Integer, Player> board;
    String message;

}
