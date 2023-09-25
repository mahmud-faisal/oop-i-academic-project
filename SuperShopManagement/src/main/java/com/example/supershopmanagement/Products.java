package com.example.supershopmanagement;

import backend.DBMSConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.supershopmanagement.HelloApplication.*;


public class Products implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<ProductAdd> productTable;
    @FXML
    private TableColumn<ProductAdd,String> idCol;
    @FXML
    private TableColumn<ProductAdd,String> nameCol;
    @FXML
    private TableColumn<ProductAdd,String> catagoryCol;
    @FXML
    private TableColumn<ProductAdd,String> priceCol;
    @FXML
    private TableColumn<ProductAdd,String> amountCol;
    private String query=null;
    private PreparedStatement stmt=null;
    ResultSet rs=null;


    ObservableList<ProductAdd> productsList= FXCollections.observableArrayList();

   public void initialize(URL url, ResourceBundle rb){
       try {
           loadData();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }

    private void loadData() throws SQLException, ClassNotFoundException {
       DBMSConnection con=new DBMSConnection("jdbc:mysql://localhost:3306/supershop","root","");
       con.getConnection();
       idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
       nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       catagoryCol.setCellValueFactory(new PropertyValueFactory<>("catagory"));
       priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
       amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

       query="SELECT * FROM PRODUCTS";
       stmt=con.getConnection().prepareStatement(query);
       rs = stmt.executeQuery();
       while(rs.next()){
           productsList.add(new ProductAdd(
                   rs.getString("ID"),          // Retrieving data from DB
                   rs.getString("NAME"),
                   rs.getString("CATAGORY"),
                   rs.getString("PRICE"),
                   rs.getString("AMOUNT")));
       }
        productTable.setItems(productsList);

    }





    public void onProductClick(ActionEvent event) throws IOException {
        stage=new Stage();
        scene=new Scene(loadFXML("ProductAdd"));
        stage.setScene(scene);
        stage.show();


    }
}
