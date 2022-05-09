package com.example.bankaccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationPage {

    @FXML
    private Button btndon;

    @FXML
    private Button btnverfy;

    @FXML
    private Label lblrp;

    @FXML
    private Label lblverfy;

    @FXML
    private TextField txtFN;

    @FXML
    private TextField txtbd;

    @FXML
    private TextField txtcun;

    @FXML
    private TextField txtfather;

    @FXML
    private TextField txtgn;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtinam;

    @FXML
    private TextField txtln;

    @FXML
    private TextField txtpass;

    @FXML
    private TextField txtphone;

    @FXML
    void pressBtnVerfy(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnverfy.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 900, 530);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    void pressDone(ActionEvent event) {

        if (txtFN.getText().compareTo("")==0||txtln.getText().compareTo("")==0||txtbd.getText().compareTo("")==0||txtgn.getText().compareTo("")==0||txtid.getText().compareTo("")==0||txtcun.getText().compareTo("")==0||txtfather.getText().compareTo("")==0||txtpass.getText().compareTo("")==0||txtphone.getText().compareTo("")==0||txtinam.getText().compareTo("")==0){
            Alert alert = new Alert(Alert.AlertType.WARNING );
            alert.setTitle("Warning dialog");
            alert.setHeaderText(null);
            alert.setContentText("please complete all the parts");
            alert.showAndWait();
        }else {
            Person client = new Person();
            client.setFirstName(txtFN.getText());
            client.setLastName(txtln.getText());
            client.setBirthDate(txtbd.getText());
            client.setGender(txtgn.getText());
            client.setCountry(txtcun.getText());
            client.setFatherName(txtfather.getText());
            client.setPhoneNumber(txtphone.getText());
            client.setPassWord(txtpass.getText());
            client.setInitialAmount(txtinam.getText());
            client.setiD(txtid.getText());
            DataBase db = new DataBase();
            db.insertClient(client,client.getiD());
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION );
            alert2.setTitle("Information dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("successfully registered");
            alert2.showAndWait();
        }
    }




}
