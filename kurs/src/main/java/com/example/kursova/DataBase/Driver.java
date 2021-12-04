package com.example.kursova.DataBase;


import com.example.kursova.Tax;
import com.example.kursova.User;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Driver {
    String dbHost = "localhost";
    String dbPort = "3306";
    String dbUser = "root";
    String dbPass = "12345";
    String dbName = "kursova";

    public int regUser(User user) {

        String insert = "INSERT INTO USERS " +
                " (login, password)"
                + " VALUES ('" + user.getLogin() + "', '" + user.getPassword() + "')";

        Connection myCon = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursova", "root", "12345");

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(insert);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM users WHERE login =? AND password =?";
        try {
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursova", "root", "12345");


            PreparedStatement prSt = myCon.prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resSet;

    }

    public void SwitchLogin(String loginNow, String loginNew){
        String UPDATE = "UPDATE USERS " +
                " SET login = '" + loginNew +
                "' WHERE login = '" + loginNow + "'";

        Connection myCon = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursova", "root", "12345");

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(UPDATE);

        } catch (SQLIntegrityConstraintViolationException q){

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SwitchPassword(String login, String passwordNew){
        String UPDATE = "UPDATE USERS " +
                " SET password = '" + passwordNew +
                "' WHERE login = '" + login + "'";

        Connection myCon = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursova", "root", "12345");

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(UPDATE);

        } catch (SQLIntegrityConstraintViolationException q){

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTax(Tax tax){
        String insert = "INSERT INTO taxs " +
                " (login_user, typetax, summa, participant, tax)"
                + " VALUES ('" + tax.getUser() + "', '" + tax.getTypeTax() + "', '" + tax.getStartBalance() + "', " +
                tax.getParticipant() + ", " + tax.getTax() + ")";

        Connection myCon = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursova", "root", "12345");

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(insert);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet vievTax(String login) {
        ResultSet resSet = null;

        String select = "SELECT * FROM taxs WHERE login_user =?";
        try {
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursova", "root", "12345");


            PreparedStatement prSt = myCon.prepareStatement(select);
            prSt.setString(1, login);

            resSet = prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resSet;

    }

    public void delTax(int id){
        String delete = "DELETE FROM taxs WHERE idtax = " + id;


        Connection myCon = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursova", "root", "12345");

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(delete);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearProfile(){

        String delete = "DELETE FROM taxs WHERE login_user = '" + getLog() + "'" ;

        Connection myCon = null;
        try {
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursova", "root", "12345");

            Statement myStmt = myCon.createStatement();

            myStmt.executeUpdate(delete);

        }catch (SQLSyntaxErrorException e){

        } catch (SQLException e) {
            e.printStackTrace();
        }
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




