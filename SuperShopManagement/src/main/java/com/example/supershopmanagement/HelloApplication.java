package com.example.supershopmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        scene=new Scene(loadFXML("Home"));
        stage.setTitle("FRD");
        stage.setScene(scene);
        stage.show();
    }
    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource(fxml+".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }
}