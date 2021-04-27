package Controller;

import Models.Publicite;
import Services.PubliciteService;

import tray.animations.AnimationType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utiles.DataBase;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class PubliciteController implements Initializable {

    @FXML
    private StackPane rootPane;
    
    @FXML private TableView<Publicite>tablev; 
    @FXML private TableColumn<Publicite, Integer>id; 
    @FXML private TableColumn<Publicite, String>content; 
    @FXML private TableColumn<Publicite, String>image; 
    @FXML private TableColumn<Publicite, String>dateDebut; 
    @FXML private TableColumn<Publicite, String>dateFin; 
    @FXML private TableColumn<Publicite, Boolean>etat; 
    
    public ObservableList<Publicite> data =FXCollections.observableArrayList();
    
    @FXML
    private TextField titrerec;
    @FXML
    private Button ajouter;
    @FXML
    private TextField searchButton;
    @FXML
    private Button supprimer;
    @FXML
    private Button update;
    @FXML
    private Button upload;
    @FXML
    private Button searchB;
    @FXML
    private ImageView pubAddImg;
    @FXML
    private TextArea publiciteContent; 
    @FXML
    private DatePicker publiciteDateDeb;
    @FXML
    private DatePicker publiciteDateFin;
    @FXML
    private CheckBox status;
    private Connection con = DataBase.getInstance().getConnection();
    private String imagePath;
    PubliciteService service = new PubliciteService();
     Publicite selectedPub;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        supprimer.setDisable(true);
        update.setDisable(true);
    
        id.setCellValueFactory(new PropertyValueFactory<Publicite,Integer>("id"));
           
        content.setCellValueFactory(new PropertyValueFactory<Publicite,String>("content"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<Publicite,String>("dateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<Publicite,String>("dateFin"));
        etat.setCellValueFactory(new PropertyValueFactory<Publicite,Boolean>("etat"));
        
        try {
            service.readAll().forEach(pub -> {
                try{
                    data.add(pub);
                }catch(Exception e){
                    e.printStackTrace();
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
            tablev.setItems(data);
    }
    @FXML
    private void Ajouter(MouseEvent event) {
        
        
        if( !publiciteContent.getText().isEmpty() && publiciteDateFin.getValue() != null && publiciteDateDeb.getValue() != null ){
            SimpleDateFormat  formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(  status.isSelected());
           Publicite pub  =  new Publicite(1, publiciteContent.getText(), imagePath,  java.sql.Date.valueOf(publiciteDateFin.getValue()), java.sql.Date.valueOf(publiciteDateDeb.getValue()), status.isSelected());
        try {
            service.add(pub);
             data.add(pub);
            tablev.setItems(data);
            tablev.refresh();
            String title = "Congratulations";
            String message = "You've successfully Added a new Advertisement ";
       
        TrayNotification tray = new TrayNotification();
         tray.setTray(title, message, NotificationType.SUCCESS);
        tray.showAndWait();
        } catch (SQLException ex) {
            
            Logger.getLogger(PubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }
        
    }
    @FXML
    private void supprimer(MouseEvent event) {
       try {
            
            service.delete(selectedPub.getId());
            data.remove(selectedPub);
            tablev.setItems(data);
            tablev.refresh();
            File f = new File("src\\uploads\\"+selectedPub.getImage());
            f.delete();
            String title = "Congratulations";
            String message = "You've successfully Deleted your Advertisement ";
       
        TrayNotification tray = new TrayNotification();
         tray.setTray(title, message, NotificationType.SUCCESS);
        tray.showAndWait();
        } catch (SQLException ex) {
            
            Logger.getLogger(PubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void select(MouseEvent event) {
        Publicite pub = tablev.getSelectionModel().getSelectedItem();
        if(pub != null) {
         publiciteContent.setText(pub.getContent());
        status.setSelected(pub.isEtat() );
        publiciteDateDeb.setValue(pub.getDateDebut().toLocalDate());
        publiciteDateFin.setValue(pub.getDateFin().toLocalDate());
        ajouter.setDisable(false);
        supprimer.setDisable(false);
        update.setDisable(false);
        selectedPub = pub;
        imagePath = pub.getImage();
        File file = new File("src\\uploads\\"+pub.getImage());
        if(file.exists()){
             System.out.println("src\\uploads\\"+pub.getImage());
        
            BufferedImage image;
            try {
                image = ImageIO.read(file);
                         WritableImage imagee = SwingFXUtils.toFXImage(image, null);
            pubAddImg.setImage(imagee);
            pubAddImg.setFitWidth(200);
            pubAddImg.setFitHeight(200);
            pubAddImg.scaleXProperty();
            pubAddImg.scaleYProperty();
            pubAddImg.setSmooth(true);
            pubAddImg.setCache(true); 
            } catch (IOException ex) {
                Logger.getLogger(PubliciteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            pubAddImg.setImage(null);
        }
        }
   
    }
    @FXML
    private void update(MouseEvent event) {
        
    Publicite pub1 = tablev.getSelectionModel().getSelectedItem();
    
       if( !publiciteContent.getText().isEmpty() && publiciteDateFin.getValue() != null && publiciteDateDeb.getValue() != null ){
            SimpleDateFormat  formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
           Publicite pub  =  new Publicite(selectedPub.getId(), publiciteContent.getText(), imagePath,  java.sql.Date.valueOf(publiciteDateFin.getValue()), java.sql.Date.valueOf(publiciteDateDeb.getValue()), status.isSelected());
        try {
            
            service.update(selectedPub.getId(), publiciteContent.getText(), imagePath,  java.sql.Date.valueOf(publiciteDateFin.getValue()), java.sql.Date.valueOf(publiciteDateDeb.getValue()), status.isSelected());
            
            data.forEach(x -> {
                 if(x.getId() == selectedPub.getId()){
                     x.setContent(publiciteContent.getText());
                     x.setDateDebut( java.sql.Date.valueOf(publiciteDateDeb.getValue()));
                     x.setDateFin( java.sql.Date.valueOf(publiciteDateFin.getValue()));
                     x.setEtat( status.isSelected());
                 }
             });
            tablev.setItems(data);
            tablev.refresh();
             
            String title = "Congratulations";
            String message = "You've successfully Updated your Advertisement ";
       
        TrayNotification tray = new TrayNotification();
         tray.setTray(title, message, NotificationType.SUCCESS);
        tray.showAndWait();
        } catch (SQLException ex) {
            
            Logger.getLogger(PubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }
    @FXML
    private void uploadAction(MouseEvent event) {
       
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
      
        //Show open file dialog
       
       File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);
            pubAddImg.setImage(imagee);
            pubAddImg.setFitWidth(200);
            pubAddImg.setFitHeight(200);
            pubAddImg.scaleXProperty();
            pubAddImg.scaleYProperty();
            pubAddImg.setSmooth(true);
            pubAddImg.setCache(true);                           

        try {
            // save image to PNG file
            File f=new File("src\\uploads\\"+file.getName());
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
            imagePath = file.getName();
            System.out.println(f.getAbsolutePath());

        } catch (IOException ex) {
            Logger.getLogger(PubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }

    }
      
    @FXML
    private void search(Event event) throws SQLException {
        System.out.println(searchButton.getText());
        
        if(searchButton.getText().length() > 0){
            data.clear();
            data.addAll(service.readAll());
             List data1 = data.stream().filter(x -> x.getContent().contains(searchButton.getText())).collect(Collectors.toList());
             System.out.println(data.size());
             if(!data1.isEmpty()){
                 data.clear();
                 data.addAll(data1);
                  
             }
             else{
                 data.clear();
                 
             }
            tablev.setItems(data);
            tablev.refresh();
        }else if(  searchButton.getText().length() == 0){
            try {
                data.clear();
                service.readAll().forEach(pub -> {
                try{
                    data.add(pub);
                }catch(Exception e){
                    e.printStackTrace();
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
            tablev.setItems(data);
            tablev.refresh();
        }
    }
    
    
}
