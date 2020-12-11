package com.bnppf.tictactoe.api;

import com.bnppf.tictactoe.enums.Player;
import com.bnppf.tictactoe.service.BoardService;
import com.bnppf.tictactoe.service.WinnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "API")
public class TicTacToeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicTacToeController.class);

    private final WinnerService winnerService;
    private final BoardService boardService;

    public TicTacToeController(WinnerService winnerService, BoardService boardService) {
        this.winnerService = winnerService;
        this.boardService = boardService;
    }

    @ApiOperation(value = "reset board")
    @DeleteMapping("/board")
    public Mono<String> cancel() {
        LOGGER.info("reset the board");
        this.boardService.getBoard().clear();
        return Mono.just("The board is reset");
    }

    @GetMapping("/board")
    @ApiOperation(value = "Get board details")
    public Flux<Map<String, Player>> getBoard() {
        LOGGER.info("get board");
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
        LOGGER.info("add value to board  {}  {}", position, player);
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
