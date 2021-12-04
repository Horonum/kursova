package com.example.kursova.Controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


import com.example.kursova.DataBase.Driver;
import com.example.kursova.Main;
import com.example.kursova.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SwitchLogScenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butAceptSwitchLogin;

    @FXML
    private Button butSwitchLoginBack;

    @FXML
    private TextField tfNewLogin;

    @FXML
    private TextField tfNowLogin;

    @FXML
    private Text textSwitchLogError;

    @FXML
    void initialize() {
        textSwitchLogError.setText("");

        butAceptSwitchLogin.setOnAction(Event -> {
            String log = getLogin();


            if (log.equals(tfNowLogin.getText())) {
                Driver db = new Driver();
                db.SwitchLogin(log, tfNewLogin.getText());

                Main.SwitchScene("LogScene.fxml", butAceptSwitchLogin);
            }

            textSwitchLogError.setText("Ви ввели хибний логін");
        });

        butSwitchLoginBack.setOnAction(Event -> {
            Main.SwitchScene("SetingsScene.fxml", butSwitchLoginBack);
        });
    }

    private String getLogin() {
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
