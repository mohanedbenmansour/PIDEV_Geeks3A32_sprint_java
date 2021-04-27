/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Models.Category;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Fourat
 */
public class PubliciteTypeController implements Initializable {

    @FXML
    private TextField tfType;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCategory(ActionEvent event) throws IOException {
       /* ServiceCategory sp = new ServiceCategory();
        sp.ajouter(new Category(tfType.getText()));
        
        JOptionPane.showMessageDialog(null, "Category ajoutée !");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AfficherCategory.fxml"));
        
        Parent root = loader.load();
        tfType.getScene().setRoot(root);*/
        
       
        
    }
    }
    

