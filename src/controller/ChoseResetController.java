/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.RegisterController.nb_valider;
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
import javafx.scene.control.TextField;
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
public class ChoseResetController implements Initializable {

    @FXML
    private Button phoneBoutton;
    @FXML
    private Button emailBoutton;
    @FXML
    private TextField emailReset;
    @FXML
    private TextField phoneReset;
    
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
    private void btSendPhone(ActionEvent event) {
    }

    @FXML
    private void btSendEmail(ActionEvent event)throws IOException,Exception {
        serviceUser service=new serviceUser();
        User u=service.FindByEmail(emailReset.getText());
        nb_valider = r.nextInt(10000);
        Mailing.mailingValider(emailReset.getText(), nb_valider);
        
        
        JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        String txt_CodeConfirmation = jop.showInputDialog(null, "Merci de saisir le code de verification !", "Verification Adresse Mail", JOptionPane.QUESTION_MESSAGE);
            
            if (Integer.parseInt(txt_CodeConfirmation) == nb_valider) {
  
//                try {
//                     su.register(u);
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bienvenue Mr(s) ", ButtonType.CLOSE);
                      
            alert.show();
            
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("../views/ResetPassword.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      ResetPasswordController cntr = LOADER.getController();
                      cntr.setCemail(emailReset.getText());
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
                    // redirection vers la page d'accueil
//                } 
//                catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//                lblResultat.setText("Inscription valide !!");
            }else {
              Alert alert = new Alert(Alert.AlertType.ERROR, "Code incorrect", ButtonType.CLOSE);
                alert.show();
            }
    }
    
}
