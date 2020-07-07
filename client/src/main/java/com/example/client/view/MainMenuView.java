package com.example.client.view;

import com.example.client.constants.UIConstants;
import com.example.client.controller.GameEngine;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


/**
 * This class represents the Main Menu View.
 * It has buttons for: starting a new game, showing weekly, monthly and all times leaderboards, and go back to login page.
 */

public class MainMenuView {

    private static Scene mainMenuScene;

    public static void create() {

        Button newGame = new Button();
        newGame.setText(UIConstants.NEW_GAME_LABEL);
        
        newGame.setOnAction(e -> {
            GameEngine.start();
            GameView.show();
        });

        Button weeklyLeaderBoard = new Button();
        weeklyLeaderBoard.setText(UIConstants.WEEKLY_LEADERBOARD_LABEL);
        
        weeklyLeaderBoard.setOnAction(e -> {
            LeaderBoardView.show(UIConstants.WEEKLY);
        });

        Button monthlyLeaderBoard = new Button();
        monthlyLeaderBoard.setText(UIConstants.MONTHLY_LEADERBOARD_LABEL);
        
        monthlyLeaderBoard.setOnAction(e -> {
            LeaderBoardView.show(UIConstants.MONTHLY);
        });

        Button allLeaderBoard = new Button();
        allLeaderBoard.setText(UIConstants.ALL_LEADERBOARD_LABEL);
        
        allLeaderBoard.setOnAction(e -> {
            LeaderBoardView.show(UIConstants.ALL_TIME);
        });


        Button backButton = new Button();
        backButton.setText(UIConstants.BACK_BUTTON_LABEL);
        backButton.setOnAction(e-> {
            LoginView.show();
        });

        VBox vb = new VBox();
        vb.getChildren().addAll(newGame, weeklyLeaderBoard, monthlyLeaderBoard, allLeaderBoard, backButton);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);

        mainMenuScene = new Scene(vb, UIConstants.WIDTH, UIConstants.HEIGHT);
    }

    
    public static void show() {
        UIConstants.window.setScene(mainMenuScene);
    }

}