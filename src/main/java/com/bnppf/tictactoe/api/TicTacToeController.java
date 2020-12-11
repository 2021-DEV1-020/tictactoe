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

        boardService.addValueToBoard(position, player);
        if (winnerService.checkForWin()) {
            return Mono.just("You are the winner");
        }
        return Mono.just(position);
    }

}
