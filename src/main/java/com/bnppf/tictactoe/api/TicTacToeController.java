package com.bnppf.tictactoe.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "API")
public class TicTacToeController {

    @GetMapping("/hello")
    @ApiOperation(value = "Hello world")
    public Mono<String> test() {
        return Mono.just("hello world");
    }
}
