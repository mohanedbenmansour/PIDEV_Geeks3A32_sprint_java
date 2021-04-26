/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.base;



import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
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
    private TableColumn<Ordre, String> test;
    @FXML
    private Button sortbtn;
    @FXML
    private TextField searchField;
   
    @FXML
    private Button pdfbtn;
     @FXML
    private TableColumn<Ordre, String> editCol;
 @FXML
    private TableView<Ordre> orderTable;
 OrdreService ordreService=new OrdreService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //refreshTable();
        test();
        searchField.textProperty().addListener((observable, oldValue, newValue) ->
           
                dynamiSearch(newValue)

);
    }    
    
    
    @FXML
    private void sortOrder(ActionEvent event) {
        
        
   
         ObservableList<Ordre> list = ordreService.afficherParOrdre();
         
      //  idCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("id"));
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
         
      //  idCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Ordre,String>("checkoutDate"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("userPhone"));
        zipCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("zipcode"));
       
        cityCol.setCellValueFactory(new PropertyValueFactory<Ordre,String>("city"));
priceCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("totalPrice"));

      orderTable.setItems(list);
        
    }
   
    private void dynamiSearch(String value){
    System.out.println(value);
        if(value.isEmpty()){
        refreshTable();
        }else{
        ObservableList<Ordre> list = ordreService.rechercher(value);
         
       // idCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Ordre,String>("checkoutDate"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("userPhone"));
        zipCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("zipcode"));
       
        cityCol.setCellValueFactory(new PropertyValueFactory<Ordre,String>("city"));
priceCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("totalPrice"));

      orderTable.setItems(list);
    }}
    
    
    
    
    
    
    private void test(){
        refreshTable();
     Callback<TableColumn<Ordre, String> , TableCell<Ordre, String>> cellFoctory;
        cellFoctory = (TableColumn<Ordre, String> param) -> {
            final TableCell<Ordre, String> cell;
         cell = new TableCell<Ordre, String>() {
             @Override
             public void updateItem(String item, boolean empty) {
                 super.updateItem(item, empty);
                 //that cell created only on non-empty rows
                 if (empty) {
                     setGraphic(null);
                     setText(null);
                     
                 } else {
                    // FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                     //FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                   
                     Button deleteIcon=new Button("delete");
                     Button pdfIcon=new Button("pdf");
                     deleteIcon.setStyle(
                             " -fx-cursor: hand ;"
                                     + "-glyph-size:28px;"
                                     + "-fx-fill:#ff1744;"
                     );
                     pdfIcon.setStyle(
                             " -fx-cursor: hand ;"
                                     + "-glyph-size:28px;"
                                     + "-fx-fill:#00E676;"
                     );
                      deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                Ordre ordre = orderTable.getSelectionModel().getSelectedItem();
                                
ordreService.supprimer(ordre);
                       
                                refreshTable();
                      
                      });
                      
                         pdfIcon.setOnMouseClicked((MouseEvent event) -> {
                                Ordre ordre = orderTable.getSelectionModel().getSelectedItem();
                                
ordreService.orderPdf(ordre);
                       
                      
                      });
                                
                           
                     
                     HBox managebtn = new HBox(pdfIcon, deleteIcon);
                     managebtn.setStyle("-fx-alignment:center");
                     /*  HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                     HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));*/
                     
                     setGraphic(managebtn);
                     
                     setText(null);
                 }
                 
             }
             
         };
            return cell;
            
        };
        System.out.println(cellFoctory);
  test.setCellFactory(cellFoctory);
        
    }


    
}