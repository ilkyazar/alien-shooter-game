package com.example.network;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is the Session class.
 * It creates a session and accepts connections on serversocket.
 * When 2 players connect to a session, a thread for them is started in sessionhandler.
 */

public class Session {

    private static int session = 1;

    public static void start(ServerSocket serverSocket) {

        try {
            System.out.println("Waiting for players.. Session no: " + session);

            Socket player1 = serverSocket.accept();

            System.out.println("Player 1 joined session " + session);
            System.out.println("Player 1: " + player1.getInetAddress().getHostAddress());

            Socket player2 = serverSocket.accept();

            System.out.println("Player 2 joined session " + session);
            System.out.println("Player 2: " + player2.getInetAddress().getHostAddress());

            System.out.println("Session thread started, session no: " + session++);

            new Thread(new SessionHandler(player1, player2)).start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}