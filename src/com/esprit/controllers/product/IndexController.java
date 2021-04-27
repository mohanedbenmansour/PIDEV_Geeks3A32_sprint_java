/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers.product;

import com.esprit.models.CategoryProduct;
import com.esprit.models.Product;
import com.esprit.models.Publicity;
import com.esprit.services.impl.ServiceCategoryProductImpl;
import com.esprit.services.impl.ServiceProductImpl;
import com.esprit.tests.PIDEVGUI;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class IndexController implements Initializable {

    @FXML
    private TableView<Product> products;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, String> description;
    @FXML
    private TableColumn<Product, String> price;
    @FXML
    private TableColumn<Product, String> categorie;
    @FXML
    private TextField tfrecherche;
    private Product selectedProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceProductImpl service = new ServiceProductImpl();
        ObservableList OL = FXCollections.observableArrayList(service.afficher());
        products.setItems(OL);
        name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        description.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        price.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        categorie.setCellValueFactory(new PropertyValueFactory<Product, String>("categorie"));
    }    

    @FXML
    private void SelectItem(MouseEvent event) {
                selectedProduct = products.getSelectionModel().getSelectedItem();

    }


    @FXML
    private void redirectTonewProduct(ActionEvent event) throws IOException {
         formController a = new formController();
        a.setProduct(null);
        Parent root = FXMLLoader.load(getClass().getResource("../../views/product/form.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
    }

    @FXML
    private void Delete(ActionEvent event) {
        int reply = JOptionPane.showConfirmDialog(null, "Do you want delete this item?", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            ServiceProductImpl service = new ServiceProductImpl();
            service.supprimer(selectedProduct);
            this.initialize(null, null);
            JOptionPane.showMessageDialog(null, "Produit supprimé !");
        }
    }

    @FXML
    private void Edit(ActionEvent event) throws IOException {
          if (selectedProduct.getId() != 0) {
            formController a = new formController();
            a.setProduct(selectedProduct);
            Parent root = FXMLLoader.load(getClass().getResource("../../views/product/form.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
        }
    }

    @FXML
    private void Search(KeyEvent event) {
         String rech=tfrecherche.getText();
        
        ServiceProductImpl service = new ServiceProductImpl();
        ObservableList OL = FXCollections.observableArrayList(service.recherche(rech));
        products.setItems(OL);
    }

    @FXML
    private void showCategory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/productcategory/index.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
    }

    @FXML
    private void showProduct(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/product/index.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
    }
    
}
