/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import pidevfx.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.User;

/**
 *
 * @author Lenovo
 */
public class TestLogin extends Application {
    
    static User Userconnected = new User();
    @Override
    public void start(Stage primaryStage) throws Exception {
      Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../views/MainLogin.fxml"));
      primaryStage.setTitle("StudentRecSys");
      primaryStage.setScene(new Scene(root, 650.0D, 400.0D));
      primaryStage.show();
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
