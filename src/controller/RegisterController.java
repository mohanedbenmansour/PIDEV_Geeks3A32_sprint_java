/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mailing.Mailing;
import models.User;
import services.serviceUser;
import views.MainLoginController;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class RegisterController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField pseudo;
    @FXML
    private PasswordField confirmMdp;
    @FXML
    private PasswordField mdp;
    @FXML
    private TextField mail;
    @FXML
    private Button inscrire;
    @FXML
    private Button annuler;
    @FXML
    private DatePicker date;
    @FXML
    private Button vider;
    @FXML
    private ComboBox<?> type;
    @FXML
    private Button modifier;
    
    Random r = new Random();
    static int nb_valider;
    serviceUser su = new serviceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException,Exception{
        User u=new User();
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setUsername(pseudo.getText());
        u.setEmail(mail.getText());
        u.setPassword(mdp.getText());
        serviceUser s =new serviceUser();
        
        
        nb_valider = r.nextInt(10000);
        Mailing.mailingValider(mail.getText(), nb_valider);
        
        
        JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        String txt_CodeConfirmation = jop.showInputDialog(null, "Merci de saisir le code de verification !", "Verification Adresse Mail", JOptionPane.QUESTION_MESSAGE);
            
            if (Integer.parseInt(txt_CodeConfirmation) == nb_valider) {
  
                try {
                     su.register(u);
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bienvenue Mr(s) "+ nom.getText() , ButtonType.CLOSE);
                      
            alert.show();
            
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("../views/MainLogin.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      MainLoginController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
                    // redirection vers la page d'accueil
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
//                lblResultat.setText("Inscription valide !!");
            }else {
              Alert alert = new Alert(Alert.AlertType.ERROR, "Code incorrect", ButtonType.CLOSE);
                alert.show();
            }
        
        
//        try{
//        s.register(u);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
        }
        
    }
    

