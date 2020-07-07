package com.example.client.multiplayer;

import com.example.client.controller.GameEngine;
import com.example.client.constants.GameConstants;
import com.example.client.constants.MultiplayerConstants;
import com.example.client.constants.UIConstants;
import javafx.application.Platform;

import com.example.client.view.GameView;
import com.example.client.model.Alien;
import com.example.client.view.WaitView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;  
import java.util.Date; 

/**
 * This is the MultiplayerClient class. Connection with multiplayer server 
 * is established through a socket and data is sent and received through two DataInputStreams.
 * This is the client side of the multiplayer level. Two clients (player 1 and player 2)
 * communicate with this class through multiplayer server.
 * 
 * When any of the players are dead or the alien is dead, the multiplayer level finishes
 * and finishGame function of the GameView is called. Then, the game proceeds to the Results page.
 */
public class MultiplayerClient {

    private static DataInputStream fromServer;
    private static DataOutputStream toServer;

    static List<Alien> aliens = new ArrayList<>();
    public static int total;

    public static void connectToServer() {
        try {
            Socket socket = new Socket(MultiplayerConstants.host, MultiplayerConstants.port);
            System.out.println("Connected to " + socket.getRemoteSocketAddress());
            
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

            WaitView.changeWaitingStatus(UIConstants.SERVER_CONNECTED);
            runCycle();

        } catch (Exception ex) {
            ex.printStackTrace();
            WaitView.changeWaitingStatus(UIConstants.CONNECTION_FAIL);
        }
    }


    private static void runCycle() {
        new Thread(() -> {
            try {
                
                int playerNo = fromServer.readInt();
                System.out.println("\n LEVEL 5 STARTED!!");
                System.out.println(" Player no: " + playerNo + "\n");

                String player1Name = GameEngine.player.getUserName();
                toServer.writeUTF(player1Name);
                String player2Name = fromServer.readUTF();
                GameEngine.player2.setUserName(player2Name);

                int myInitialScore = GameEngine.player.getScore();
                //toServer.writeInt(GameEngine.player.getScore());
                //int othersInitialScore = fromServer.readInt();
                
                Platform.runLater(() -> WaitView.changeWaitingStatus(UIConstants.MATCH_FOUND + player2Name));
                Thread.sleep(1000);
                Platform.runLater(() -> GameView.show());
                GameEngine.isWaitingPage = false;
                
                List<Alien> aliens = GameEngine.getAliens();
                aliens.get(aliens.size()-1).startMoving();
                int alienHealth = GameEngine.aliens.get(GameEngine.aliens.size()-1).getHealth();

                
                while(true) {

                    toServer.writeInt(GameEngine.player.getScore());
                    toServer.writeInt(GameEngine.player.getHealth());

                    GameEngine.player2.setScore(fromServer.readInt());
                    GameEngine.player2.setHealthMultiplayer(fromServer.readInt());

                    updateMultiplayerLabels();

                    // positions
                    double playerposX = GameEngine.player.getPosX();
                    double playerposY = GameEngine.player.getPosY();
                    toServer.writeDouble(playerposX);
                    toServer.writeDouble(playerposY);

                    double player2posX = fromServer.readDouble();
                    double player2posY = fromServer.readDouble();

                    Platform.runLater(() -> {
                        GameEngine.player2.setPosX(player2posX);
                        GameEngine.player2.setPosY(player2posY);
                        
                    });

                    // alien damages
                    int damage = alienHealth - aliens.get(aliens.size()-1).getHealth();
                    toServer.writeInt(damage);
                    int damage2 = fromServer.readInt();
                    alienHealth = alienHealth - (damage + damage2);
                    
                    aliens.get(aliens.size()-1).setHealth(damage2, true);

                    // isGameOver
                    boolean isGameOver = !GameEngine.player.isAlive();
                    toServer.writeBoolean(isGameOver);

                    boolean isOthersGameOver = fromServer.readBoolean();
                    
                    if(isOthersGameOver){
                        GameEngine.player2.setIsDead();
                        GameEngine.aliens.get(aliens.size()-1).setIsDead();
                    }
                    
                    boolean isAlienAlive = GameEngine.aliens.get(GameEngine.aliens.size()-1).isAlive();
                    toServer.writeBoolean(isAlienAlive);
                    boolean isOthersAlienAlive = fromServer.readBoolean();


                    if (isGameOver || isOthersGameOver|| !isAlienAlive || !isOthersAlienAlive) {
                                GameView.levelOver = true;
                                
                                toServer.writeInt(GameEngine.player.getScore());
                                toServer.writeInt(myInitialScore);

                                int othersScore = fromServer.readInt();
                                GameEngine.player2.setScore(othersScore);
                                int othersInitialScore = fromServer.readInt();

                                System.out.println("\nGAME OVER");
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                                Date date = new Date();  
                                System.out.println(formatter.format(date));

                                int myScore = GameEngine.player.getScore();
                                if (myScore > othersScore) {
                                    GameEngine.player.setScore(myScore + GameConstants.GIANT_BONUS_POINTS);
                                    GameEngine.player.setBonus();
                                }
                                else if (myScore == othersScore) {
                                    ;
                                }
                                else {
                                    GameEngine.player2.setScore(othersScore + GameConstants.GIANT_BONUS_POINTS);
                                    GameEngine.player2.setBonus();
                                    othersScore += GameConstants.GIANT_BONUS_POINTS;
                                }

                                System.out.println("My score: " + GameEngine.player.getScore());
                                System.out.println("Other player's score: " + othersScore);
                                total = GameEngine.player.getScore() + othersScore;
                                System.out.println("Total score: " + total);
                                
                                Platform.runLater(() -> {
                                    GameView.finishGame(true);
                                });
                                
                                break;
                    }
                        

                }

                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        
    }

    private static void updateMultiplayerLabels(){
        Platform.runLater(() -> {
            GameView.updateHealth2Label(GameEngine.player2.getHealth());
            GameView.updateScore2Label(GameEngine.player2.getScore());
        });
    }

}