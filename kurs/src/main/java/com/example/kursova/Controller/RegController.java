package com.example.kursova.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.example.kursova.DataBase.Driver;
import com.example.kursova.Main;
import com.example.kursova.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegController {

    @FXML
    private Button butReg;

    @FXML
    private Text textError2;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private PasswordField tfPasword2;

    @FXML
    void initialize() {
        textError2.setText(" ");

        butReg.setOnAction(Event ->{
            if(tfPasword2.getText().equals(tfPassword.getText())) {
                Driver db = new Driver();
                User user = new User(tfLogin.getText(), tfPassword.getText());
                if (db.regUser(user) == 1) {
                    textError2.setText("Даний логін вже зареєстрований");
                }
                else {

                    butReg.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogScene.fxml"));

                    try {
                        fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Stage stage = new Stage();
                    stage.setTitle("TaxCalculation");
                    stage.setScene(new Scene(fxmlLoader.getRoot()));
                    stage.setResizable(false);
                    stage.show();
                }
            }
            else{
                    textError2.setText("Паролі не співпадають!");
                    tfPassword.clear();
                    tfPasword2.clear();
                }

        });
    }

}
