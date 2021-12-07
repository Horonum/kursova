package com.example.kursova.Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursova.DataBase.Driver;
import com.example.kursova.Main;
import com.example.kursova.Tax;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddTaxController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butAceptAddTax;

    @FXML
    private Button butCancelAddTax;

    @FXML
    private ComboBox cbTypeTax;

    @FXML
    private TextField tfBalance;

    @FXML
    void initialize() {
        cbTypeTax.getItems().addAll("Податок з основного або додаткового місця роботи",
        "Податок з авторських винагород",
        "Податок з продажу майна",
        "Податок з отримання грошофих сум або майна",
        "Податок з отримання коштів з-за кордону",
        "Податок з пільг на дітей",
        "Податок з матеріальної допомоги");

        butCancelAddTax.setOnAction(Event -> {
            Main.SwitchScene("OperationInTaxsScene.fxml", butCancelAddTax);
        });

        butAceptAddTax.setOnAction(Event -> {

            Integer balance = Integer.valueOf(tfBalance.getText());

            String choose = cbTypeTax.getSelectionModel().getSelectedItem().toString();

            Tax tax = searchTax(balance, choose);
            Driver db = new Driver();
            db.addTax(tax);

            Main.SwitchScene("OperationInTaxsScene.fxml", butAceptAddTax);
        });
    }

    private Tax searchTax(int balance, String choose){
        Tax tax = new Tax();
        int participant = 0;

        String str1 = "Податок з основного або додаткового місця роботи";
        String str2 = "Податок з авторських винагород";
        String str3 = "Податок з продажу майна";
        String str4 = "Податок з отримання грошофих сум або майна";
        String str5 = "Податок з отримання коштів з-за кордону";
        String str6 = "Податок з пільг на дітей";
        String str7 = "Податок з матеріальної допомоги";

        if(str1.equals(choose)){
            participant = 20;
        }
        if(str2.equals(choose)){
            participant = 10;
        }
        if(str3.equals(choose)){
            participant = 18;
        }
        if(str4.equals(choose)){
            participant = 15;
        }
        if(str5.equals(choose)){
            participant = 25;
        }
        if(str6.equals(choose)){
            participant = 8;
        }
        if(str7.equals(choose)){
            participant = 5;
        }

        tax.setTypeTax(choose);
        tax.setStartBalance(balance);
        tax.setUser(getLog());
        tax.setParticipant(participant);
        tax.setTax(balance / 100 * participant);

        return tax;
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
