package com.example.bankaccount;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class MainTransaction {


    @FXML
    private ListView<String> LVtransaction;

    final ObservableList<String> personaltransactions = FXCollections.observableArrayList();

    @FXML
    private Button btndeposit;

    @FXML
    private Button btndonet;

    @FXML
    private Button btnexit;

    @FXML
    private Button btntransaction;

    @FXML
    private Button btnwithdraw;

    @FXML
    private Label lblacountnum;

    @FXML
    private Label lbldeposit;

    @FXML
    private Label lbltransfer;

    @FXML
    private Label lblwithdraw;

    @FXML
    private TextField txtamount;

    @FXML
    private TextField txtamountt;

    @FXML
    private TextField txtdes;

    @FXML
    private TextField txtwithdrawamount;

    private static Connection connectionforDataBase;
    private static Statement statementforDataBase;
    static String AccNum= HelloController1.getAccNum();
    private static void connectionforDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionforDataBase = DriverManager.getConnection("jdbc:sqlite:Bank.db");
            statementforDataBase = connectionforDataBase.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void pressBtnDeposit(ActionEvent event) throws SQLException {
        if (txtamount.getText().compareTo("")==0){
            Alert alert6 = new Alert(Alert.AlertType.WARNING );
            alert6.setTitle("Warning dialog");
            alert6.setHeaderText(null);
            alert6.setContentText("please complete all the parts");
            alert6.showAndWait();
        }else {
            TransactionPage transactionPage = new TransactionPage();
            transactionPage.setSrsAccNum(AccNum);
            transactionPage.setDesAccnum("");
            transactionPage.setAmount(txtamount.getText());
            transactionPage.setType("Deposit");
            DataBase dataB = new DataBase();
            dataB.insertAllTransactions(transactionPage);
            String depositAmount= txtamount.getText();
            long DA =Long.parseLong(depositAmount);
            long CurentBalance=Long.parseLong(getBalance(AccNum));
            long UpdatedBlance=DA+CurentBalance;
            updateBalance(UpdatedBlance,AccNum);
            Alert alert6 = new Alert(Alert.AlertType.INFORMATION );
            alert6.setTitle("Warning dialog");
            alert6.setHeaderText(null);
            alert6.setContentText("DONE");
            alert6.showAndWait();

        }

    }

    @FXML
    void pressBtnDont(ActionEvent event) throws SQLException {
        if (txtdes.getText().compareTo("")==0||txtamountt.getText().compareTo("")==0){
            Alert alert11 = new Alert(Alert.AlertType.WARNING );
            alert11.setTitle("Warning dialog");
            alert11.setHeaderText(null);
            alert11.setContentText("please complete all the parts");
            alert11.showAndWait();
        }else {
            long SrcBalance=Long.parseLong(getBalance(AccNum));
            String destnationacc= txtdes.getText();
            long DesBlance= Long.parseLong(getBalance(destnationacc));
            long transfeA =Long.parseLong(txtamountt.getText());
            if (SrcBalance>=transfeA){
                TransactionPage trans =new TransactionPage();
                DataBase dbs =new DataBase();
                trans.setSrsAccNum(AccNum);
                trans.setDesAccnum(txtdes.getText());
                trans.setAmount(txtamountt.getText());
                trans.setType("transfer");
                dbs.insertAllTransactions(trans);
                long updateblance=SrcBalance-transfeA;
                long updateddes = DesBlance+transfeA;
                updateBalance(updateblance,AccNum);
                updateBalance(updateddes,destnationacc);
                Alert alert12 = new Alert(Alert.AlertType.INFORMATION );
                alert12.setTitle("Warning dialog");
                alert12.setHeaderText(null);
                alert12.setContentText("DONE");
                alert12.showAndWait();
            }else {
                Alert alert13 = new Alert(Alert.AlertType.WARNING );
                alert13.setTitle("Warning dialog");
                alert13.setHeaderText(null);
                alert13.setContentText("you balance is not enough to transfer");
                alert13.showAndWait();
            }
        }
    }

    @FXML
    void pressBtnExit(ActionEvent event) {

        Platform.exit();
    }

    @FXML
    void pressBtnTrancaction(ActionEvent event) throws SQLException {
      connectionforDataBase();
      String Sql = "SELECT SourceAccountNum,DesAccountNum,Amount,Type FROM Transactions";
      ResultSet resultSet =statementforDataBase.executeQuery(Sql);
      while (resultSet.next()){
          String SourceAccountNum =resultSet.getString("SourceAccountNum");
          String DesAccountNum =resultSet.getString("DesAccountNum");
          String Amount =resultSet.getString("Amount");
          String Type =resultSet.getString("Type");
          if (SourceAccountNum.equals(AccNum)) {
              personaltransactions.add(SourceAccountNum + "   " + DesAccountNum + "   " + Amount + "   " + Type);
          }
          LVtransaction.setItems(personaltransactions);
      }

    }

    @FXML
    void pressBtnWithDraw(ActionEvent event) throws SQLException {

        if (txtwithdrawamount.getText().compareTo("")==0){
            Alert alert8 = new Alert(Alert.AlertType.WARNING );
            alert8.setTitle("Warning dialog");
            alert8.setHeaderText(null);
            alert8.setContentText("please complete all the parts");
            alert8.showAndWait();

        }else {
            String Withdrawamount=txtwithdrawamount.getText();
            long WA = Long.parseLong(Withdrawamount);
            long curentblance=Long.parseLong(getBalance(AccNum));
            if (curentblance>=WA){
                TransactionPage Tp = new TransactionPage();
                DataBase DB = new DataBase();
                Tp.setSrsAccNum(AccNum);
                Tp.setDesAccnum("");
                Tp.setAmount(txtwithdrawamount.getText());
                Tp.setType("WithDraw");
                DB.insertAllTransactions(Tp);
                long updatedBlance=curentblance-WA;
                updateBalance(updatedBlance,AccNum);
                Alert alert9 = new Alert(Alert.AlertType.INFORMATION );
                alert9.setTitle("Warning dialog");
                alert9.setHeaderText(null);
                alert9.setContentText("DONE");
                alert9.showAndWait();
            }else {
                Alert alert10 = new Alert(Alert.AlertType.WARNING );
                alert10.setTitle("Warning dialog");
                alert10.setHeaderText(null);
                alert10.setContentText("you balance is not enough to withdraw");
                alert10.showAndWait();

            }
        }


    }

    public String getBalance(String accNum)throws SQLException {
        connectionforDataBase();
        String sql="SELECT AccountNum , Balance FROM Clients";
        String Balance="";

        ResultSet rs = statementforDataBase.executeQuery(sql);
        while (rs.next()){
            String DataAccNum= rs.getString("AccountNum");
            if (DataAccNum.equals(accNum)){
                Balance= rs.getString("Balance");
            }
        }
return Balance;
    }

    public void intialaze(){
        lblacountnum.setText(HelloController1.AccNum);
    }
    public void updateBalance(long newBalance , String accNum) throws SQLException{
        connectionforDataBase();
        String update ="UPDATE Clients set Balance='"+newBalance+"'WHERE AccountNum='"+accNum+"';";
        statementforDataBase.executeUpdate(update);
    }
}


