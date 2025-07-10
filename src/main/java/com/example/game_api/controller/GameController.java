package com.example.game_api.controller;

import com.example.game_api.model.Player;
import com.example.game_api.service.GameLoopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameLoopService gameLoopService;

    public GameController(GameLoopService service) {
        this.gameLoopService = service;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRoom(@RequestBody Player player) {
        String roomId = gameLoopService.createRoom(player);
        return ResponseEntity.ok(roomId);
    }

    @PostMapping("/join/{roomId}")
    public ResponseEntity<String> joinRoom(@PathVariable String roomId, @RequestBody Player player) {
        gameLoopService.joinRoom(roomId, player);
        return ResponseEntity.ok("Joined");
    }
}

