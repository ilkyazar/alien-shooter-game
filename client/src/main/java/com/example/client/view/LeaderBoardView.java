package com.example.client.view;

import com.example.client.constants.UIConstants;
import com.example.client.WebService;
import com.example.client.model.LeaderboardEntry;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.ArrayList;

/** This class is the view for Leaderboard page.
 * According to the time option, Leaderboard entries are fetched from the WebService and
 * each entry is written to the table columns.
 * Tables show the information of player ID, username, score and date. Scores are sorted in descending order.
 * Score is calculated by how many times you shoot an alien and how many times you got shot.
 */

public class LeaderBoardView {

    private static Scene leaderBoardScene;
    private static TableView leaderBoardTable;
    private static Label leaderBoardTitle;

    public static void create() {

        Button back = new Button();
        back.setText(UIConstants.BACK_BUTTON_LABEL);
        back.setOnAction(e->{
            MainMenuView.show();
        });

        TableColumn<Integer, LeaderboardEntry> idCol = new TableColumn<>(UIConstants.LEADERBOARD_PLAYERID_LABEL);
        TableColumn<String, LeaderboardEntry> nameCol = new TableColumn<>(UIConstants.LEADERBOARD_PLAYERNAME_LABEL);
        TableColumn<Integer, LeaderboardEntry> scoreCol = new TableColumn<>(UIConstants.LEADERBOARD_SCORE_LABEL);
        TableColumn<String, LeaderboardEntry> dateCol = new TableColumn<>(UIConstants.LEADERBOARD_DATE_LABEL);

        idCol.setResizable(false);
        nameCol.setResizable(false);
        scoreCol.setResizable(false);
        dateCol.setResizable(false);
        
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);

        leaderBoardTable = new TableView();
        leaderBoardTable.setEditable(false);
        
        leaderBoardTitle = new Label();
        leaderBoardTitle.setStyle("-fx-font-weight: bold");

        idCol.prefWidthProperty().bind(leaderBoardTable.widthProperty().multiply(0.1));
        nameCol.prefWidthProperty().bind(leaderBoardTable.widthProperty().multiply(0.2));
        scoreCol.prefWidthProperty().bind(leaderBoardTable.widthProperty().multiply(0.3));
        dateCol.prefWidthProperty().bind(leaderBoardTable.widthProperty().multiply(0.4));
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        leaderBoardTable.getColumns().addAll(idCol, nameCol, scoreCol, dateCol);

        vbox.getChildren().addAll(leaderBoardTitle ,leaderBoardTable,back);
        leaderBoardScene = new Scene(vbox, UIConstants.WIDTH, UIConstants.HEIGHT);

    }


    public static void show(int time) {
        List<LeaderboardEntry> entries = new ArrayList<>();
        
        switch(time){
            case 1:
                leaderBoardTitle.setText(UIConstants.WEEKLY_LEADERBOARD_LABEL);
                entries = WebService.getWeeklyLeaderboard();
                break;
            case 2:
                leaderBoardTitle.setText(UIConstants.MONTHLY_LEADERBOARD_LABEL);
                entries = WebService.getMonthlyLeaderboard();
                break;
            case 3:
                leaderBoardTitle.setText(UIConstants.ALL_LEADERBOARD_LABEL);
                entries = WebService.getAllLeaderboard();
                break;
        }
            
        leaderBoardTable.getItems().clear();
        for (LeaderboardEntry entry : entries) {
            leaderBoardTable.getItems().add(entry);
        }
        UIConstants.window.setScene(leaderBoardScene);
    }


}