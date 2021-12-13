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
        tfX1.setStyle("-fx-background-color: white;");
        tfX2.setStyle("-fx-background-color: white;");

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
                int x1, x2;

                if(!isNumber(tfX1.getText())){
                    tfX1.setStyle("-fx-background-color: #EBC7D4;");
                    return;
                }
                tfX1.setStyle("-fx-background-color: white;");

                if (!isNumber(tfX2.getText())){
                    tfX2.setStyle("-fx-background-color: #EBC7D4;");
                    return;
                }
                tfX2.setStyle("-fx-background-color: white;");

                if(Integer.parseInt(tfX2.getText()) < Integer.parseInt(tfX1.getText())){
                    x1 = Integer.parseInt(tfX2.getText());
                    x2 = Integer.parseInt(tfX1.getText());
                }
                else {
                    x2 = Integer.parseInt(tfX2.getText());
                    x1 = Integer.parseInt(tfX1.getText());
                }
                OperatingInTaxController controller = new OperatingInTaxController(x1, x2);
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

    private static boolean isNumber(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
