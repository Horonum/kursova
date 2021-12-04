package com.example.kursova.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursova.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SettingsSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butSetingsBack;

    @FXML
    private Button butSwitchImg;

    @FXML
    private Button butSwitchLogin;

    @FXML
    private Button butSwitchPassword;

    private Image image;

    @FXML
    void initialize() {

       butSwitchLogin.setOnAction(Event -> {
           Main.SwitchScene("SwitchLogScene.fxml", butSwitchLogin);
       });

       butSwitchPassword.setOnAction(Event -> {
           Main.SwitchScene("SwitchPassScene.fxml", butSwitchPassword);
       });

       butSetingsBack.setOnAction(Event -> {
           Main.SwitchScene("MainScene.fxml", butSetingsBack);
       });

       butSwitchImg.setOnAction(Event -> {
           Main.SwitchScene("SwitchImg", butSwitchImg);
       });
    }
}
