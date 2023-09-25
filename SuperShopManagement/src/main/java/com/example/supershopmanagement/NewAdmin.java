package com.example.supershopmanagement;


import backend.DBMSConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NewAdmin{
    public NewAdmin() {
        // Initialize any required variables or perform necessary setup
    }
    @FXML
    private TextField firstNameFill;
    @FXML
    private TextField lastNameFill;
    @FXML
    private DatePicker dobFill;
    @FXML
    private TextField counterFill;
    @FXML
    private TextField designationFill;
    @FXML
    private TextField contactFill;
    @FXML
    private TextField emergencyFill;
    @FXML
    private TextField emailFill;
    @FXML
    private TextField passwordFill;
    @FXML
    private Label out;
    ////////////////////collectors////////////////////////////
    @FXML
    private String firstName;
    @FXML
    private String lastName;
    @FXML
    private String dob;
    @FXML
    private String counter;
    @FXML
    private String designation;
    @FXML
    private String contact;
    @FXML
    private String emergency;
    @FXML
    private String email;
    @FXML
    private String password;



    public NewAdmin(String firstName, String lastName, LocalDate dob, String counter, String designation, String email, String contact, String emergency, String password){

        this.lastName=firstName+" " + lastName;
        this.dob= String.valueOf(dob);
        this.counter=counter;
        this.email=email;
        this.contact=contact;
        this.emergency=emergency;
        this.password=password;
        this.designation= designation;
    }
    public void insertAdminData(){
        try{
            DBMSConnection dbmsConnect=new DBMSConnection("jdbc:mysql://localhost:3306/supershop","root","");

            Connection con=dbmsConnect.getConnection();
            String sql="insert into admin values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt= con.prepareStatement(sql);

            String getIdQuery = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'supershop' AND TABLE_NAME = 'admin'";
            Statement getIdStmt = con.createStatement();
            ResultSet rs = getIdStmt.executeQuery(getIdQuery);
            int nextId = 1; // Default initial ID value

            if (rs.next()) {
                nextId = rs.getInt("AUTO_INCREMENT");
            }
            stmt.setInt(1,nextId);

            stmt.setString(2,lastName);
            stmt.setString(3,counter);
            stmt.setString(4, designation);
            stmt.setString(5,contact);
            stmt.setString(6,email);
            stmt.setString(7,contact);
            stmt.setString(8,emergency);
            stmt.setString(9,password);
            int i = stmt.executeUpdate();
            System.out.println("The value of i is "+ i);
            dbmsConnect.closeConnection(con, stmt);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void onSave(){

        NewAdmin newAdmin=new NewAdmin(firstNameFill.getText(),lastNameFill.getText(),dobFill.getValue(),counterFill.getText(),designationFill.getText(),emailFill.getText(),contactFill.getText(),emergencyFill.getText(),passwordFill.getText());
        newAdmin.insertAdminData();
    }



}

