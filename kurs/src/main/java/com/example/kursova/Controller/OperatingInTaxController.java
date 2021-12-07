package com.example.kursova.Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.kursova.DataBase.Driver;
import com.example.kursova.Main;
import com.example.kursova.Tax;
import com.example.kursova.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class OperatingInTaxController implements Initializable {

    private ArrayList<Tax> taxArrayList = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Tax, Integer> idtax;

    @FXML
    private TableColumn<Tax, Integer> Participant;

    @FXML
    private TableColumn<Tax, Integer> StartBalance;

    @FXML
    private TableColumn<Tax, Integer> Tax;

    @FXML
    private TableColumn<Tax, String> TypeTax;

    @FXML
    private TableColumn<Tax, String> User;

    @FXML
    private TableView<Tax> table;

    @FXML
    private Button butAddTax;

    @FXML
    private Button butDeleteTax;

    @FXML
    private Button butSearchSumma;

    @FXML
    private Button butTaxSceneBack;

    @FXML
    private Button butTaxSceneExit;

    @FXML
    private TextField tfSumma;

    ObservableList<Tax> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Driver db = new Driver();
        ResultSet result = db.vievTax(getLog());

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                list.add(new Tax(result.getInt(1), result.getString(2), result.getString(3),
                        result.getInt(4), result.getInt(5), result.getInt(6)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        idtax.setCellValueFactory(new PropertyValueFactory<Tax, Integer>("idtax"));
        User.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser()));
        TypeTax.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTypeTax()));
        StartBalance.setCellValueFactory(new PropertyValueFactory<Tax, Integer>("StartBalance"));
        Participant.setCellValueFactory(new PropertyValueFactory<Tax, Integer>("Participant"));
        Tax.setCellValueFactory(new PropertyValueFactory<Tax, Integer>("Tax"));



        table.setItems(list);

        butAddTax.setOnAction(Event-> {
            Main.SwitchScene("AddTaxScene.fxml", butAddTax);
        });

        butTaxSceneExit.setOnAction(Event-> {
            Main.SwitchScene("LogScene.fxml", butTaxSceneExit);
        });

        butSearchSumma.setOnAction(Event-> {
            Integer sum = 0;
            for (int i = 0; i < list.size(); i++){
                sum+=list.get(i).getTax();
            }

            tfSumma.setText(sum.toString());
        });

        butTaxSceneBack.setOnAction(Event -> {
            Main.SwitchScene("MainScene.fxml", butTaxSceneBack);
        });

        butDeleteTax.setOnAction(Event -> {
            int i = table.getSelectionModel().getSelectedItem().getIdtax();

            db.delTax(i);

            Main.SwitchScene("OperationInTaxsScene.fxml", butDeleteTax);
        });

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
