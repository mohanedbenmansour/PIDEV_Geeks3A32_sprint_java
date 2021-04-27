/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import entity.Category;
import entity.Tournoi;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import mail.Mailing;
import services.ServiceTrounoi;
import util.Connector;

/**
 * FXML Controller class
 *
 * @author Fourat
 */
public class AjoutTournoiController implements Initializable {

    private TextField tfdescription;
    private TextField tfadresse;
    private TextField tfnbmax;
    private TextField tfnomT;
    @FXML
    private Button ok;
    @FXML
    private TextField tfd;
    @FXML
    private TextField tfa;
    @FXML
    private TextField tfnb;
    @FXML
    private TextField tfn;
    @FXML
    private DatePicker datepick;
    @FXML
    private ComboBox<Category> comboCat;
    @FXML
    private Button bt_img;
    @FXML
    private ImageView img;
    private File file; 
    private String lien="";
    @FXML
    private TextField tfYoutube;
    
    private String Temail;

    public String getTemail() {
        return Temail;
    }

    public void setTemail(String Temail) {
        this.Temail = Temail;
    }
    
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String  query = "SELECT * from category";
          Statement st ;
          ResultSet rs;
          ObservableList<Category> categories = FXCollections.observableArrayList();

        try {
            st=Connector.getInstance().getCnx().createStatement();
            rs=st.executeQuery(query);

            while(rs.next()) 
            {
                categories.add(new Category(Integer.valueOf(rs.getInt("id")),rs.getString("type")));
            }   
        } catch (SQLException ex) {
        Logger.getLogger(AjoutTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboCat.setItems(categories);
    }
    
       @FXML
    private void UploadImage(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

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

        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);
            img.setImage(imagee);
            img.setFitWidth(200);
            img.setFitHeight(200);
            img.scaleXProperty();
            img.scaleYProperty();
            img.setSmooth(true);
            img.setCache(true);                           

        try {
            File f=new File("src\\uploads\\"+tfn.getText()+tfa.getText()+".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
            
           this.lien=f.getAbsolutePath();
           
            System.out.println(this.lien);

        } catch (IOException ex) {
            Logger.getLogger(AjoutTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }

    }
    
     @FXML
    private void AjouterTournoi(ActionEvent event) throws IOException, Exception {
        int Category =comboCat.getSelectionModel().getSelectedItem().getId();
        
        ServiceTrounoi sp = new ServiceTrounoi();
 
        sp.ajouter(new Tournoi(tfn.getText(),
                tfd.getText(),tfa.getText(), new Date(),
                Date.from(datepick.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Integer.parseInt(tfnb.getText()),
                Category,this.lien,tfYoutube.getText(),1,1)
                
        );
        
                Mailing.mailingValider("lahmarfourat22@gmail.com", tfn.getText());

        
        JOptionPane.showMessageDialog(null, "Tournois ajoutée !");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AfficherTournoi.fxml"));
        
        Parent root = loader.load();
        tfn.getScene().setRoot(root);
        
    }
    
    
}
