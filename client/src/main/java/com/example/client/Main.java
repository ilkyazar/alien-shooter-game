package com.example.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.client.constants.UIConstants;
import com.example.client.view.*;

/**
 * Main class initiates the primarystage, sets window,
 * creates all the views and then shows LoginView to start the GUI of the application.
 */

public class Main extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(ClientApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) {

        UIConstants.window = primaryStage;
        UIConstants.window.setTitle(UIConstants.GAME_NAME);
        UIConstants.window.setResizable(false);

        LoginView.create();
        MainMenuView.create();
        LeaderBoardView.create();
        GameView.create();
        WaitView.create();
        GameOverView.create();

        LoginView.show();

        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
    }
}
