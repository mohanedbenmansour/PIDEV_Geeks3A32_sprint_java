/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Category;
import entity.Tournoi;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceCategory;
import services.ServiceTrounoi;

/**
 * FXML Controller class
 *
 * @author Fourat
 */
public class AfficherCategoryController implements Initializable {
    
    @FXML
    private TableView<Category> category;
  
    @FXML
    private TableColumn<Category,String> typecol;
    @FXML
    private Button btajouter;
    @FXML
    private Button supp;

        
            ObservableList<Category>observableList;
    @FXML
    private Button bt_tr;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        showCategory();
                
        
    }    
    public Connection getConnection(){
        Connection conn ;
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/geek","root","");
            return conn ;
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
            return null ;
        }
    }

   public ObservableList<Category> getCategoryList(){
       ObservableList<Category> categoryList = FXCollections.observableArrayList();
       Connection conn = getConnection();
       String query ="SELECT * FROM `category`";
       Statement st;
       ResultSet rs;
       
       try{
           st= conn.createStatement();
           rs=st.executeQuery(query);
           Category categorys;
           while (rs.next()){
               categorys = new Category(rs.getString("type"));
               categoryList.add(categorys);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
       return categoryList; 
   }
    public void showCategory(){
        ObservableList<Category> list = getCategoryList(); 
   
        typecol.setCellValueFactory(new PropertyValueFactory<Category,String>("type"));
        
        category.setItems(list);
    }
    
    
    
    
    
        @FXML
    void gotoAjoutCategory(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("../views/AjoutCategory.fxml"));
        
      Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
        @FXML
    void gotoAfficherTournoi(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("../views/AfficherTournoiBack.fxml"));
        
      Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
    
        @FXML
    void suppr(ActionEvent event) {
        ServiceCategory sp = new ServiceCategory();
        if (category.getSelectionModel().getSelectedItem()!=null){
        sp.supprimer(category.getSelectionModel().getSelectedItem());
        
        showCategory();
      
         observableList.clear();
          observableList = getCategoryList();
         category.setItems(observableList);
    
    }
    }
    
    
}
