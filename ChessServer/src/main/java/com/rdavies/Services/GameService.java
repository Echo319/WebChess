package com.rdavies.Services;

import com.rdavies.game.GameState;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final ConcurrentHashMap<String, GameState> activeGames = new ConcurrentHashMap<>();

    public GameState getOrCreateGame(String gameId) {
        return activeGames.computeIfAbsent(gameId, GameState::new);
    }

    public void endGame(String gameId) {
        activeGames.remove(gameId);
    }

}
