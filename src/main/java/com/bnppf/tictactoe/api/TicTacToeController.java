package com.bnppf.tictactoe.api;

import com.bnppf.tictactoe.enums.Player;
import com.bnppf.tictactoe.holders.ApiPaths;
import com.bnppf.tictactoe.model.TurnRequest;
import com.bnppf.tictactoe.model.TurnResponse;
import com.bnppf.tictactoe.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiPaths.V1)
@Api(tags = "API")
public class TicTacToeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicTacToeController.class);

    private final BoardService boardService;

    public TicTacToeController(BoardService boardService) {
        this.boardService = boardService;
    }

    @DeleteMapping(ApiPaths.BOARD)
    @ApiOperation(value = "Clear board")
    public Mono<TurnResponse> cancel() {

        LOGGER.info("reset the board");
        return Mono.just(this.boardService.clearBoard());
    }

    @PostMapping(ApiPaths.BOARD)
    @ApiOperation(value = "Add position to board")
    public Mono<TurnResponse> addToBoard(@RequestBody @Valid TurnRequest request,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return Mono.just(TurnResponse.builder().message(result.getFieldErrors()
                    .stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "))).build());
        }
        LOGGER.info("add value to board  {} ", request);
        return Mono.just(boardService.addValueToBoard(request));
    }

    @GetMapping("/board")
    @ApiOperation(value = "Get board details")
    public Flux<Map<Integer, Player>> getBoard() {
        LOGGER.info("get board");
        return Flux.just(boardService.getBoard());
    }
}
