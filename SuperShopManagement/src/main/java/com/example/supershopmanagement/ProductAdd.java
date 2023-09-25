package com.example.supershopmanagement;

import backend.DBMSConnection;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductAdd {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField nameFill;
    @FXML
    private TextField catagoryFill;
    @FXML
    private TextField idFill;
    @FXML
    private TextField amountFill;
    @FXML
    private TextField priceFill;
    @FXML
    private Label msgLbl;
    private String name;

    private String catagory;

    private String id;

    private int amount;

    private double price;

    public ProductAdd(String id,String name,String catagory,String price,String amount){
        this.id=id;
        this.name=name;
        this.catagory=catagory;
        this.amount=Integer.parseInt(amount);
        this.price=Double.parseDouble(price);
    }
    public ProductAdd() {
        // Default constructor
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void insertProduct(){
        try{
            DBMSConnection dbmsConnect=new DBMSConnection("jdbc:mysql://localhost:3306/supershop","root","");

            Connection con=dbmsConnect.getConnection();
            String sql="insert into products values(?,?,?,?,?)";
            PreparedStatement stmt= con.prepareStatement(sql);

            stmt.setString(1,id);
            stmt.setString(2,name);
            stmt.setString(3,catagory);
            stmt.setDouble(4, price);
            stmt.setInt(5,amount);
            int i = stmt.executeUpdate();
            System.out.println("The value of i is "+ i);
            dbmsConnect.closeConnection(con, stmt);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    public void onAddBtnClick(){
        ProductAdd temp=new ProductAdd(idFill.getText(),nameFill.getText(),catagoryFill.getText(),priceFill.getText(),amountFill.getText());
        temp.insertProduct();
        msgLbl.setText("Product added.");
        System.out.println("Product added");
        stage.close();
    }
}
