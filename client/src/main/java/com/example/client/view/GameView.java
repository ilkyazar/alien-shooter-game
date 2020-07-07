package com.example.client.view;

import com.example.client.WebService;
import com.example.client.constants.GameConstants;
import com.example.client.constants.UIConstants;
import com.example.client.controller.GameEngine;
import com.example.client.model.Player;
import com.example.client.model.Alien;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 

import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCode;

/**
 * This is the Game View class. It contains a gamePane which holds all the game components.
 * Player starts shooting automatically when the game starts. Player moves with the mouse move event.
 * When all aliens are dead, GameView increments the level in GameEngine and new aliens are created.
 * 
 * When Level 4 is finished without getting killed, the GameView stops until a match for the multiplayer
 * level is found. Then it resumes for level 5.
 * When the game ends, score of the player is saved to the leaderboard. And the 
 * gamepane is cleaned and all variables are set to idle for the next game.
 * 
 * Also Ctrl+Shift+9 cheat is implemented here. (But it is not applicable for Level 5!)
 * 
 */
public class GameView {

    private static Scene gameViewScene;
    public static Pane gamePane;
    private static Text level;
    private static Text health;
    private static Text score;
    private static Text health2;
    private static Text score2;
    public static Player player2;
    public static boolean levelOver = false;
    public static boolean gameOver = false;


    public static void create() {
        gamePane = new Pane();
        gameViewScene = new Scene(gamePane, UIConstants.WIDTH, UIConstants.HEIGHT);

        try {
            Image image = new Image(UIConstants.BACKGROUND_PATH);
            ImageView background = new ImageView(image);
            background.setFitHeight(UIConstants.HEIGHT);
            background.setFitWidth(UIConstants.WIDTH);
            gamePane.getChildren().add(background);
        } 
        catch (Exception e) {
            System.out.println(e);
        }

        initLabels();
          
    }

    static void play() {
        if(GameEngine.getLevel()<5){
            GameEngine.newLevel();
        }
        
        Player player = GameEngine.player;
        gamePane.getChildren().add(player.getShip());

        if (GameEngine.getLevel() < 5){
            player.startShooting();
            addAliens();
        }
        else{
            player2 = GameEngine.player2;
            gamePane.getChildren().add(player2.getShip());
        }


        gameViewScene.setOnMouseMoved(event-> {
        
            if (levelOver == false) {
                
                clearAliens(false);
                
                if (!GameEngine.isAliensAlive()) {
                    System.out.println(UIConstants.LEVEL_PASSED_LABEL);
                    GameEngine.setLevel(GameEngine.getLevel()+1);
                    showLevelOver(UIConstants.LEVEL_PASSED_LABEL);
                    
                    levelOver = true;
                    return;
                }
    
                if (player.isAlive()) {
                    player.getShip().setTranslateX(event.getX());
                    player.getShip().setTranslateY(event.getY());

                }
                else {
                    levelOver = true;
                    clearAliens(true);
                    if(GameEngine.getLevel() < 5){
                        //finish single player game
                        finishGame(false);
                    }
                    return;
                }
            }
            
        });

        KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.NUMPAD9, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
        KeyCombination keyComb2 = new KeyCodeCombination(KeyCode.DIGIT9, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);           
        gameViewScene.addEventHandler(KeyEvent.KEY_RELEASED, event-> {
            
                if (keyComb1.match(event) || keyComb2.match(event) ) {
                    if (GameEngine.getLevel() != 5) {
                        levelOver = true;
                        clearAliens(true);
                        GameEngine.setLevel(GameEngine.getLevel()+1);
                        showLevelOver(UIConstants.LEVEL_PASSED_LABEL);
                    }
                }
            
        });

    }

    public static void clearAliens(boolean killAll){
        for (Alien alien : GameEngine.getAliens() ){
            if (killAll)
                alien.setIsDead();

            if (!alien.isAlive()) {
                gamePane.getChildren().remove(alien.getAlienShip());
            }
        }
        
    }

