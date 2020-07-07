package com.example.client.model;

import com.example.client.constants.*;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import java.lang.Math;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This is the Alien class.
 * There are 4 types of aliens which have different health and damage values.
 * 3 of them appear in the single player levels and the giant alien appears on level 5.
 * They are created in the game engine
 * 
 * Aliens shoot in different speeds depending on their type.
 * They also move randomly in a Timeline.
 */

public class Alien extends Player {
    private int health;
    private double bulletSpeed;
    private boolean isDead = false;
    private Timeline moveBullet;
    private ImageView alienShip;
    private int width;
    private int height;
    private int type;
    private int dir = 1;

 
    public Alien(int type) { 
        this.type = type;  
        this.width = GameConstants.PLAYER_WIDTH;
        this.height = GameConstants.PLAYER_HEIGHT;
    }

    public int getType() {
        return this.type;
    }

    public void createAlien() {
        this.alienShip = new ImageView();
        switch(type){
            case(1):
                this.bulletSpeed = GameConstants.ENEMY_TYPE1_BULLETSPEED;
                Image image1 = new Image(UIConstants.ALIEN_1_PATH);
                this.alienShip = new ImageView(image1);
                this.health = GameConstants.ENEMY_TYPE1_HEALTH;
                break;
            case(2):
                this.bulletSpeed = GameConstants.ENEMY_TYPE2_BULLETSPEED;
                Image image2 = new Image(UIConstants.ALIEN_2_PATH);
                this.alienShip = new ImageView(image2);
                this.health = GameConstants.ENEMY_TYPE2_HEALTH;   
                break;
            case(3):
                this.bulletSpeed = GameConstants.ENEMY_TYPE3_BULLETSPEED;
                Image image3 = new Image(UIConstants.ALIEN_3_PATH);
                this.alienShip = new ImageView(image3);
                this.health = GameConstants.ENEMY_TYPE3_HEALTH;   
                break;
            case(4):
                this.bulletSpeed = GameConstants.GIANT_ENEMY_BULLETSPEED;
                Image image4 = new Image(UIConstants.GIANT_PATH);
                this.alienShip = new ImageView(image4);

                this.health = GameConstants.GIANT_ENEMY_HEALTH;  
        }

        alienShip.setFitHeight(100);
        alienShip.setFitWidth(100); 

        if (type == 4) {
            alienShip.setFitHeight(500);
            alienShip.setFitWidth(500); 
        }

        int randomX = ThreadLocalRandom.current().nextInt(200, UIConstants.WIDTH-200);
        int randomY = ThreadLocalRandom.current().nextInt(50, 500);
        alienShip.setTranslateX(randomX);
        alienShip.setTranslateY(randomY);

        if (type == 4) {
            alienShip.setTranslateX(UIConstants.WIDTH / 2);
            alienShip.setTranslateY(100);
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

    public void startMoving() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(100), e -> {

                int signx = new Random().nextBoolean() ? 1 : 2;
                double x = Math.random()*((10.0 - 5.0) + 1) + 0.1;

                double newX = signx == 2 ? this.getPosX() + x : this.getPosX() - x;

                double downBy = 1.5;
                if (this.type == 2) {
                    downBy = 1.0;
                }
                else {
                    downBy = 0.5;
                }
                
                double newY;
                if (this.type == 4) {
                    newY = this.getPosY();

                    
                    if (this.getPosX() <= 110) {
                        dir = 1;
                        //System.out.println("TO RIGHT " + this.getPosX());
                    }
                    else if (UIConstants.WIDTH - this.getPosX() < 600) {
                        dir = -1;
                        //System.out.println("TO LEFT " + this.getPosX());
                    }

                    newX = this.getPosX() + 10 * dir;

                }
                else {
                    newY = this.getPosY() + downBy;
                }
                
                if(newX > GameConstants.ALIEN_OFFSET_X && newX < UIConstants.WIDTH - GameConstants.ALIEN_OFFSET_X) // 100 < x < 900
                    this.alienShip.setTranslateX(newX);
                if(newY > GameConstants.ALIEN_OFFSET_Y && newY < UIConstants.HEIGHT - GameConstants.ALIEN_OFFSET_Y) // 200 < y < 800
                    this.alienShip.setTranslateY(newY);
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void shootBullet() {
        Bullet bullet = new Bullet(this.getPosX(), this.getPosY(), false);
        bullet.createBullet(GameConstants.BULLET_DOWN_DIRECTION);
    }

    public double getPosX() {
        return this.alienShip.getTranslateX();
    }

    public double getPosY() {
        return this.alienShip.getTranslateY();
    }

    public ImageView getAlienShip() {
        return this.alienShip;
    }

    public int getWidth(){
        return this.width ;
    }
    public int getHeight() {
        return this.height;
    }

    public void setHealth(int update, boolean isDamage) {
        if (isDamage)
            this.health -= update;
        else
            this.health += update;    
            
        if (this.health <= 0) {
            this.isDead = true;
        }

    }

    public boolean isAlive() {
        return !this.isDead;
    }

    public void setIsDead() {
        this.isDead = true;
    }

    public int getHealth() {
        return this.health;
    }


}