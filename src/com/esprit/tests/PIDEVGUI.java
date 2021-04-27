/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author aissa
 */
public class PIDEVGUI extends Application {

    public static Stage pStage = new Stage();

    @Override
    public void start(Parent root) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("../views/product/market.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("../views/product/index.fxml"));
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
