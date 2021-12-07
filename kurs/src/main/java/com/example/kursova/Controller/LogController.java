package com.example.kursova.Controller;


import com.example.kursova.Main;
import com.example.kursova.User;
import com.example.kursova.DataBase.Driver;


import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LogController {
    User user = new User();

    @FXML
    private Button butLogin;

    @FXML
    private Button butRegistry;

    @FXML
    private Hyperlink linkAuthors;

    @FXML
    private Text textError;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfPassword;

    @FXML
    void initialize() {
        String Error = textError.getText();
        textError.setText("");

        butLogin.setOnAction(Event -> {
            loginUser(tfLogin.getText(), tfPassword.getText());
        });

        butRegistry.setOnAction(Event -> {
            butRegistry.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegScene.fxml"));

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("TaxCalculation");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        });

    }

    public void loginUser(String login, String password) {
        Driver dbHandler = new Driver();

        user.setLogin(login);
        user.setPassword(password);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }

        if (counter == 1) {
            FileWrite(user);
            Main.SwitchScene("MainScene.fxml", butLogin);


        } else {
            textError.setText("Ви ввели хибно логін або пароль");
        }

    }
    private void FileWrite(User user){
        File file = new File("D:\\kurs\\userData.txt");

        try {
            file.delete();
            file.createNewFile();

            FileWriter writer = new FileWriter("D:\\\\kurs\\\\userData.txt", true);

            BufferedWriter bufferWriter = new BufferedWriter(writer);


            bufferWriter.write(user.getLogin());
            bufferWriter.write("\n" + user.getPassword());

            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}

