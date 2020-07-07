package com.example.client.view;

import com.example.client.constants.*;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.example.client.controller.GameEngine;
import com.example.client.model.Player;
import javafx.scene.Scene;
import com.example.client.multiplayer.*;

/**
 * This class is the result page of the game.
 * It shows the result for a player if the player dies in a single-player level.
 * It shows the results for two players, the total score and the
 * player who recieves the bonus score for the multiplayer level.
 */
public class GameOverView {

    private static Scene gameOverScene;
    private static VBox vbox;
    private static Label scoreLabel1;
    private static Label scoreLabel2;
    private static Label totalScoreLabel;
    private static Label bonusLabel;
    
    public static void create() {

        Label gameOverLabel = new Label(UIConstants.GAME_OVER_LABEL);
        scoreLabel1 = new Label();
        scoreLabel2 = new Label();
        totalScoreLabel = new Label();
        bonusLabel = new Label();
        
        vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        gameOverScene = new Scene(vbox, UIConstants.WIDTH, UIConstants.HEIGHT);

        Button backButton = new Button();
        backButton.setText(UIConstants.MAIN_MENU_LABEL);

        backButton.setOnAction(e -> {

            MainMenuView.show();

        });

        vbox.getChildren().addAll(gameOverLabel, scoreLabel1, scoreLabel2, totalScoreLabel, bonusLabel, backButton);
    }

    public static void show(Player player1, Player player2, boolean isMultiplayer) {
        if(isMultiplayer){
            String player1Name = GameEngine.player.getUserName();
            String player2Name = GameEngine.player2.getUserName();

            scoreLabel1.setText(player1Name + UIConstants.SCORE + Integer.toString(player1.getScore()));
            scoreLabel2.setText(player2Name + UIConstants.SCORE + Integer.toString(player2.getScore()));
            totalScoreLabel.setText(UIConstants.TOTAL_SCORE + MultiplayerClient.total);

            if (GameEngine.player.gotBonus()) {
                System.out.println("Player1 got bonus");
                bonusLabel.setText(GameConstants.GIANT_BONUS_POINTS + UIConstants.GOT_BONUS + player1Name);
            }
            else if (GameEngine.player2.gotBonus()) {
                System.out.println("Player2 got bonus");
                bonusLabel.setText(GameConstants.GIANT_BONUS_POINTS + UIConstants.GOT_BONUS + player2Name);
            }
            else {
                System.out.println("Nobody could get bonus");
                bonusLabel.setText(UIConstants.NO_BONUS);
            }
        }
        else{
            String player1Name = GameEngine.player.getUserName();
            scoreLabel1.setText(player1Name + UIConstants.SCORE + Integer.toString(player1.getScore()));
            scoreLabel2.setText("");
            totalScoreLabel.setText("");
            bonusLabel.setText("");
        }
        
        UIConstants.window.setScene(gameOverScene);
    }
}