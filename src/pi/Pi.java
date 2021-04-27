/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import javafx.application.Application;
import services.ServiceCategory;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fourat
 */
public class Pi extends Application {

   
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent  root;
        root = FXMLLoader.load(getClass().getResource("../views/AfficherTournoi.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
//////          Scanner sc = new Scanner(System.in);
////        ServiceTrounoi sp = new ServiceTrounoi();
//           ServiceCategory sp = new ServiceCategory();
//////         
//////          System.out.println("donner une category : ");
//////          String ty= sc.nextLine();
//////        sp.ajouter(new Category(ty));
//        sp.afficher().forEach(System.out::println);
//////          //Category t;
           launch(args);
       
       
    }
}

