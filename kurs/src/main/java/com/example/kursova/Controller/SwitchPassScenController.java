package com.example.kursova.Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursova.DataBase.Driver;
import com.example.kursova.Main;
import com.example.kursova.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

public class SwitchPassScenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butAceptSwitchLogin;

    @FXML
    private Button butSwitchLoginBack;

    @FXML
    private Text textSwitchPassError;

    @FXML
    private PasswordField tfNewPassword;

    @FXML
    private PasswordField tfNowPassword;

    @FXML
    void initialize() {
        textSwitchPassError.setText("");

        butAceptSwitchLogin.setOnAction(Event -> {
            String pass = getPass();
            String log = getLog();

            if (pass.equals(tfNowPassword.getText())) {
                Driver db = new Driver();
                db.SwitchPassword(log, tfNewPassword.getText());

                Main.SwitchScene("LogScene.fxml", butAceptSwitchLogin);
            }

            textSwitchPassError.setText("Ви ввели хибний старий пароль");
        });

        butSwitchLoginBack.setOnAction(Event -> {
            Main.SwitchScene("SetingsScene.fxml", butSwitchLoginBack);
        });
    }

    private String getPass() {
        try {
            FileInputStream fstream = new FileInputStream("D:\\\\kurs\\\\userData.txt");
            BufferedReader infile = new BufferedReader(new InputStreamReader(
                    fstream));
            String data = new String();
            if ((data = infile.readLine()) != null)
                if ((data = infile.readLine()) != null){
                return data;
            }
        } catch (IOException e) {
            // Error
        }
        return "";
    }
    private String getLog() {
        try {
            FileInputStream fstream = new FileInputStream("D:\\\\kurs\\\\userData.txt");
            BufferedReader infile = new BufferedReader(new InputStreamReader(
                    fstream));
            String data = new String();
            if ((data = infile.readLine()) != null) {
                return data;
            }
        } catch (IOException e) {
            // Error
        }
        return "";
    }

}
