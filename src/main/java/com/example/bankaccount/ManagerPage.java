package com.example.bankaccount;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.sql.*;

public class ManagerPage {

    @FXML
    private ListView<String> LVtrancastion;

    @FXML
    private ListView<String> Lvclintview;

    final ObservableList<String> clientInfo = FXCollections.observableArrayList();
    final ObservableList<String> allTransactions = FXCollections.observableArrayList();

    @FXML
    private Button btnExit;

    @FXML
    private Button btnallinfo;

    @FXML
    private Button btntransaction;

    @FXML
    void pressBtnExitt(ActionEvent event) {

        Platform.exit();
    }

    @FXML
    void pressBtnallinfo(ActionEvent event) throws SQLException {
Lvclintview.setVisible(true);
LVtrancastion.setVisible(false);
connectionforDataBase();
String sql ="SELECT Firstname,Lastname,Birthdate,Gender,Country,nc,FatherName ,PhoneNumber,Password,Balance,AccountNum FROM Clients";
        ResultSet set = statementforDataBase.executeQuery(sql);
        while (set.next()){
            String firstname =set.getString("Firstname");
            String ln =set.getString("Lastname");
            String bd =set.getString("Birthdate");
            String gen =set.getString("Gender");
            String country =set.getString("Country");
            String nc =set.getString("nc");
            String Fn =set.getString("FatherName");
            String Ph =set.getString("PhoneNumber");
            String pss =set.getString("Password");
            String blanc =set.getString("Balance");
            String acc =set.getString("AccountNum");
            clientInfo.add( firstname + ln+ bd+ gen +country +nc+ Fn+ Ph+ pss+ blanc +acc );
            Lvclintview.setItems(clientInfo);
        }
    }

    @FXML
    void pressBtnalltransaction(ActionEvent event) throws SQLException {
        Lvclintview.setVisible(false);
        LVtrancastion.setVisible(true);
        connectionforDataBase();
        String Sql = "SELECT SourceAccountNum,DesAccountNum,Amount,Type FROM Transactions";
        ResultSet resultSet =statementforDataBase.executeQuery(Sql);
        while (resultSet.next()){
            String SourceAccountNum =resultSet.getString("SourceAccountNum");
            String DesAccountNum =resultSet.getString("DesAccountNum");
            String Amount =resultSet.getString("Amount");
            String Type =resultSet.getString("Type");

                allTransactions.add(SourceAccountNum + "   " + DesAccountNum + "   " + Amount + "   " + Type);

            LVtrancastion.setItems(allTransactions);
        }

    }

    private static Connection connectionforDataBase;
    private static Statement statementforDataBase;

    private static void connectionforDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionforDataBase = DriverManager.getConnection("jdbc:sqlite:Bank.db");
            statementforDataBase = connectionforDataBase.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}