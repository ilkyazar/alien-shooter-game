package com.example.network;

import com.example.constants.MultiplayerConstants;
import java.net.ServerSocket;

/**
 * Multiplayer Server creates a server socket
 * and starts sessions continuously.
 */

public class MultiplayerServer {

    public static void start() {
        new Thread(()-> {
            try {
                ServerSocket serverSocket = new ServerSocket(MultiplayerConstants.port);
                
                System.out.println("Server started at port 8083");
                
                while(true) {
                    Session.start(serverSocket);
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}