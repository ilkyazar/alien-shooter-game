package com.example.model;

import com.example.constants.*;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

/**This is the Player class for multiplayer server.
 * Only used for storing score, health etc. 
*/

public class Player {
    private int health;
    private int score;
    private double bulletSpeed;
    private boolean isDead = false;
    private Timeline moveBullet;
    private int width;
    private int height;
    private ImageView spaceShip;

 
    public Player(){
        this.score = 0;
        this.health = GameConstants.PLAYER_HEALTH;
        this.bulletSpeed = GameConstants.BULLETSPEED;  
        this.width = GameConstants.PLAYER_WIDTH;
        this.height = GameConstants.PLAYER_HEIGHT;
    }

    public void setHealth(int update, boolean isDamage) {
        if (isDamage){
            if(this.health - update <= 0){
                this.health = 0;
                this.isDead = true;    
            }
            else
                this.health -= update;
        }        
        else
            this.health += update;

    }

    public void setHealthMultiplayer(int health){
        this.health = health;
    }

    public int getHealth(){
        return this.health;
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int newScore){
        this.score = newScore;
    }

    public boolean isAlive() {
        return !this.isDead;
    }

    public void setIsDead() {
        this.isDead = true;
    }


}