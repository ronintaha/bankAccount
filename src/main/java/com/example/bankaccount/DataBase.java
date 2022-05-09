package com.example.bankaccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static Connection connectionforDataBase;
    private static Statement statementforDataBase;

    DataBase() {
        connectionforDataBase();
        creatTableforClients();
        createTableforTransactions();

    }


    private static void connectionforDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionforDataBase = DriverManager.getConnection("jdbc:sqlite:Bank.db");
            statementforDataBase = connectionforDataBase.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void creatTableforClients() {
        String tableSQL = "CREATE TABLE IF NOT EXISTS Clients (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,Firstname TEXT,Lastname TEXT , Birthdate TEXT,Gender TEXT,Country TEXT,nc TEXT,Fathername TEXT ,Phonenumber TEXT,Password TEXT , Balance TEXT,AccountNum TEXT );";
        try {
            statementforDataBase.executeUpdate(tableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertClient(Person client, String number) {
        connectionforDataBase();
        String id = number + "";
        String Firstname = client.getFirstName();
        String LastName = client.getLastName() + "";
        String BirthDate = client.getBirthDate() + "";
        String Gender = client.getGender() + "";
        String Country = client.getCountry() + "";
        String ID = client.getiD() + "";
        String FatherName = client.getFatherName() + "";
        String Phonenumber = client.getPhoneNumber()+ "";
        String Password = client.getPassWord() + "";
        String InitialAmount = client.getInitialAmount() + "";
        String AccountNum = client.creatAccountNumber();


        String insertSQL = "INSERT INTO Clients (id ,Firstname,Lastname,Birthdate,Gender,Country,nc,FatherName ,PhoneNumber,Password,Balance,AccountNum) VALUES ('" + id + "','" + Firstname + "','" + LastName + "','" + BirthDate + "','" + Gender + "','" + Country + "','" + ID + "','" + FatherName + "','" + Phonenumber + "','" + Password + "','" + InitialAmount + "','" + AccountNum + "');";
        try {
            statementforDataBase.executeUpdate(insertSQL);
            System.out.println("inserted person");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage() + "person");
            e.printStackTrace();
        }

    }

    private void createTableforTransactions() {

        String tableSQL = "CREATE TABLE IF NOT EXISTS Transactions (SourceAccountNum TEXT,DesAccountNum TEXT , Amount TEXT,Type TEXT);";
        try {
            statementforDataBase.executeUpdate(tableSQL);
            System.out.println("Client Info table is created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void insertAllTransactions(TransactionPage transaction) {
        connectionforDataBase();
        String SourceAccountNum =transaction.getSrsAccNum();
        String DesAccountNum = transaction.getDesAccnum()+ "";
        String Amount= transaction.getAmount()+ "";
        String Type = transaction.getType();
//

        String insertSQL = "INSERT INTO Transactions(SourceAccountNum,DesAccountNum,Amount,Type) VALUES ('" + SourceAccountNum + "','" + DesAccountNum + "','" +Amount + "','" + Type + "');";
        try {
            statementforDataBase.executeUpdate(insertSQL);
            System.out.println("inserted person");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage() + "person");
            e.printStackTrace();
        }

    }
}




