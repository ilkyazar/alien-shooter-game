package com.example.client.view;

import com.example.client.constants.UIConstants;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * This page is the waiting page for the multiplayer level.
 * When a match is found, the usernames of each players are shown in the screen
 * before level 5 starts.
 */
public class WaitView {

    private static Scene waitingRoomScene;

    private static Label label;

    public static void create() {

        label = new Label();
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        vBox.getChildren().add(label);

        waitingRoomScene = new Scene(vBox, UIConstants.WIDTH, UIConstants.HEIGHT);

    }

    public static void show() {
        label.setText(UIConstants.CONNECTING_TO_SERVER);
        UIConstants.window.setScene(waitingRoomScene);
    }

    public static void changeWaitingStatus(String message) {
        label.setText(message);
    }

}