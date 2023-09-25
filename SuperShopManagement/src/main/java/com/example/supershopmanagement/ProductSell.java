package com.example.supershopmanagement;

import backend.DBMSConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;


import static com.example.supershopmanagement.HelloApplication.*;

public class ProductSell {
    private Stage stage;
    private Scene scene;


    @FXML
    private javafx.scene.control.TextField nameFill;
    @FXML
    private javafx.scene.control.TextField contactFill;
    @FXML
    private javafx.scene.control.TextField idFill;
    @FXML
    private javafx.scene.control.TextField counterFill;
    @FXML
    private javafx.scene.control.TextField payFill;
    @FXML
    private javafx.scene.control.Label msgLbl;

    @FXML
    private TextField contactFilll,payFilll,counterFilll,idFilll;



    private String name,contact,id,counter,date,pay,trxnNo;


        public ProductSell(){

        }

        public ProductSell(String name,String contact,String id,String counter,String pay){
            this.name=name;
            this.contact=contact;
            this.id=id;
            this.pay=pay;
            this.counter=counter;
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            this.date=  currentDate.format(formatter);
            this.trxnNo=generateTrxnId();

        }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void insertSold(){
        try{
            DBMSConnection dbmsConnect=new DBMSConnection("jdbc:mysql://localhost:3306/supershop","root","");

            Connection con=dbmsConnect.getConnection();
            String sql="insert into sold_product values(?,?,?,?,?)";
            PreparedStatement stmt= con.prepareStatement(sql);

            stmt.setString(1,date);
            stmt.setString(2,name);
            stmt.setString(3,contact);
            stmt.setString(4,trxnNo);
            stmt.setDouble(5, Double.parseDouble(pay));
            int i = stmt.executeUpdate();
            System.out.println("The value of i is "+ i);
            dbmsConnect.closeConnection(con, stmt);
            updateProductTable(id,1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String generateTrxnId(){
        String trxn="8391KXTR";
        String alp="ASDFGHJKLQWERTYUIOPZXCVBNM";
        String num="1234567890";
        Random rndm=new Random();
        int x= rndm.nextInt(1000)+1;

        return (trxn+x);
    }
@FXML
    public void onProductClick(ActionEvent event) throws IOException {
        stage=new Stage();
        scene=new Scene(loadFXML("Products"));
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void onCustomerClick(ActionEvent event) throws IOException {
        stage=new Stage();
        scene=new Scene(loadFXML("CustomerList"));
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void onProfileClick(ActionEvent event) throws IOException {
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(loadFXML("Products"));
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void onLogOutClick(ActionEvent event) throws IOException {
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(loadFXML("Home"));
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void onConfirmBtnClick(){
        ProductSell temp=new ProductSell(nameFill.getText(),contactFill.getText(),idFill.getText(),counterFill.getText(),payFill.getText());
        temp.insertSold();
        msgLbl.setText("Product Sold");
        System.out.println("Product added");

    }
    public void updateProductTable(String id,int unit) throws SQLException, ClassNotFoundException {
        DBMSConnection dbmsConnect=new DBMSConnection("jdbc:mysql://localhost:3306/supershop","root","");

        Connection con=dbmsConnect.getConnection();
        int amnt=0;


        String selectQuery = "SELECT AMOUNT FROM products WHERE ID = ?";




        PreparedStatement statement = con.prepareStatement(selectQuery);
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
             amnt = resultSet.getInt("Amount");


        } else {
            System.out.println("No matching rows found.");
        }



            String sql = "UPDATE products SET AMOUNT = ? WHERE ID = ?";



            int updated = amnt-1;

            PreparedStatement statements = con.prepareStatement(sql);
            statements.setInt(1, updated);

            statements.setString(2, id);
        System.out.println("OKK");
            int rowsUpdated = statements.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Value updated successfully!");
            } else {
                System.out.println("No rows were updated.");
            }
            dbmsConnect.closeConnection(con,statements);
        }
    }


