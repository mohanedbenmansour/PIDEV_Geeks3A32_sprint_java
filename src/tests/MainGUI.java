/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */


public class MainGUI extends Application {
    
    public static Stage pStage = new Stage();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
        Parent  root = FXMLLoader.load(getClass().getResource("../views/event/Show Events.fxml"));
        //Parent  root = FXMLLoader.load(getClass().getResource("../views/event/EventsBack.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("PIDEV");
        primaryStage.setScene(scene);
        primaryStage.show();
        pStage = primaryStage;
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
