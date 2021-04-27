/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Tournoi;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import util.Connector;

/**
 * FXML Controller class
 *
 * @author Fourat
 */
public class AfficheTController implements Initializable {

    @FXML
    private WebView WebView;
    @FXML
    private Label lbnom;
    @FXML
    private Label lbdateP;
    @FXML
    private Label lbdateT;
    @FXML
    private ImageView imageT;
    @FXML
    private Label lbdesc;
    @FXML
    private Label lbCat;
    @FXML
    private Button bt_back;
    @FXML
    private Button bt_part;
    @FXML
    private Label lbadr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
   
   
    }
    
      public void init(Tournoi t) throws SQLException{
        lbnom.setText(t.getNom());
        lbdesc.setText(t.getDescription());
        lbadr.setText(t.getAdresse());
        lbdateP.setText("Publié le : "+String.valueOf(t.getDate_publication()));
        lbdateT.setText(String.valueOf(t.getDate_tournoi()));
        
             Connection cnx = Connector.getInstance().getCnx();
            PreparedStatement ps;
            ps = cnx.prepareStatement("SELECT type FROM category WHERE id = ?");
            ps.setString(1, String.valueOf(t.getCategory_Id()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            lbCat.setText(rs.getString(1));
        
            imageT.setImage(new Image("file:///"+String.valueOf(t.getImage())));

           String str = t.getLien_youtube();
           char[] ch2 = str.toCharArray();
           String  str2 = "";
           String  str3 = "";
      
                for (int i = 0; i < str.length(); i++) {
                     if (ch2[i]== '=')
            
                    str2 = str.substring(i,str.length());   
      }
      
      str3 = str2.substring(1,str2.length());
            
         WebView.getEngine().load(
            "https://www.youtube.com/embed/"+str3
             );
          WebView.setPrefSize(320, 210);    
       
    }
          @FXML
    void gotoAfficherTournoi(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("../views/AfficherTournoi.fxml"));
        
      Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
  
    @FXML
    private void GeneratePdf(ActionEvent event) {
        try {
            System.out.println("Telechargement du pdf");
            String dir = System.getProperty("user.dir");

            OutputStream file = new FileOutputStream(new File(dir + lbnom.getText()+".pdf"));


            Document document = new Document();

            PdfWriter.getInstance(document, file);


            document.open();
            

            document.addTitle("Participation au Tournoi");
            document.add(new Paragraph("Vous vous etes inscrit au tournoi "+lbnom.getText()+" pour le "+lbdateT.getText()));
            document.add(new Paragraph("Pour plus de detail : "));
            document.add(new Paragraph(lbdesc.getText()));
            document.add(new Paragraph("L'adresse est : "+lbadr.getText()));
            document.add(new Paragraph("Category : "+lbCat.getText()));
            
           // document.add(new Paragraph(new Date().toString()));


            document.close();

            file.close();
            System.out.println("pdf telechargé ");


        } catch (Exception e) {


            e.printStackTrace();

        }

    }
}