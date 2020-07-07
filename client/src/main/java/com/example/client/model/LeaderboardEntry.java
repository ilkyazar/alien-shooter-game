package com.example.client.model;

/**
 * This is the LeaderboardEntry class.
 * It shows username and score of the player and the date of the game in the leaderboard page
 */

public class LeaderboardEntry {
    private Integer playerId;
    private String username;
    private Integer score;
    private String date;

    public LeaderboardEntry(Integer playerId, String username, Integer score, String date){
        this.playerId = playerId;
        this.username = username;
        this.score = score;
        this.date = date;
    }

    public void setPlayerId(int playerId){
        this.playerId = playerId;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setDate(String date){
        this.date = date;
    }

    public int getPlayerId(){
        return this.playerId;
    }

    public String getUsername(){
        return this.username;
    }

    public int getScore(){
        return this.score;
    }

    public String getDate(){
        return this.date;
    }
}