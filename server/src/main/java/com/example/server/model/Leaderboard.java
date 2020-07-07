package com.example.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Leaderboard")

/**
 * Represents a Leaderboard in the game.
 */
public class Leaderboard {
    @Id
    @Column(name = "leaderboardId")
    private int leaderboardId;

    @Column(name = "playerId")
    private int playerId;

    @Column(name = "score")
    private int score;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String date;

    public Leaderboard(){

    }

    public Leaderboard(int leaderboardId, int playerId, int score, String date) {
        this.leaderboardId = leaderboardId;
        this.playerId = playerId;
        this.score = score;
        this.date = date;
    }

    /**
     * Gets the id of this Leaderboard.
     * @return this Leaderboard's id.
     */
    public int getLeaderboardId() {
        return leaderboardId;
    }

    /**
     * Changes the id of this Leaderboard.
     * @param bid This Leaderboard's new id.
     */
    public void setLeaderboardId(int bid) {
        this.leaderboardId = bid;
    }

    /**
     * Gets the playerId of Player recorded in this Leaderboard.
     * @return Player's playerId.
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Changes the pid of Player recorded in this Leaderboard.
     * @param pid Player's new id.
     */
    public void setPlayerId(int pid) {
        this.playerId = pid;
    }

    /**
     * Gets the score of Player.
     * @return Player's score recorded in this Leaderboard.
     */
    public int getScore() {
        return score;
    }

    /**
     * Changes the score of Player recorded in this Leaderboard.
     * @param score Player's new score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the date of this Leaderboard.
     * @return Leaderboard's score date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Changes the date of this Leaderboard.
     * @param date Leaderboard's new date.
     */
    public void setDate(String date) {
        this.date = date;
    }
}
