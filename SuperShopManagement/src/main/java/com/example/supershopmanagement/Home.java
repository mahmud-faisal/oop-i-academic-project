package com.example.supershopmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.supershopmanagement.HelloApplication.loadFXML;

public class Home {
    private Stage stage;
    private Scene scene;


    @FXML
    public void onManagerClick(ActionEvent event) throws IOException {
//        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         stage=new Stage();
        scene=new Scene(loadFXML("OwnerLogIn"));
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void onAdminClick(ActionEvent event) throws IOException {
        stage=new Stage();
        scene=new Scene(loadFXML("AdminLogIn"));
        stage.setScene(scene);
        stage.show();

    }





}
