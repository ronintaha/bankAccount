package com.example.bankaccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController1 {
    private static Connection connectionforDataBase;
    private static Statement statementforDataBase;
    static String AccNum;
    @FXML
    private RadioButton btnclient;

    @FXML
    private Button btndone;

    @FXML
    private RadioButton btnmanager;

    @FXML
    private Button btnsignup;

    @FXML
    private ImageView imgdol;

    @FXML
    private ImageView imgpass;

    @FXML
    private ImageView imguser;

    @FXML
    private Label lblSignin;

    @FXML
    private Label lblWelcom;

    @FXML
    private Label lblnothaveaccount;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtusername;

    @FXML
    void pressBtnDone(ActionEvent event) throws IOException, SQLException {

        boolean check = false;
        if (btnmanager.isSelected()==false&&btnclient.isSelected()==false||txtusername.getText().equals("")||txtusername.getText().equals("")){

            Alert alert6 = new Alert(Alert.AlertType.WARNING );
            alert6.setTitle("Warning dialog");
            alert6.setHeaderText(null);
            alert6.setContentText("please complete all the parts");
            alert6.showAndWait();
        }else {
            if (btnclient.isSelected()){

                connectionForDataBase();
                String sql ="SELECT nc, Password ,AccountNum FROM Clients";
                boolean check2=false;
                ResultSet rs =statementforDataBase.executeQuery(sql);
                while (rs.next()){
                    String username= rs.getString("nc");
                    String password= rs.getString("Password");
                    if (txtusername.getText().compareTo(username)==0&&txtpassword.getText().compareTo(password)==0){
                        AccNum= rs.getString("AccountNum");
                        check2=true;
                        Alert alert4 = new Alert(Alert.AlertType.INFORMATION );
                        alert4.setTitle("hey yoo");
                        alert4.setHeaderText(null);
                        alert4.setContentText("Welcome");
                        alert4.showAndWait();
                        Stage stage =(Stage) btndone.getScene().getWindow();
                        stage.close();
                        Stage primaryStage=new Stage();
                        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("MainTransaction.fxml"));
                        Scene scene = new Scene(root,800,500);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        break;
                    }if (check2==false){
                        Alert alert5 = new Alert(Alert.AlertType.WARNING );
                        alert5.setTitle("Warning dialog");
                        alert5.setHeaderText(null);
                        alert5.setContentText("Username or password is wrong");
                        alert5.showAndWait();
                        txtpassword.setText("");
                        txtusername.setText("");
                    }
                }
            }
            if (btnmanager.isSelected()){
                if (txtusername.getText().equals("mohammadTaha")&&txtpassword.getText().equals("123")){
                    check =true;
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION );
                    alert2.setTitle("hey yoo");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Welcome");
                    alert2.showAndWait();
                    Stage stage =(Stage) btndone.getScene().getWindow();
                    stage.close();
                    Stage primaryStage=new Stage();
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("ManagerPage.fxml"));
                    Scene scene = new Scene(root,800,700);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }if (check==false){
                    Alert alert3 = new Alert(Alert.AlertType.WARNING );
                    alert3.setTitle("Warning dialog");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Username or password is wrong");
                    alert3.showAndWait();
                    txtpassword.setText("");
                    txtusername.setText("");
                }
            }
        }
        connectionforDataBase.close();
    }

    @FXML
    void pressBtnSignUp(ActionEvent event) throws IOException {
        Stage stage =(Stage) btnsignup.getScene().getWindow();
        stage.close();
        Stage primaryStage=new Stage();
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("RegistrationPage.fxml"));
        Scene scene = new Scene(root,600,750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void connectionForDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionforDataBase = DriverManager.getConnection("jdbc:sqlite:Bank.db");
            statementforDataBase =  connectionforDataBase.createStatement();
            System.out.println("Connection Created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static String getAccNum(){
        return AccNum;
    }
}
