/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.base;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Ordre;
import services.OrdreService;

/**
 * FXML Controller class
 *
 * @author mohan
 */
public class OrdreController implements Initializable {

    @FXML
    private TableColumn<Ordre, Integer> idCol;
    @FXML
    private TableColumn<Ordre, String> dateCol;
    @FXML
    private TableColumn<Ordre, Integer> phoneCol;
    @FXML
    private TableColumn<Ordre, Integer> zipCol;
    @FXML
    private TableColumn<Ordre, String> cityCol;
    @FXML
    private TableColumn<Ordre, Integer> priceCol;
    @FXML
    private Button sortbtn;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchbtn;
    @FXML
    private Button pdfbtn;
 @FXML
    private TableView<Ordre> orderTable;
 OrdreService ordreService=new OrdreService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshTable();
    }    
    
    
    @FXML
    private void sortOrder(ActionEvent event) {
        
        
   
         ObservableList<Ordre> list = ordreService.afficherParOrdre();
         
        idCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Ordre,String>("checkoutDate"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("userPhone"));
        zipCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("zipcode"));
       
        cityCol.setCellValueFactory(new PropertyValueFactory<Ordre,String>("city"));
priceCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("totalPrice"));

      orderTable.setItems(list);
    }

  

    @FXML
    private void pdfOrder(ActionEvent event) {
     
    }
      @FXML
    private void refreshTable() {
        
        
         ObservableList<Ordre> list = ordreService.afficher();
         
        idCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Ordre,String>("checkoutDate"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("userPhone"));
        zipCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("zipcode"));
       
        cityCol.setCellValueFactory(new PropertyValueFactory<Ordre,String>("city"));
priceCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("totalPrice"));

      orderTable.setItems(list);
        
    }
    @FXML
    private void searchOrder(){
        
         ObservableList<Ordre> list = ordreService.rechercher(searchField.getText());
         
        idCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Ordre,String>("checkoutDate"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("userPhone"));
        zipCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("zipcode"));
       
        cityCol.setCellValueFactory(new PropertyValueFactory<Ordre,String>("city"));
priceCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("totalPrice"));

      orderTable.setItems(list);
    }
}
