package com.example.game_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bullet {

    private int x;

    private int y;

    private String owner;

    private int direction;

    private int width = 15;

    private int height = 15;

}
