/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import models.CategoryEvent;
import models.Event;
import services.ServiceCategoryEvent;
import services.ServiceEvent;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class CategoryEventBackController implements Initializable {

    @FXML
    private Button EventsBack;
    private final ObservableList<CategoryEvent> catEventList = FXCollections.observableArrayList();

    @FXML
    private Button CategoryEventsBack;
    @FXML
    private TableView<CategoryEvent> tvCatEvents;
    @FXML
    private Button btnCatEventDelete;
    @FXML
    private Label eventShowTitle;
    @FXML
    private TextField CatEventSearch;
    @FXML
    private TextField tfCatEventName;
    @FXML
    private Button btnCatEventAdd;
    @FXML
    private TableColumn<?, ?> CategoryColNom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCatEvents();
        
        FilteredList<CategoryEvent> filter = new FilteredList<>(catEventList, b->true);
        CatEventSearch.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(category -> {
            if(newValue.isEmpty() || newValue==null) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(category.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            }else 
                return false;
        });
        });
        SortedList<CategoryEvent> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tvCatEvents.comparatorProperty());
        
        tvCatEvents.setItems(sort);
    }    

    @FXML
    private void GoToEvents(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/EventsBack.fxml"));
        
        Parent root = loader.load();
        eventShowTitle.getScene().setRoot(root);
        
        EventsBackController aec = loader.getController();
    }

    ObservableList<CategoryEvent> list ;

    public ObservableList<CategoryEvent> getCatEventsList(){
        Connection cnx = DataSource.getInstance().getCnx();
        String Query = "SELECT * FROM categorie_event";
        Statement st;
        ResultSet rs;

        try{    
            st= cnx.createStatement();
            rs = st.executeQuery(Query);
            CategoryEvent cats;
            while(rs.next())
            {
            cats = new CategoryEvent(rs.getInt("id"),
                    rs.getString("name"));
           catEventList.add(cats);
            }
        } catch (Exception ex) {
        ex.printStackTrace();
        }
        return catEventList;
    }
    
    public void showCatEvents(){
       
       list = getCatEventsList();
       CategoryColNom.setCellValueFactory((new PropertyValueFactory <>("nom") ));
       
       tvCatEvents.setItems(list);
   } 
    
    @FXML
    private void deleteCat(ActionEvent event) {
        CategoryEvent c = tvCatEvents.getSelectionModel().getSelectedItem();
        ServiceCategoryEvent sce = new ServiceCategoryEvent();
        sce.supprimer(c);
        JOptionPane.showMessageDialog(null, "Categorie supprimée");
        
        list.clear();
        showCatEvents();
    }

    @FXML
    private void AddCat(ActionEvent event) {
        ServiceCategoryEvent sce = new ServiceCategoryEvent();
        sce.ajouter(new CategoryEvent(tfCatEventName.getText()));
        JOptionPane.showMessageDialog(null, "Categorie ajouté !");
        list.clear();
        showCatEvents();
        tfCatEventName.setText("");
            
    }
    
}
