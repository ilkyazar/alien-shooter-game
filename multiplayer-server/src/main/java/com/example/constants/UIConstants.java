package com.example.constants;

import javafx.stage.Stage;

/**
 * Constants related to UI components (ie. labels, text fields, image paths)
 */

public class UIConstants {
    public static Stage window;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    public static final String GAME_NAME = "indiGO";
    public static final String LOGIN_NAME_LABEL = "Name";
    public static final String LOGIN_PASSWORD_LABEL = "Password";
    public static final String LOGIN_BUTTON_LABEL = "Login";
    public static final String SIGN_UP_BUTTON_LABEL = "Sign Up";
    public static final String START_BUTTON_LABEL = "START";

    public static final String ALERT = "ALERT";
    public static final String LOGIN_ALERT = "Wrong username or password!";
    public static final String SIGNUP_ALERT = "Player already exists!";

    public static final String SUCCESS = "SUCCESS";
    public static final String LOGIN_SUCCESS = "Login successful!!";
    public static final String SIGNUP_SUCCESS = "Register successful!!";

    public static final String NEW_GAME_LABEL = "New Game";
    public static final String WEEKLY_LEADERBOARD_LABEL = "Weekly Leaderboard";
    public static final String MONTHLY_LEADERBOARD_LABEL = "Monthly Leaderboard";
    public static final String ALL_LEADERBOARD_LABEL = "All Times Leaderboard";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String NEXT_LEVEL_LABEL = ">> CONTINUE";
    public static final String MAIN_MENU_LABEL = ">> Back to Main Menu";
    public static final String SCORE_LABEL = "Your Score: ";
    public static final String HEALTH_LABEL = "Health: ";
    public static final String LEVEL_LABEL = "LEVEL ";

    public static final String LEVEL_PASSED_LABEL = "LEVEL PASSED!";
    public static final String GAME_OVER_LABEL = "GAME OVER";


    public static final String BACKGROUND_PATH = "./static/background.jpg";
    public static final String SPACESHIP_PATH = "./static/rocket.png";
    public static final String ALIEN_1_PATH = "./static/alien-1.png";
    public static final String ALIEN_2_PATH = "./static/alien-2.png";
    public static final String ALIEN_3_PATH = "./static/alien-3.png";
    public static final String GIANT_PATH = "./static/giant.png";

    public static final String LEADERBOARD_PLAYERID_LABEL = "User ID";
    public static final String LEADERBOARD_PLAYERNAME_LABEL = "Username";
    public static final String LEADERBOARD_SCORE_LABEL = "Score";
    public static final String LEADERBOARD_DATE_LABEL = "Date";

    public static final int WEEKLY = 1;
    public static final int MONTHLY = 2;
    public static final int ALL_TIME = 3;     


    public static final String CONNECTING_TO_SERVER = "Connecting to server...";
    public static final String SERVER_CONNECTED = "Connected to server.";
    public static final String CONNECTION_FAIL = "Failed to connect to the server.";

}