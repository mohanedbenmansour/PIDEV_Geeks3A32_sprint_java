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
import javafx.scene.image.Image;
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
public class EditGamersController implements Initializable {

    @FXML
    private TextField tfGamerEditName;
    @FXML
    private TextField tfGamerEditAge;
    @FXML
    private TextArea tfGamerEditDesc;
    private ImageView gamerEditImg;    
    @FXML
    private TextField tfGamerEditTwitch;
    @FXML
    private TextField tfGamerEditFacebook;
    @FXML
    private TextField tfGamerEditTeam;
    private File file; 
    private String lien="";
    private int id;
 
//    @FXML
//    private DatePicker dpEventEditDateDeb;
//    @FXML
//    private DatePicker dpEventEditDateFin;
//    @FXML
//    private TextField tfEventEditNbP;
    @FXML
    private Button EventEditButton;
    @FXML
    private ImageView eventEditImg;
    @FXML
    private Button gamerEditChoose;
    @FXML
    private Button EventEditCancel;

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
//        cbEventEdit.setItems(categories);
    }    
    

    public void init(Gamers e){
        tfGamerEditName.setText(e.getName());
        tfGamerEditDesc.setText(e.getAge());
        tfGamerEditAge.setText(e.getDescription());
        //dpEventEditDateDeb.setValue(e.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        //dpEventEditDateFin.setValue(e.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        gamerEditImg.setImage(new Image("file:///"+String.valueOf(e.getPlayer_photo())));
        lien = e.getPlayer_photo();
        tfGamerEditTwitch.setText(String.valueOf(e.getGamer_twitch()));
        tfGamerEditFacebook.setText(String.valueOf(e.getGamer_facebook()));
        tfGamerEditTeam.setText(String.valueOf(e.getGamer_team()));
        
        
        id = e.getId();
    }
    
    @FXML
    private void ModifierGamer(ActionEvent event) throws IOException {
//        int category =cbEventEdit.getSelectionModel().getSelectedItem().getId();
        serviceGamer se = new serviceGamer();
        se.modifier(new Gamers(id,tfGamerEditName.getText(), tfGamerEditAge.getText(), 
                tfGamerEditDesc.getText(), 
                this.lien, tfGamerEditTwitch.getText(), tfGamerEditFacebook.getText(), tfGamerEditTeam.getText()));
        
        JOptionPane.showMessageDialog(null, "Gamer modifié !");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ShowGamers.fxml"));
        
        Parent root = loader.load();
        tfGamerEditName.getScene().setRoot(root);
        
        ShowGamersController dpc = loader.getController();
    }

    @FXML
    private void UploadImage2ActionPerformed(ActionEvent event) {
        
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
            gamerEditImg.setImage(imagee);
            gamerEditImg.setFitWidth(200);
            gamerEditImg.setFitHeight(200);
            gamerEditImg.scaleXProperty();
            gamerEditImg.scaleYProperty();
            gamerEditImg.setSmooth(true);
            gamerEditImg.setCache(true);                           

        try {
            // save image to PNG file
            File f=new File("src\\uploads\\"+tfGamerEditName.getText()+tfGamerEditAge.getText()+".png");
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
    private void cancelEdit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ShowGamers.fxml"));
        
        Parent root = loader.load();
        tfGamerEditName.getScene().setRoot(root);
        
        ShowGamersController dpc = loader.getController();
    }

    
}
