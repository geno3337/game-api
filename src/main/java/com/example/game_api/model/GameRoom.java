package com.example.game_api.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameRoom {
    private String id;
    private Player player1;
    private Player player2;
    private GameState state;

    private List<Bullet> bulletList = new ArrayList<>();

    public GameRoom(String id, Player p1) {
        this.id = id;
        this.player1 = p1;
        this.state = new GameState();
        this.state.setPlayer1(p1);
    }

    public void join(Player p2) {
        this.player2 = p2;
        this.state.setPlayer2(p2);
    }

    public void applyAction(PlayerAction action) {
        Player player = getPlayer(action.getPlayerId());
        if (player == null) return;

        switch (action.getType()) {
            case "MOVE_UP" -> player.setY(100);
            case "MOVE_DOWN" -> player.setY(200);
            case "FIRE" -> fireBullet(player);
        }
    }

    private void fireBullet(Player player) {
        String playerNum = getPlayerNumber(player.getId());
        Bullet bullet = new Bullet();
        bullet.setDirection(playerNum.equals("player1")?1:-1);
        bullet.setOwner(playerNum);
        bullet.setX(playerNum.equals("player1")?player.getX()+60:player.getX()-15);
        bullet.setY(playerNum.equals("player1")?player.getY()+50:player.getY()+50);
        bulletList.add(bullet);
        this.state.setBulletList(bulletList);
    }

    public Player getPlayer(String id) {
        if (player1 != null && player1.getId().equals(id)) return player1;
        if (player2 != null && player2.getId().equals(id)) return player2;
        return null;
    }

    public String getPlayerNumber(String id) {
        if (player1 != null && player1.getId().equals(id)) return "player1";
        if (player2 != null && player2.getId().equals(id)) return "player2";
        return null;
    }

    public void updateBullets() {
        if (bulletList == null || bulletList.isEmpty()) return;

        bulletList.removeIf(bullet -> {
            // Move bullet
            bullet.setX(bullet.getX() + bullet.getDirection() * 10);

            // Out of bounds check
            if (bullet.getX() < 0 || bullet.getX() > 800) {
                return true; // remove bullet
            }

            // Collision check
            Player target = bullet.getOwner().equals("player1") ? player2 : player1;
            if (target != null && isColliding(bullet, target)) {
                target.setHealth(target.getHealth() - 10); // Reduce life
                return true; // remove bullet
            }

            return false; // keep bullet
        });

        this.state.setBulletList(bulletList);
    }

    private boolean isColliding(Bullet bullet, Player player) {
        int bulletWidth = 10;
        int bulletHeight = 10;
        int playerWidth = 50;
        int playerHeight = 100;

        return bullet.getX() < player.getX() + playerWidth &&
                bullet.getX() + bulletWidth > player.getX() &&
                bullet.getY() < player.getY() + playerHeight &&
                bullet.getY() + bulletHeight > player.getY();
    }



}

