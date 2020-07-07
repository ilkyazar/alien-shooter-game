package com.example.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Player")

/**
 * Represents a Player in the game.
 */
public class Player {
    @Id
    @Column(name = "playerId")
    private int playerId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public Player(){ }

    public Player(int playerId, String username, String password) {
        this.playerId = playerId;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the id of this Player.
     * @return this Player's id.
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Changes the id of this Player.
     * @param playerId This Player's new id.
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Gets the username of this Player.
     * @return this Player's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Changes the username of this Player.
     * @param username This Player's new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of this Player.
     * @return this Player's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Changes the password of this Player.
     * @param password This Player's new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
