/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Category;
import entity.Tournoi;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import services.ServiceTrounoi;
import util.Connector;

/**
 * FXML Controller class
 *
 * @author Fourat
 */
public class UpdateTournoiController implements Initializable {

    @FXML
    private TextField tfmd;
    @FXML
    private TextField tfma;
    @FXML
    private TextField tfmnb;
    @FXML
    private TextField tfmn;
    @FXML
    private Button ok;
    private ComboBox<Category> combomCat;
    private ImageView mimg;

    @FXML
    private TextField tfmYoutube;
    private int id ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

     public void init(Tournoi t) throws SQLException{
        tfmn.setText(t.getNom());
        tfmd.setText(t.getDescription());
        tfma.setText(t.getAdresse());
       
        tfmnb.setText(String.valueOf(t.getNb_max()));
        tfmYoutube.setText(t.getLien_youtube());
      
        id = t.getId();
       
    }
     
    
    
    
    @FXML
    private void modifierTournoi(ActionEvent event) throws IOException {
        
              String query = "UPDATE tournoi Set  description = '"+tfmd.getText()+"', adresse = '"+
            tfma.getText()+"', nb_max = "+tfmnb.getText()+",lien_youtube = '"+tfmYoutube.getText()+"' WHERE nom = '"+tfmn.getText()+"'";
        
              
              executeQuery(query);
        
        JOptionPane.showMessageDialog(null, "Tournoi modifié !");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AfficherTournoi.fxml"));
        
        Parent root = loader.load();
        tfmn.getScene().setRoot(root);
        
        AfficherTournoiController atc = loader.getController();
        
    }

     public Connection getConnection(){
        Connection conn ;
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/geeks","root","");
            return conn ;
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
            return null ;
        }
    }
     private void executeQuery(String query){
       Connection conn=getConnection();
       Statement st;
       try{
           st=conn.createStatement();
           st.executeUpdate(query);
       }catch(Exception ex){
                      ex.printStackTrace();

       }
   }
    
}
