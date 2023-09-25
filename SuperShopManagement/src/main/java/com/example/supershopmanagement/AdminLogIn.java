package com.example.supershopmanagement;



import backend.LogInCheck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static com.example.supershopmanagement.HelloApplication.loadFXML;
import static java.awt.Color.*;


public class AdminLogIn {
    @FXML
    private TextField adminId;
    @FXML
    private TextField adminPassword;
    @FXML
    private Label caution;
    private String id;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String password;
    private Stage stage;
    private Scene scene;

    public  AdminLogIn(){

    }
    public  AdminLogIn(String id,String password){
      this.id=id;
      this.password=password;
    }

    public  void sendToBackEnd(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        AdminLogIn ali=new AdminLogIn(adminId.getText(),adminPassword.getText());
        LogInCheck d=new LogInCheck();
        boolean checked=d.checkInBE(ali.getId() ,ali.getPassword(),"ADMIN");
        if(checked){

            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(loadFXML("ProductSell"));
                stage.setScene(scene);
                stage.show();



        }
        else{
         caution.setText("Wrong User Id or Password");

        }

    }


}
