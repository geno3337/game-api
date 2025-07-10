package com.example.game_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String id;
    private String name;
    private int x;
    private int y;
    private String color;
    private int width=60;
    private int height=140;
    private int health = 100;
    private boolean isFiring = false;
}
