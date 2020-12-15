package com.bnppf.tictactoe.model;

import com.bnppf.tictactoe.enums.Player;
import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@ToString
public class TurnRequest {


    Player player;

    @Max(value = 9, message = "Position should be less than 9")
    @Min(value = 1, message = "Position should be bigger than 1")
    Integer position;

}

