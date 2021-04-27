/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.User;
import services.serviceUser;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private PasswordField pass2;
    @FXML
    private PasswordField pass3;
    serviceUser su = new serviceUser();
    
    private String cemail;

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void Reset(ActionEvent event) {
        User u=new User();
        u.setPassword(pass2.getText());
        u.setEmail(cemail);
        
//        serviceUser s =new serviceUser();
        
        try {
            su.ResetPassword(u);
        }  catch (SQLException ex) {
                    ex.printStackTrace();
                }
        
    }
    
}
