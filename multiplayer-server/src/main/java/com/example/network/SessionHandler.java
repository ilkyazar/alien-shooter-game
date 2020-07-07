package com.example.network;


import com.example.model.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import java.text.SimpleDateFormat;  
import java.util.Date;  

/**
 * The session handler class is where the communication between 2 client is handled.
 * It reads from two DataInputStream, and informs each client about the other one.
 * The location of the spaceships, scores, healths etc. is sent by this class.
 */

public class SessionHandler implements Runnable {

    private Socket player1socket;
    private Socket player2socket;

    private DataOutputStream toPlayer1;
    private DataInputStream fromPlayer1;

    private DataOutputStream toPlayer2;
    private DataInputStream fromPlayer2;

    Player player1, player2;
    int playerTotalDamage1, playerTotalDamage2;
    int player1InitialScore, player2InitialScore;

    public SessionHandler(Socket player1socket, Socket player2socket) {
        this.player1socket = player1socket;
        this.player2socket = player2socket;
        
    }


    public void run() {
        try {
            // Create data input and output streams
            fromPlayer1 = new DataInputStream(
                    player1socket.getInputStream());
            toPlayer1 = new DataOutputStream(
                    player1socket.getOutputStream());
            fromPlayer2 = new DataInputStream(
                    player2socket.getInputStream());
            toPlayer2 = new DataOutputStream(
                    player2socket.getOutputStream());

            toPlayer1.writeInt(1);
            toPlayer2.writeInt(2);

            player1 = new Player();
            player2 = new Player();
            System.out.println("Created players.");
            playerTotalDamage1 = 0;
            playerTotalDamage2 = 0;

            String player1Name = fromPlayer1.readUTF();
            String player2Name = fromPlayer2.readUTF();
            toPlayer1.writeUTF(player2Name);
            toPlayer2.writeUTF(player1Name);
            
            while(true){
                int player1score = fromPlayer1.readInt();
                player1.setScore(player1score);
                int player1health = fromPlayer1.readInt();
                player1.setHealthMultiplayer(player1health); 
                int player2score = fromPlayer2.readInt();
                player2.setScore(player2score);
                int player2health = fromPlayer2.readInt();
                player2.setHealthMultiplayer(player2health);

                updateLabels();

                //updating positions
                double player1posx = fromPlayer1.readDouble();
                double player1posy = fromPlayer1.readDouble();
                double player2posx = fromPlayer2.readDouble();
                double player2posy = fromPlayer2.readDouble();

                System.out.println("Player 1 pos X: " + player1posx);
                System.out.println("Player 1 pos Y: " + player1posy);
                System.out.println("Player 2 pos X: " + player2posx);
                System.out.println("Player 2 pos Y: " + player2posy);

                toPlayer1.writeDouble(player2posx);
                toPlayer1.writeDouble(player2posy);
                toPlayer2.writeDouble(player1posx);
                toPlayer2.writeDouble(player1posy);

                //alien damages
                int damage1 = fromPlayer1.readInt();
                int damage2 = fromPlayer2.readInt();
                toPlayer1.writeInt(damage2);
                toPlayer2.writeInt(damage1);
                
                System.out.println("Player1 damaged giant alien by: " + damage1);
                System.out.println("Player2 damaged giant alien by: " + damage2);

                playerTotalDamage1 += damage1;
                playerTotalDamage2 += damage2;
                System.out.println("TOTAL: Player1 damaged giant alien by: " + playerTotalDamage1);
                System.out.println("TOTAL: Player2 damaged giant alien by: " + playerTotalDamage2);

                //isGameOver
                boolean gameOver1 = fromPlayer1.readBoolean();
                boolean gameOver2 = fromPlayer2.readBoolean();
                toPlayer1.writeBoolean(gameOver2);
                toPlayer2.writeBoolean(gameOver1);
                
                boolean isAlienAlive1 = fromPlayer1.readBoolean();
                boolean isAlienAlive2 = fromPlayer2.readBoolean();
                toPlayer1.writeBoolean(isAlienAlive2);
                toPlayer2.writeBoolean(isAlienAlive1);
                
                if (gameOver1 || gameOver2 || !isAlienAlive1 || !isAlienAlive2) {

                    System.out.println("\nGAME OVER");
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();  
                    System.out.println(formatter.format(date));
                    
                    int player1Score = fromPlayer1.readInt();
                    int player2Score = fromPlayer2.readInt();
                    player1InitialScore = fromPlayer1.readInt();
                    player2InitialScore = fromPlayer2.readInt();

                    toPlayer1.writeInt(player2Score);
                    toPlayer2.writeInt(player1Score);
                    toPlayer1.writeInt(player2InitialScore);
                    toPlayer2.writeInt(player1InitialScore);

                    System.out.println("Initial score of player1 was: " + player1InitialScore);
                    System.out.println("Initial score of player2 was: " + player2InitialScore);
                    System.out.println("Player 1 total score : " + player1Score);
                    System.out.println("Player 2 total score : " + player2Score);
                    int total = player1Score + player2Score;
                    System.out.println("Total score: " + total);
                    break;
                }


            }

        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateLabels(){
        try{
            toPlayer1.writeInt(player2.getScore());
            toPlayer1.writeInt(player2.getHealth());
            toPlayer2.writeInt(player1.getScore());
            toPlayer2.writeInt(player1.getHealth());
        }
        
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    
}