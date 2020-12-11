package com.bnppf.tictactoe.api;

import com.bnppf.tictactoe.enums.Player;
import com.bnppf.tictactoe.service.BoardService;
import com.bnppf.tictactoe.service.WinnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "API")
public class TicTacToeController {

    private final WinnerService winnerService;
    private final BoardService boardService;

    public TicTacToeController(WinnerService winnerService, BoardService boardService) {
        this.winnerService = winnerService;
        this.boardService = boardService;
    }

    @GetMapping("/board")
    @ApiOperation(value = "Get board details")
    public Flux<Map<String, Player>> getBoard() {
        return Flux.just(boardService.getBoard());
    }

    @PostMapping("/board/{position}")
    @ApiOperation(value = "Add position to board")
    public Mono<String> addToBoard(@PathVariable(value = "position") String  position,
                                                 @RequestParam(value = "player") Player player) {

        if (boardService.getBoard().isEmpty()
                && Player.O.equals(player)) {
            return Mono.just("X always goes first");
        }
        boardService.addValueToBoard(position, player);
        if (winnerService.checkForWin()) {
            return Mono.just(String.format("You player %s is the winner", player.getValue()));
        }
        else if (!winnerService.checkForWin()
                    && boardService.getBoard().size() == 9) {
            return Mono.just("The game is a draw");
        }
        return Mono.just(String.format("The position %s is added to the board", position));
    }

}
