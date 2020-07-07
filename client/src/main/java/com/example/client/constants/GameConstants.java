package com.example.client.constants;

import javafx.scene.paint.Color;

/**
 * Constants related to game view (ie. player, alien, bullet)
 */

public class GameConstants {
    public static final int PLAYER_HEALTH = 100;
    public static final int PLAYER_WIDTH = 40;
    public static final int PLAYER_HEIGHT = 60;
    public static final double BULLETSPEED = 100;

    public static final int GIANT_BONUS_POINTS = 500;

    public static final int ENEMY_TYPE1_HEALTH = 30;
    public static final int ENEMY_TYPE2_HEALTH = 35;
    public static final int ENEMY_TYPE3_HEALTH = 40;
    public static final int GIANT_ENEMY_HEALTH = 500;

    public static final double ENEMY_TYPE1_BULLETSPEED = 1000;
    public static final double ENEMY_TYPE2_BULLETSPEED = 1500;
    public static final double ENEMY_TYPE3_BULLETSPEED = 2000;
    public static final double GIANT_ENEMY_BULLETSPEED = 500;
    
    public static final Color ENEMY_TYPE1_COLOR = Color.GREEN;
    public static final Color ENEMY_TYPE2_COLOR = Color.LIME;
    public static final Color ENEMY_TYPE3_COLOR = Color.CHARTREUSE;
    public static final Color GIANT_ENEMY_COLOR = Color.BLUE;

    public static final Color PLAYER_BULLET_COLOR = Color.GRAY;
    public static final Color ENEMY_BULLET_COLOR = Color.GREENYELLOW;


    public static final int PLAYER_BULLET_DAMAGE = 10;
    public static final int BULLET_UP_DIRECTION = -70;
    public static final int BULLET_DOWN_DIRECTION = 100;

    public static Double[] PLAYER_SHIP_SHAPE = new Double[]{        
        0.0,20.0, 20.0,0.0, 40.0,20.0, 40.0,60.0, 30.0,50.0, 10.0,50.0, 0.0,60.0};
    
    public static Double[] PLAYER_BULLET_SHAPE = new Double[]{0.0,20.0, 5.0,0.0, 10.0,20.0};

    public static Double[] ENEMY_TYPE1_SHAPE = new Double[]{
        20.0,0.0, 40.0,0.0, 60.0,20.0, 60.0,40.0, 40.0,60.0, 20.0,60.0, 0.0,40.0, 0.0,20.0};
    public static Double[] ENEMY_TYPE2_SHAPE = new Double[]{
        20.0,0.0, 30.0,10.0, 40.0,0.0, 60.0,20.0, 40.0,10.0, 30.0,20.0, 20.0,10.0, 0.0,20.0};
    public static Double[] ENEMY_TYPE3_SHAPE = new Double[]{
        30.0,0.0, 60.0,15.0, 30.0,30.0, 0.0,15.0 };
    public static Double[] GIANT_ENEMY_SHAPE = new Double[] {
        10.0,0.0, 50.0,0.0, 100.0,10.0, 100.0,50.0, 50.0,100.0, 10.0,100.0, 0.0,50.0, 0.0,10.0
    };
    
    public static Double[] ENEMY_BULLET_SHAPE = new Double[]{0.0,-20.0, 5.0,0.0, 10.0,-20.0};
    public static final int ENEMY_LEVEL1_DAMAGE = 10;
    public static final int ENEMY_LEVEL2_DAMAGE = 20;
    public static final int ENEMY_LEVEL3_DAMAGE = 30;
    public static final int ENEMY_LEVEL4_DAMAGE = 40;
    public static final int GIANT_ENEMY_DAMAGE = 50;

    public static final int ALIEN_1_LEVEL_1 = 1;
    public static final int ALIEN_2_LEVEL_1 = 1;
    public static final int ALIEN_3_LEVEL_1 = 1;

    public static final int ALIEN_1_LEVEL_2 = 2;
    public static final int ALIEN_2_LEVEL_2 = 1;
    public static final int ALIEN_3_LEVEL_2 = 2;

    public static final int ALIEN_1_LEVEL_3 = 3;
    public static final int ALIEN_2_LEVEL_3 = 2;
    public static final int ALIEN_3_LEVEL_3 = 3;

    public static final int ALIEN_1_LEVEL_4 = 4;
    public static final int ALIEN_2_LEVEL_4 = 3;
    public static final int ALIEN_3_LEVEL_4 = 3;

    public static final int ALIEN_OFFSET_X = 100;
    public static final int ALIEN_OFFSET_Y = 200;

}