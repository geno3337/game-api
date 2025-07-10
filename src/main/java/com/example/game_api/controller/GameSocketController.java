package com.example.game_api.controller;

import com.example.game_api.model.PlayerAction;
import com.example.game_api.service.GameLoopService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class GameSocketController {

    private final GameLoopService service;

    public GameSocketController(GameLoopService service) {
        this.service = service;
    }

    @MessageMapping("/action")
    public void receiveAction(@Payload PlayerAction action) {
        service.handleAction(action);
    }
}

