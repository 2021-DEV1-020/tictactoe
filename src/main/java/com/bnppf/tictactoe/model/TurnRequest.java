package com.bnppf.tictactoe.model;

import com.bnppf.tictactoe.enums.Player;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Value
@ToString
public class TurnRequest {

    @NotBlank(message = "Player cannot be null")
    Player player;

    @Max(9)
    @Min(1)
    @NotNull
    Integer position;

}

