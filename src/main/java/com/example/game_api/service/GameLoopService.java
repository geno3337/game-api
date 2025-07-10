package com.example.game_api.service;

import com.example.game_api.model.GameRoom;
import com.example.game_api.model.Player;
import com.example.game_api.model.PlayerAction;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class GameLoopService {
    private final Map<String, GameRoom> gameRooms = new ConcurrentHashMap<>();
    private final SimpMessagingTemplate messagingTemplate;

    public GameLoopService(SimpMessagingTemplate template) {
        this.messagingTemplate = template;

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::gameLoop, 0, 33, TimeUnit.MILLISECONDS);
    }

    public String createRoom(Player player) {
        String roomId = UUID.randomUUID().toString();
        Player player2 = new Player();
        player2.setId(player.getId());
        player2.setName(player.getName());
        player2.setColor("red");
        player2.setX(100);
        player2.setY(200);
        GameRoom room = new GameRoom(roomId, player2);
        gameRooms.put(roomId, room);
//        messagingTemplate.convertAndSend("/topic/room/" + room.getId(), room.getState());
        return roomId;
    }

    public void joinRoom(String roomId, Player player) {
        Player player2 = new Player();
        player2.setId(player.getId());
        player2.setName(player.getName());
        player2.setColor("blue");
        player2.setX(600);
        player2.setY(200);
        GameRoom room = gameRooms.get(roomId);
        if (room != null) room.join(player2);
//        messagingTemplate.convertAndSend("/topic/room/" + room.getId(), room.getState());
    }

    public void handleAction(PlayerAction action) {
        System.out.println("Received action: " + action); // debug log
        GameRoom room = gameRooms.get(action.getRoomId());
        if (room != null) {
            room.applyAction(action);
//            messagingTemplate.convertAndSend("/topic/room/" + room.getId(), room.getState());
        }
    }

    private void gameLoop() {
        for (GameRoom room : gameRooms.values()) {
            room.updateBullets();
            messagingTemplate.convertAndSend("/topic/room/" + room.getId(), room.getState());
        }
    }
}

