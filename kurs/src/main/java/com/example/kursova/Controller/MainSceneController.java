package com.example.kursova.Controller;

import com.example.kursova.DataBase.Driver;
import com.example.kursova.Main;
import com.example.kursova.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSceneController {
    User user = new User();

    @FXML
    private Button butClearProfile;

    @FXML
    private Button butExitAcaunt;

    @FXML
    private Button butSettings;

    @FXML
    private Button butTaxs;

    @FXML
    void initialize(){
        butExitAcaunt.setOnAction(Event -> {
            Main.SwitchScene("LogScene.fxml", butExitAcaunt);
        });

        butSettings.setOnAction(Event -> {
            Main.SwitchScene("SetingsScene.fxml", butSettings);
        });

        butClearProfile.setOnAction(Event -> {
            Stage stage = new Stage();
            Driver db = new Driver();
            db.clearProfile();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SendMessageClearProfile.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("TaxCalculation");
            stage.setScene(scene);
            stage.getIcons().add(new Image("https://st2.depositphotos.com/2038163/11363/v/380/depositphotos_113637712-stock-illustration-moderm-minimalis-initial-logo-tc.jpg"));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();


        });

        butTaxs.setOnAction(Event -> {
            Main.SwitchScene("OperationInTaxsScene.fxml", butTaxs);
        });
    }
}
