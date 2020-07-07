package com.example.client.model;

import com.example.client.constants.*;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**This is the Player class.
 * Player has only one health and damage value
 * These values updated in this class function according to getting hit or shooting some aliens
 * They are created in the game engine
 * Also the Player has a username attribute which is set in the LoginView.
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
    private int playerNo = 1;
    private String userName;
    private boolean gotBonus = false;

 
    public Player(){
        this.score = 0;
        this.health = GameConstants.PLAYER_HEALTH;
        this.bulletSpeed = GameConstants.BULLETSPEED;  
        this.width = GameConstants.PLAYER_WIDTH;
        this.height = GameConstants.PLAYER_HEIGHT;
        this.gotBonus = false;
        createPlayer();
    }


    public void createPlayer() {

        try {
            Image image = new Image(UIConstants.SPACESHIP_PATH);
            this.spaceShip = new ImageView(image);
            spaceShip.setFitHeight(100);
            spaceShip.setFitWidth(100);
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    
    }

    public void startShooting() {
        this.moveBullet = new Timeline();
        moveBullet.setCycleCount(Timeline.INDEFINITE);
        KeyFrame movePlane = new KeyFrame(Duration.millis(this.bulletSpeed),
            event -> {
                if(!isDead){
                    shootBullet();
                }
            }
        );
        moveBullet.getKeyFrames().add(movePlane);
        moveBullet.play();
    }

    public void shootBullet(){
        Bullet bullet = new Bullet(this.getPosX(), this.getPosY(), true);
        bullet.setPlayerNo(this.playerNo);
        bullet.createBullet(GameConstants.BULLET_UP_DIRECTION);
    }

    public double getPosX(){
        return this.spaceShip.getTranslateX();
    }

    public double getPosY(){
        return this.spaceShip.getTranslateY();
    }

    public ImageView getShip(){
        return this.spaceShip;
    }

    public void setPosX(double value){
        this.spaceShip.setTranslateX(value);
    }

    public void setPosY(double value){
        this.spaceShip.setTranslateY(value);
    }

    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
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

    public void setPlayerNo(int no){
        this.playerNo = no;
    }

    public int getPlayerNo(){
        return this.playerNo;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String name){
        this.userName = name;
    }

    public boolean gotBonus(){
        return this.gotBonus;
    }

    public void setBonus(){
        this.gotBonus = true;
    }


}