package com.example.client.model;

import com.example.client.constants.GameConstants;
import com.example.client.controller.GameEngine;
import com.example.client.view.GameView;
import javafx.scene.shape.Polygon;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Animation;
import java.util.List;

import java.text.SimpleDateFormat;  
import java.util.Date;  

/**
 * This is the Bullet class.
 * This class is common for aliens and players
 * Damage and speed changes accordingly.
 * For each bullet, collision check is made in check hit function.
 * Check hit function controls if player is shot or any of the aliens are shot,
 * and updates the scores and healths accordingly.
 */

public class Bullet {
    private int damage;
    private double bulletSpeed;
    private Polygon bullet;
    private Timeline moveBullet;
    private boolean isPlayer;
    private int playerNo = 1;

    public Bullet(double x, double y, boolean isPlayer) {
        
        this.bulletSpeed = GameConstants.BULLETSPEED;
        this.isPlayer = isPlayer;
        this.bullet = new Polygon();
        if (isPlayer) {
            bullet.getPoints().addAll(GameConstants.PLAYER_BULLET_SHAPE);
            bullet.setTranslateX(x + 45);
            bullet.setTranslateY(y - 30);
            bullet.setFill(GameConstants.PLAYER_BULLET_COLOR);
            this.damage = GameConstants.PLAYER_BULLET_DAMAGE;
        }
        else {
            bullet.getPoints().addAll(GameConstants.ENEMY_BULLET_SHAPE);
            bullet.setTranslateX(x + 25);
            bullet.setTranslateY(y + 50);

            List<Alien> aliens = GameEngine.getAliens();
            if (aliens.size() > 0 && aliens.get(aliens.size() - 1).getType() == 4) {
                bullet.setTranslateX(x + 260);
                bullet.setTranslateY(y + 420);
            }
            bullet.setFill(GameConstants.ENEMY_BULLET_COLOR);
            this.damage = GameConstants.ENEMY_LEVEL1_DAMAGE;
        }
        
    }

    public void createBullet(int dir) {
        if (GameEngine.player.isAlive()) {
                moveBullet = new Timeline(
                    new KeyFrame(Duration.millis(this.bulletSpeed), e -> {
                        bullet.setTranslateY(bullet.getTranslateY() + dir);
                        checkHit();
                    })
            );
            moveBullet.setCycleCount(Animation.INDEFINITE);
            GameView.gamePane.getChildren().add(bullet);
            moveBullet.play();
        }
        
    }

    boolean inRangeX(double x, double other_x, int type) {
        int r = 60;

        if (type == 3) r = 30;

        if (type != 4) {
            if (x < other_x + r && other_x - r < x)
                return true;
            else
                return false;
        }
        else {
            if (x < other_x + 500 && other_x < x)
                return true;
            else
                return false;
        }
    }

    boolean inRangeY(double x, double other_x, int type) {
        int r = 60;

        if (type == 2) r = 20;
        if (type == 3) r = 30;
        if (type == 4) r = 100;

        if (type != 4) {
            if (x < other_x + r && other_x - r < x)
                return true;
            else
                return false;
        }
        else {
            if (x < other_x + 500 && other_x < x)
                return true;
            else
                return false;
        }
    }

    void stopMoveBullet() {
        this.moveBullet.stop();
    }

    void checkHit() {
        if (this.playerNo == 1
            && GameEngine.player2.isAlive() && GameEngine.player.isAlive()
            && !GameEngine.isWaitingPage
            && GameView.levelOver == false) {
                if (this.isPlayer) {
                    if((GameEngine.getLevel() == 5 && GameEngine.aliens.get(GameEngine.aliens.size() - 1).isAlive())
                     || GameEngine.getLevel() != 5) {
                        for(Alien alien : GameEngine.getAliens()){
                            if ( alien.isAlive() 
                            && inRangeX(this.bullet.getTranslateX(), alien.getAlienShip().getTranslateX(), alien.getType())
                            && inRangeY(this.bullet.getTranslateY(), alien.getAlienShip().getTranslateY(), alien.getType())) {
                                
                                alien.setHealth(this.damage, true);
                                System.out.println("Alien (type " + alien.getType() + ") damaged: -" + this.damage);
                                
                                this.stopMoveBullet();
                                GameView.gamePane.getChildren().remove(bullet);
            
                                int newScore = GameEngine.player.getScore() + 10;

                                GameEngine.player.setScore(newScore);
                                GameView.updateScoreLabel(newScore);
                                System.out.println("Score of player: "+ GameEngine.player.getScore());
            
                                if (alien.isAlive() == false) {
                                    int inc = 10;
                                    int type = alien.getType();
                                    if (type == 2) inc = 20;
                                    else if (type == 3) inc = 30;
                                    else if (type == 4) inc = 40;

                                    newScore = GameEngine.player.getScore() + inc;
                                    GameEngine.player.setScore(newScore);
                                    GameView.updateScoreLabel(newScore);

                                    System.out.println("ALIEN (type " + alien.getType() + ") IS DEAD!");
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                                    Date date = new Date();  
                                    System.out.println(formatter.format(date));

                                    System.out.println("Score of player: "+ GameEngine.player.getScore());
                                    
                                }
                            }
            
                        }
                    }
                }
        
                else {
                    if((GameEngine.getLevel() == 5 && GameEngine.aliens.get(GameEngine.aliens.size() - 1).isAlive())
                     || GameEngine.getLevel() != 5) {

                        if (inRangeX(this.bullet.getTranslateX(), GameEngine.player.getShip().getTranslateX(), 0)
                        && inRangeY(this.bullet.getTranslateY(), GameEngine.player.getShip().getTranslateY(), 0)) {
                            
                            GameEngine.player.setHealth(this.damage, true);
                            System.out.println("Player damaged: -" + this.damage);
                            
                            int newScore = GameEngine.player.getScore() - 10;
                            if(newScore >= 0){
                                GameEngine.player.setScore(newScore);
                                GameView.updateScoreLabel(newScore);
                            }
                            System.out.println("Score of player: "+ GameEngine.player.getScore());
        
                            GameView.gamePane.getChildren().remove(bullet);
        
                            GameView.updateHealthLabel(GameEngine.player.getHealth());
                            if (GameEngine.player.isAlive() == false) {
                                System.out.println("PLAYER IS DEAD!");
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                                Date date = new Date();  
                                System.out.println(formatter.format(date));
                                System.out.println("Score of player: "+ GameEngine.player.getScore());
                            }
                        }

                    }
                
            }
        }
        
    }

    public void setPlayerNo(int no){
        this.playerNo = no;
    }

    public int getPlayerNo(){
        return this.playerNo;
    }

}

