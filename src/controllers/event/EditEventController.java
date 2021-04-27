/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.event;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import models.CategoryEvent;
import models.Event;
import services.ServiceEvent;
import tests.MainGUI;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EditEventController implements Initializable {

    @FXML
    private TextField tfEventEditNom;
    @FXML
    private TextArea tfEventEditDesc;
    @FXML
    private TextField tfEventEditLieu;
    @FXML
    private DatePicker dpEventEditDateDeb;
    @FXML
    private DatePicker dpEventEditDateFin;
    @FXML
    private TextField tfEventEditPrix;
    @FXML
    private TextField tfEventEditNbP;
    @FXML
    private Button EventEditButton;
    @FXML
    private ImageView eventEditImg;
    @FXML
    private Button eventEditChoose;
    private File file; 
    private String lien="";
    private int id;
    @FXML
    private ComboBox<CategoryEvent> cbEventEdit;
    @FXML
    private Button EventEditCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String  query = "SELECT * from categorie_event";
          Statement st ;
          ResultSet rs;
          ObservableList<CategoryEvent> categories = FXCollections.observableArrayList();

        try {
            st=DataSource.getInstance().getCnx().createStatement();
            rs=st.executeQuery(query);

            while(rs.next()) 
            {
                categories.add(new CategoryEvent(Integer.valueOf(rs.getString("id")),rs.getString("name")));
            }   
        } catch (SQLException ex) {
        Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbEventEdit.setItems(categories);
        
        ///////number validator//////////
        
        tfEventEditNbP.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfEventEditNbP.setText(newValue.replaceAll("[^\\d]", ""));            
            }
        });
        
        ////////price validator//////////
        
        UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {

            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {

                if (t.isReplaced()) 
                    if(t.getText().matches("[^0-9]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
                
                if (t.isAdded()) {
                    if (t.getControlText().contains(".")) {
                        if (t.getText().matches("[^0-9]")) {
                            t.setText("");
                        }
                    } else if (t.getText().matches("[^0-9.]")) {
                        t.setText("");
                    }
                }
                
                return t;
            }
        };
        tfEventEditPrix.setTextFormatter(new TextFormatter<>(filter));
    }    
    

    public void init(Event e){
        tfEventEditNom.setText(e.getNom());
        tfEventEditDesc.setText(e.getDescription());
        tfEventEditLieu.setText(e.getLieu());
        //dpEventEditDateDeb.setValue(e.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        //dpEventEditDateFin.setValue(e.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        tfEventEditPrix.setText(String.valueOf(e.getPrix()));
        tfEventEditNbP.setText(String.valueOf(e.getNbParticipants()));
        eventEditImg.setImage(new Image("file:///"+String.valueOf(e.getImg())));
        lien = e.getImg();
        id = e.getId();
    }
    
    @FXML
    private void ModifierEvent(ActionEvent event) throws IOException {
        
        if(ValidateEmptyForm(tfEventEditNom,tfEventEditDesc,tfEventEditLieu,dpEventEditDateDeb,dpEventEditDateFin,tfEventEditNbP,tfEventEditPrix,eventEditImg,cbEventEdit)
            && ValidateDateDeb(dpEventEditDateDeb) && ValidateDateFin(dpEventEditDateDeb,dpEventEditDateFin) && ValidateName(tfEventEditNom))
        {
            int category =cbEventEdit.getSelectionModel().getSelectedItem().getId();
            ServiceEvent se = new ServiceEvent();
            se.modifier(new Event(id,tfEventEditNom.getText(), tfEventEditLieu.getText(), 
                    tfEventEditDesc.getText(), Float.parseFloat(tfEventEditPrix.getText()), 
                    this.lien, Integer.parseInt(tfEventEditNbP.getText()), new Date(), 
                    Date.from(dpEventEditDateDeb.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), 
                    Date.from(dpEventEditDateFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), "enabled", 4, category));

            JOptionPane.showMessageDialog(null, "Evènement modifié !");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Show Events.fxml"));

            Parent root = loader.load();
            tfEventEditNom.getScene().setRoot(root);

            ShowEventsController dpc = loader.getController();
        }
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
            eventEditImg.setImage(imagee);
            eventEditImg.setFitWidth(200);
            eventEditImg.setFitHeight(200);
            eventEditImg.scaleXProperty();
            eventEditImg.scaleYProperty();
            eventEditImg.setSmooth(true);
            eventEditImg.setCache(true);                           

        try {
            // save image to PNG file
            this.lien=UUID.randomUUID().toString();
            File f=new File("src\\uploads\\"+"pro-" + this.lien + ".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
            
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }

    }

    @FXML
    private void cancelEdit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/Show Events.fxml"));
        
        Parent root = loader.load();
        tfEventEditNom.getScene().setRoot(root);
        
        ShowEventsController dpc = loader.getController();
    }
    
    private boolean ValidateEmptyForm(TextField nom, TextArea desc, TextField lieu, DatePicker d, DatePicker f, TextField nbp, TextField prix,ImageView img, ComboBox cat){
         if (nom.getText().equals("") || desc.getText().equals("") || lieu.getText().equals("") || 
                 d.getValue()==null || f.getValue()==null || nbp.getText().equals("") || 
                 prix.getText().equals("") || img.getImage()==null ||cat.getValue()==null) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText("Veuillez remplir tous les champs");
             alert.showAndWait();
             
             return false;  
        } else {
             return true;  
         }
     }
    
    private boolean ValidateName(TextField t){
         Pattern p = Pattern.compile("[a-zA-Z]+");
         Matcher m = p.matcher(t.getText());
         if (m.find() && m.group().equals(t.getText())){
             return true;
             
         }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(t.getText()+" : nom non valide");
             alert.showAndWait();
             
             return false;
         }
     }
    
    private boolean ValidateDateDeb(DatePicker d){
         if (d.getValue().isAfter(LocalDate.now())) {
            return true;
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(" Date début non valide");
             alert.showAndWait();
             
             return false;  
         }
     }
    
    private boolean ValidateDateFin(DatePicker d, DatePicker f){
         if (f.getValue().isAfter(d.getValue())) {
            return true;
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(" Date fin non valide");
             alert.showAndWait();
             
             return false;  
         }
     }
    
    @FXML
    private void ShowEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/event/Show Events.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
    }

    @FXML
    private void ShowShop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/product/market.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
    }
}
