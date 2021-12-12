package com.example.kursova.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursova.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SearchParametersController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butAceptParameters;

    @FXML
    private Button butCancelParameters;

    @FXML
    private TextField tfX1;

    @FXML
    private TextField tfX2;

    @FXML
    void initialize() {
            butCancelParameters.setOnAction(Event -> {
                OperatingInTaxController controller = new OperatingInTaxController();
                butCancelParameters.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OperationInTaxsScene.fxml"));
                fxmlLoader.setController(controller);

                try {
                    fxmlLoader.load();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage stage = new Stage();
                stage.setTitle("TaxCalculation");
                stage.setScene(new Scene(fxmlLoader.getRoot()));
                stage.getIcons().add(new Image("https://st2.depositphotos.com/2038163/11363/v/380/depositphotos_113637712-stock-illustration-moderm-minimalis-initial-logo-tc.jpg"));
                stage.setResizable(false);
                stage.show();
            });

            butAceptParameters.setOnAction(Event -> {

                OperatingInTaxController controller = new OperatingInTaxController(Integer.valueOf(tfX1.getText()), Integer.valueOf(tfX2.getText()));
                butAceptParameters.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OperationInTaxsScene.fxml"));
                fxmlLoader.setController(controller);

                try {
                    fxmlLoader.load();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage stage = new Stage();
                stage.setTitle("TaxCalculation");
                stage.setScene(new Scene(fxmlLoader.getRoot()));
                stage.getIcons().add(new Image("https://st2.depositphotos.com/2038163/11363/v/380/depositphotos_113637712-stock-illustration-moderm-minimalis-initial-logo-tc.jpg"));
                stage.setResizable(false);
                stage.show();
            });
    }

}
