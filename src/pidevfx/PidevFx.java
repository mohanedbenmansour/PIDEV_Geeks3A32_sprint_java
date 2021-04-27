/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class PidevFx extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
      Parent  root = FXMLLoader.load(getClass().getClassLoader().getResource("../views/RechercheaAvancee.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("PIDEV");
        primaryStage.setScene(scene);
        primaryStage.show();
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
