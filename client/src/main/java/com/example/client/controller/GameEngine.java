package com.example.client.controller;

import com.example.client.view.*;
import com.example.client.model.Player;
import com.example.client.multiplayer.MultiplayerClient;
import com.example.client.constants.GameConstants;
import com.example.client.model.Alien;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Game engine class contains a player object, list of aliens in the game and current level.
 * When a new game is started by the button at the Main Menu View,
 * Game engine starts by creating a new player.
 * 
 * The player2 in the GameEngine holds the information of the second player in the multiplayer level.
 * Health, score, aliens etc. attributes of the player2 is updated according to the data
 * read by the Multiplayer Client. 
 * 
 * This class also spawns new aliens for each level.
 * When an alien is killed, the object remains on the list,
 * but isKilled bool value of Alien is set to false.
 * When the game is over, then the Aliens list is cleaned.
 * 
 * Current level value is hold in GameEngine,
 * but incremented by GameView when all aliens in GameEngine's alien list is killed.
 */

public class GameEngine {
    static public Player player;
    static public Player player2;
    public static List<Alien> aliens = new ArrayList<>();
    private static int level = 1;
    public static boolean isWaitingPage = false;

    static public ReentrantLock alienLock = new ReentrantLock();
    static public ReentrantLock playerLock = new ReentrantLock();

    public static void start() {

        player = new Player();
        player.setUserName(LoginView.userName);
        player2 = new Player();
        player2.setPlayerNo(2);
        
    }

    public static void stop(){
        aliens = new ArrayList<>();
        level = 1;
        player = new Player();
        player2 = new Player();
        player2.setPlayerNo(2);
    }

    public static void newLevel(){
        GameView.updateLevelLabel(level);
        switch(level){
            case 1:
                createAliens(GameConstants.ALIEN_1_LEVEL_1, GameConstants.ALIEN_2_LEVEL_1, GameConstants.ALIEN_3_LEVEL_1);
                break;
            case 2:
                createAliens(GameConstants.ALIEN_1_LEVEL_2, GameConstants.ALIEN_2_LEVEL_2, GameConstants.ALIEN_3_LEVEL_2);
                break;
            case 3:
                createAliens(GameConstants.ALIEN_1_LEVEL_3, GameConstants.ALIEN_2_LEVEL_3, GameConstants.ALIEN_3_LEVEL_3);
                break;
            case 4:
                createAliens(GameConstants.ALIEN_1_LEVEL_4, GameConstants.ALIEN_2_LEVEL_4, GameConstants.ALIEN_3_LEVEL_4);
                break;
            case 5:
                GameView.gamePane.getChildren().remove(player.getShip());
                createGiantAlien();
                GameView.addGiantAlien();
                isWaitingPage = true;
                player2.startShooting();
                WaitView.show();
                MultiplayerClient.connectToServer();
                
        }
    }

    public static void createAliens(int alienType1, int alienType2, int alienType3 ){
        for(int i=0; i < alienType1; i++){
            Alien alien = new Alien(1);
            alien.createAlien();
            aliens.add(alien);
        }
        for(int i=0; i < alienType2; i++){
            Alien alien = new Alien(2);
            alien.createAlien();
            aliens.add(alien);
        }
        for(int i=0; i < alienType3; i++){
            Alien alien = new Alien(3);
            alien.createAlien();
            aliens.add(alien);
        }
    }

    public static void createGiantAlien() {
        Alien alien = new Alien(4);
        alien.createAlien();
        aliens.add(alien);
    }

    public static boolean isAliensAlive(){
        for(int i=0; i<aliens.size(); i++){
            if(aliens.get(i).isAlive())
                return true;
        }
        return false;
    }

    public static List<Alien> getAliens(){
        return aliens;
    }

    public static void setLevel(int newLevel){

        level = newLevel;
    }

    public static int getLevel(){
        return level;
    }



}