    public static void initLabels(){
        level = new Text();
        level.setX(UIConstants.WIDTH /2.0 - 40); level.setY(30);         
        level.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        level.setFill(Color.WHITE);
        updateLevelLabel(1);

        health = new Text(); 
        health.setX(UIConstants.WIDTH /2.0 - 200); health.setY(60);
        health.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        health.setFill(Color.WHITE);
        updateHealthLabel(GameConstants.PLAYER_HEALTH);

        score = new Text();
        score.setX(UIConstants.WIDTH /2.0 + 100); score.setY(60);
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));    
        score.setFill(Color.WHITE);
        updateScoreLabel(0);

        health2 = new Text();
        health2.setX(UIConstants.WIDTH /2.0 - 200); health2.setY(100);
        health2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        health2.setFill(Color.WHITE);

        score2 = new Text();
        score2.setX(UIConstants.WIDTH /2.0 + 100); score2.setY(100);
        score2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));    
        score2.setFill(Color.WHITE);
        
        gamePane.getChildren().addAll(level, health, score, health2, score2);
    }

    static void addAliens() {
        //System.out.println("Adding aliens for level: " + GameEngine.getLevel());
        for(Alien alien : GameEngine.getAliens()){
            if(alien.isAlive()){
                gamePane.getChildren().add(alien.getAlienShip());
                alien.startShooting();
                alien.startMoving();
            }
        }
    }

    public static void addGiantAlien() {
        for(Alien alien : GameEngine.getAliens()){
            if(alien.isAlive()){
                gamePane.getChildren().add(alien.getAlienShip());
                alien.startShooting();
            }
        }
    }


    static void showLevelOver(String s) {

        Button overButton = new Button();
        String buttonTxt = UIConstants.NEXT_LEVEL_LABEL;
        String infoText = s;

        overButton.setText(buttonTxt);
        overButton.prefWidthProperty().bind(UIConstants.window.widthProperty().multiply(0.60));

        VBox vb = new VBox();
        vb.getChildren().add(new Text(infoText));
        vb.getChildren().addAll(overButton);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        overButton.setAlignment(Pos.CENTER);
        String style = "-fx-background-color: rgba(255, 255, 255, 0.5);";
        vb.setStyle(style);

        gamePane.getChildren().add(vb);
        vb.setLayoutX(200);
        vb.setLayoutY(850);
        vb.prefWidthProperty().bind(UIConstants.window.widthProperty().multiply(0.60));

        overButton.setOnAction(e-> {
            levelOver = false;
            GameEngine.newLevel();
            if (GameEngine.getLevel() != 5)
                addAliens();
            gamePane.getChildren().remove(vb);
        });
        
    }

    public static void finishGame(boolean isMultiplayer){
        Player player1 = GameEngine.player;
        Player player2 = GameEngine.player2;

        GameEngine.player.setIsDead();
        GameEngine.player2.setIsDead();
        WebService.addToLeaderboard(LoginView.getPlayerId(),GameEngine.player.getScore());
        
        gamePane.getChildren().remove(GameEngine.player.getShip());
        if(isMultiplayer){
            gamePane.getChildren().remove(GameEngine.player2.getShip());
        }

        for (Alien alien: GameEngine.aliens) {
            gamePane.getChildren().remove(alien.getAlienShip());
        }
        
        updateScoreLabel(0);
        updateHealthLabel(GameConstants.PLAYER_HEALTH);

        score2.setText("");
        health2.setText("");
        
        gameOver = false;
        levelOver = false;
        
        gameViewScene.setOnMouseMoved(null);
        GameOverView.show(player1, player2, isMultiplayer);
        System.out.println("Game engine stopped");
        GameEngine.stop();
    }


    public static void updateLevelLabel(int newLevel) {
        level.setText(UIConstants.LEVEL_LABEL + String.valueOf(newLevel));
    }

    public static void updateHealthLabel(int newHealth){
        health.setText(UIConstants.HEALTH_LABEL + String.valueOf(newHealth));
    }

    public static void updateScoreLabel(int newScore){
        score.setText(UIConstants.SCORE_LABEL + String.valueOf(newScore));
    }

    public static void updateHealth2Label(int newHealth){
        health2.setText(UIConstants.HEALTH_LABEL_2 + String.valueOf(newHealth));
    }

    public static void updateScore2Label(int newScore){
        score2.setText(UIConstants.SCORE_LABEL_2 + String.valueOf(newScore));
    }

    public static void show() {
        play();
        UIConstants.window.setScene(gameViewScene);
        
    }
}