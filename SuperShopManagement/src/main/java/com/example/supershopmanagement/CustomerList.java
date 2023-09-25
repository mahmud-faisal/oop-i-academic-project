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


public class CustomerList implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<CustomerClass> customerTable;

    @FXML
    private TableColumn<CustomerClass,String> nameCol;
    @FXML
    private TableColumn<CustomerClass,String> contactCol;
    @FXML
    private TableColumn<CustomerClass,String> lastBuyDateCol;
    @FXML
    private TableColumn<CustomerClass,String> lastBuyCol;
    @FXML
    private TableColumn<CustomerClass,String> totalBuyCol;
    private String query=null;
    private PreparedStatement stmt=null;
    ResultSet rs=null;


    ObservableList<CustomerClass> customers= FXCollections.observableArrayList();
//    ObservableList<ProductAdd> productsList= FXCollections.observableArrayList();

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
        DBMSConnection dbmsConnect=new DBMSConnection("jdbc:mysql://localhost:3306/supershop","root","");
        dbmsConnect.getConnection();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        lastBuyDateCol.setCellValueFactory(new PropertyValueFactory<>("lastBuyDate"));
        lastBuyCol.setCellValueFactory(new PropertyValueFactory<>("lastBuy"));
        totalBuyCol.setCellValueFactory(new PropertyValueFactory<>("totalBuy"));

        query="SELECT * FROM CUSTOMER";
        stmt=dbmsConnect.getConnection().prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            customers.add(new CustomerClass(
                              // Retrieving data from DB
                    rs.getString("NAME"),
                    rs.getString("CONTACT"),
                    rs.getString("LAST_BUY_DATE"),
                    rs.getString("LAST_BUY_AMOUNT"),
                    rs.getString("TOTAL_BUY")
                    ));
        }
        customerTable.setItems(customers);


    }





    public void onProductClick(ActionEvent event) throws IOException {
        stage=new Stage();
        scene=new Scene(loadFXML("ProductAdd"));
        stage.setScene(scene);
        stage.show();


    }
}
