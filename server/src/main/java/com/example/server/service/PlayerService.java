package com.example.server.service;

import com.example.server.model.Player;

import java.util.List;

/**
 * This is the interface for PlayerService.
 * All the methods are implemented in PlayerServiceImpl class.
 */
public interface PlayerService {
    Player signupPlayer(Player player);
    List<Player> getAllPlayers();
    Player updatePlayer(Player player);
    Player deletePlayer(Integer id);
    Player findPlayer(String username);
    String getNamebyId(Integer id);
    boolean checkPlayer(String username);
    boolean loginPlayer(Player player);
}
