package com.example.game_api.model;

import lombok.Data;

@Data
public class PlayerAction {
    private String roomId;
    private String playerId;
    private String type; // MOVE_UP, MOVE_DOWN, FIRE
}
