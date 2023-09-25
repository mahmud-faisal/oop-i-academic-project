package com.example.supershopmanagement;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.supershopmanagement.HelloApplication.loadFXML;

public class ManagerHome {
    private Stage stage;
    private Scene scene;
    public void onProductClick(ActionEvent event) throws IOException {
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(loadFXML("Products"));
        stage.setScene(scene);
        stage.show();


    }
    public void onCustomerClick(ActionEvent event) throws IOException {
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(loadFXML("CustomerList"));
        stage.setScene(scene);
        stage.show();


    }
    public void onProfileClick(ActionEvent event) throws IOException {
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(loadFXML("Products"));
        stage.setScene(scene);
        stage.show();


    }
    public void onLogOutClick(ActionEvent event) throws IOException {
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(loadFXML("Home"));
        stage.setScene(scene);
        stage.show();


    }

}
