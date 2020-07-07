package com.example.client.view;

import com.example.client.constants.*;
import com.example.client.WebService;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;

import javafx.scene.Scene;

/**
 * This class is the login page of the game.
 * It contains fields for username and password, and buttons for login or signup.
 * For login, authentication of the username and password is checked by the WebService,
 * and then redirected to MainMenu view.
 * For signup, if the username is available, it is registered (added to db) by the WebService.
 */
public class LoginView {

    private static Scene loginScene;
    private static final TextField nameTextField = new TextField();
    private static final PasswordField passwordTextField = new PasswordField();
    public static String userName;
    
    public static void create() {

        Button loginButton = new Button();
        loginButton.setText(UIConstants.LOGIN_BUTTON_LABEL);

        loginButton.setOnAction(e -> {

            if (WebService.canUserLogin(nameTextField.getText(), passwordTextField.getText())) {
                System.out.println(UIConstants.LOGIN_SUCCESS);
                userName = nameTextField.getText();
                MainMenuView.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Login");
                alert.setContentText(UIConstants.LOGIN_ALERT);
                alert.showAndWait();
                
            }

        });


        Button signupButton = new Button();
        signupButton.setText(UIConstants.SIGN_UP_BUTTON_LABEL);
        
        signupButton.setOnAction(e -> {

            if (WebService.isUsernameAvailable(nameTextField.getText())) {
                WebService.registerUser(nameTextField.getText(), passwordTextField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Sign Up");
                alert.setContentText(UIConstants.SIGNUP_SUCCESS);
                alert.showAndWait();
                
            } 
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Sign Up");
                alert.setContentText(UIConstants.SIGNUP_ALERT);
                alert.showAndWait();
                
            }
        });
        
        Label nameLabel = new Label(UIConstants.LOGIN_NAME_LABEL);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(nameLabel, nameTextField);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        Label passwordLabel = new Label(UIConstants.LOGIN_PASSWORD_LABEL);
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(passwordLabel, passwordTextField);
        hbox2.setSpacing(10);
        hbox2.setAlignment(Pos.CENTER);

        VBox vb = new VBox();
        vb.getChildren().addAll(hbox , hbox2, loginButton, signupButton);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);

        loginScene = new Scene(vb, UIConstants.WIDTH, UIConstants.HEIGHT);
        UIConstants.window.show();
    }

    public static void show(){
        UIConstants.window.setScene(loginScene);
    }

    public static int getPlayerId(){
        return WebService.getIdbyUsername(nameTextField.getText());
    }

}