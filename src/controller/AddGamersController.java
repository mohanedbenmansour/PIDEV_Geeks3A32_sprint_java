/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import models.Gamers;
import services.serviceGamer;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AddGamersController implements Initializable {

    @FXML
    private TextField tfGamerAddName;
    @FXML
    private TextField tfGamerAddAge;
    @FXML
    private TextArea tfGamerAddDesc;
    @FXML
    private ImageView GamerAddImg;
    @FXML
    private TextField tfGamerAddTwitch;
    @FXML
    private TextField tfGamerAddFb;
    @FXML
    private TextField tfGamerAddTeam;
    @FXML
    private Button GamerAddButton;
    @FXML
    private Button GamerAddChoose;
    private File file; 
    private String lien="";
    @FXML
    private Button GamerAddCancel;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        String  query = "SELECT * from categorie_event";
//          Statement st ;
//          ResultSet rs;
//          ObservableList<CategoryEvent> categories = FXCollections.observableArrayList();
//
//        try {
//            st=DataSource.getInstance().getCnx().createStatement();
//            rs=st.executeQuery(query);
//
//            while(rs.next()) 
//            {
//                categories.add(new CategoryEvent(Integer.valueOf(rs.getString("id")),rs.getString("name")));
//            }   
//        } catch (SQLException ex) {
//        Logger.getLogger(AddGamersController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        cbEventAdd.setItems(categories);
    }  
      
    
    @FXML
    private void UploadImageActionPerformed(ActionEvent gamer) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);
            GamerAddImg.setImage(imagee);
            GamerAddImg.setFitWidth(200);
            GamerAddImg.setFitHeight(200);
            GamerAddImg.scaleXProperty();
            GamerAddImg.scaleYProperty();
            GamerAddImg.setSmooth(true);
            GamerAddImg.setCache(true);                           

        try {
            // save image to PNG file
            File f=new File("src\\uploads\\"+tfGamerAddName.getText()+tfGamerAddAge.getText()+".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
            
           this.lien=f.getAbsolutePath();
           
            System.out.println(this.lien);

        } catch (IOException ex) {
            Logger.getLogger(AddGamersController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }

    }

    @FXML
    private void AjouterGamer(ActionEvent gamer) throws IOException {
//        int category =cbEventAdd.getSelectionModel().getSelectedItem().getId();
        
        serviceGamer se = new serviceGamer();
        se.ajouter(new Gamers(tfGamerAddName.getText(), tfGamerAddAge.getText(), 
                tfGamerAddDesc.getText(),this.lien, tfGamerAddTwitch.getText(), 
                 tfGamerAddFb.getText(),tfGamerAddTeam.getText()));
        
        JOptionPane.showMessageDialog(null, "gamer ajouté !");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ShowGamers.fxml"));
        
        Parent root = loader.load();
        tfGamerAddName.getScene().setRoot(root);
        
        ShowGamersController dpc = loader.getController();
        
    }

    @FXML
    private void cancelAdd(ActionEvent gamer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ShowGamers.fxml"));
        
        Parent root = loader.load();
        tfGamerAddName.getScene().setRoot(root);
        
        ShowGamersController dpc = loader.getController();
    }
    
    
    
}
