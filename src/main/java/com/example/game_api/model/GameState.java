package com.example.game_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameState {
    private Player player1;
    private Player player2;
    private List<Bullet> bulletList = new ArrayList<>();
}
