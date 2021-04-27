/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers.productcategory;

import com.esprit.models.CategoryProduct;
import com.esprit.services.impl.ServiceCategoryProductImpl;
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
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class IndexController implements Initializable {

    @FXML
    private TableView<CategoryProduct> categories;
    @FXML
    private TableColumn<CategoryProduct, String> name;
    @FXML
    private TextField tfName;
    private static CategoryProduct categorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCategoryProductImpl service = new ServiceCategoryProductImpl();
        ObservableList OL = FXCollections.observableArrayList(service.afficher());
        categories.setItems(OL);
        name.setCellValueFactory(new PropertyValueFactory<CategoryProduct, String>("name"));
    }    

    @FXML
    private void SelectItem(MouseEvent event) {
                categorie = categories.getSelectionModel().getSelectedItem();
                tfName.setText(categorie.getName());

    }

    @FXML
    private void redirectTonewCategory(ActionEvent event) {
                    ServiceCategoryProductImpl service = new ServiceCategoryProductImpl();
                    service.ajouter(new CategoryProduct(tfName.getText()));
                    JOptionPane.showMessageDialog(null, "Categorie ajoutée !");
                    this.initialize(null, null);
                    tfName.setText("");

    }

    @FXML
    private void Delete(ActionEvent event) {
         int reply = JOptionPane.showConfirmDialog(null, "Do you want delete this item?", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            ServiceCategoryProductImpl service = new ServiceCategoryProductImpl();
            service.supprimer(categorie);
                                this.initialize(null, null);

           
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
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
