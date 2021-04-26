/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.base;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import models.Invoice;
import models.Ordre;
import services.InvoiceService;

/**
 * FXML Controller class
 *
 * @author mohan
 */
public class InvoiceController implements Initializable {
  
      @FXML
    private TextField searchField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField subtotalField;
    @FXML
    private TextField taxField;
 
    
  
    @FXML
    private Button generateInvoice;
    @FXML
    private Button updateInvoicebtn;
    @FXML
    private Button deleteInvoicebtn;
    @FXML
    private Button sortbtn;
    @FXML
    private TableColumn<Invoice, Integer> idCol;
    @FXML
    private TableColumn<Invoice, String> nameCol;
    @FXML
    private TableColumn<Invoice, String> emailCol;
    @FXML
    private TableColumn<Invoice, Integer> phoneCol;
    @FXML
    private TableColumn<Invoice, String> dateCol;
    @FXML
    private TableColumn<Invoice, Integer> totalCol;
    @FXML
    private TableColumn<Invoice, Integer> test;
  @FXML
    private TableView<Invoice> invoiceTable;
ObservableList<Invoice>  InvoiceList = FXCollections.observableArrayList();
InvoiceService invoiceService=new InvoiceService();
    /**
     * Initializes the controller class.
     */


   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
        searchField.textProperty().addListener((observable, oldValue, newValue) ->
    dynamiSearch(newValue)
);
        
    }    

  

    @FXML
    private void addInvoice(ActionEvent event) {

       String name=nameField.getText();
       String email=emailField.getText();
              

        String phone=phoneField.getText();
         

        String  subtotal=subtotalField.getText();
        String tax=taxField.getText();
        String total="10";
        
        String date="4/20/2021";

 
    Invoice invoice=new Invoice( name, email, Integer.parseInt(phone), Integer.parseInt(subtotal),Integer.parseInt(tax),Integer.parseInt(total),date);

    invoiceService.ajouter(invoice);
     

    refreshTable();
    }
    @FXML
    private void refreshTable() {
        
         ObservableList<Invoice> list = invoiceService.afficher();
         
        //idCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("phone"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("creationDate"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("totalCost"));


      invoiceTable.setItems(list);
    }
     @FXML
    private void sortInvoice() {
     
         ObservableList<Invoice> list = invoiceService.afficherParOrdre();
         
        //idCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("phone"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("creationDate"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("totalCost"));


      invoiceTable.setItems(list);
    
    
    }
    
     @FXML
      private void updateInvoice(){
         //String id=idInvoice.getText();
     String name=nameField.getText();
       String email=emailField.getText();
              

        String phone=phoneField.getText();
         

        String  subtotal=subtotalField.getText();
        String tax=taxField.getText();
        String total="10";
        
        String date="4/20/2021";

    Invoice invoice=new Invoice( 1,name, email, Integer.parseInt(phone), Integer.parseInt(subtotal),Integer.parseInt(tax),Integer.parseInt(total),date);
invoiceService.modifier(invoice);
     

    refreshTable();
      
      }
    
    
        private void dynamiSearch(String value){
            if (value.isEmpty()){
                refreshTable();
            }else{
      ObservableList<Invoice> list = invoiceService.rechercher(value);
         
       // idCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("phone"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("creationDate"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("totalCost"));


      invoiceTable.setItems(list);}
    }
        
         private void test(){
        refreshTable();
     Callback<TableColumn<Invoice, String> , TableCell<Invoice, String>> cellFoctory;
        cellFoctory = (TableColumn<Invoice, String> param) -> {
            final TableCell<Invoice, String> cell;
         cell = new TableCell<Invoice, String>() {
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
                     Button editIcon=new Button("pdf");
                     deleteIcon.setStyle(
                             " -fx-cursor: hand ;"
                                     + "-glyph-size:28px;"
                                     + "-fx-fill:#ff1744;"
                     );
                     editIcon.setStyle(
                             " -fx-cursor: hand ;"
                                     + "-glyph-size:28px;"
                                     + "-fx-fill:#00E676;"
                     );
                      deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                Invoice invoice = invoiceTable.getSelectionModel().getSelectedItem();
                                
invoiceService.supprimer(invoice);
                       
                                refreshTable();
                      
                      });
                      
                         editIcon.setOnMouseClicked((MouseEvent event) -> {
                                Invoice invoice = invoiceTable.getSelectionModel().getSelectedItem();
                             /*   nameField.setText(invoice.getName());
       emailField.setText(invoice.getEmail());
        phoneField.setText(invoice.getPhone());
       subtotalField.setText(invoice.getSubtotal());
       taxField.setText(invoice.getTax());*/
        

                       
                      
                      });
                                
                           
                     
                     HBox managebtn = new HBox(editIcon, deleteIcon);
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
