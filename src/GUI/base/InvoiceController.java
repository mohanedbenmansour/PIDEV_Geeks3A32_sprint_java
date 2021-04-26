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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import models.Invoice;
import services.InvoiceService;

/**
 * FXML Controller class
 *
 * @author mohan
 */
public class InvoiceController implements Initializable {
    @FXML
    private TextField idInvoice;
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
    private TextField totalCostField;
    
    @FXML
    private Button searchbtn;
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
         
        idCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("id"));
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
         
        idCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("phone"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("creationDate"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("totalCost"));


      invoiceTable.setItems(list);
    
    
    }
     @FXML
    private void deleteInvoice(){
Invoice invoice=new Invoice();

      String id=idInvoice.getText();
        invoice.setId(Integer.parseInt(id));

invoiceService.supprimer(invoice);
     

    refreshTable();
    }
     @FXML
      private void updateInvoice(){
         String id=idInvoice.getText();
     String name=nameField.getText();
       String email=emailField.getText();
              

        String phone=phoneField.getText();
         

        String  subtotal=subtotalField.getText();
        String tax=taxField.getText();
        String total="10";
        
        String date="4/20/2021";

    Invoice invoice=new Invoice( Integer.parseInt(id),name, email, Integer.parseInt(phone), Integer.parseInt(subtotal),Integer.parseInt(tax),Integer.parseInt(total),date);
invoiceService.modifier(invoice);
     

    refreshTable();
      
      }
    @FXML
    private void searchInvoice() {
        
         ObservableList<Invoice> list = invoiceService.rechercher("invoice");
         
        idCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("phone"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("creationDate"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("totalCost"));


      invoiceTable.setItems(list);
    }
    
        private void dynamiSearch(String value){
            if (value.isEmpty()){
                refreshTable();
            }else{
      ObservableList<Invoice> list = invoiceService.rechercher(value);
         
        idCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("phone"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Invoice,String>("creationDate"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("totalCost"));


      invoiceTable.setItems(list);}
    }
}
