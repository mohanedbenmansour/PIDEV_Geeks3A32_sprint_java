/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author House
 */
public class TestRegister extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Stage stage = new Stage();
            Parent root = FXMLLoader
        .load(getClass().getResource("../views/Register.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Hello World!");
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
