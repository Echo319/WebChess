package com.rdavies.controllers;

import com.rdavies.Services.GameService;
import com.rdavies.game.GameState;
import com.rdavies.messages.MoveMessage;
import com.rdavies.messages.MoveResponse;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final GameService gameService;

    public WebSocketController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/game/{gameId}/move")
    @SendTo("/topic/game/{gameId}")
    public MoveResponse GameMove(@DestinationVariable String gameId, @Payload MoveMessage move) {

        GameState gameState = gameService.getOrCreateGame(gameId);

        if(!gameState.handleMove(move)) {
            return new MoveResponse(false, gameState.getFenString());
        }

        return new MoveResponse(true, gameState.getFenString());
    }
}