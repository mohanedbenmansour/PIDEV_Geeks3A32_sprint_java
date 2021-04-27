/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers.product;

import com.esprit.models.Product;
import com.esprit.models.Publicity;
import com.esprit.services.impl.ServiceCategoryProductImpl;
import com.esprit.services.impl.ServiceProductImpl;
import com.esprit.tests.PIDEVGUI;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class formController implements Initializable {

    @FXML
    private TextArea tfDescription;
    @FXML
    private TextField tfName;
    @FXML
    private Label ltitre;
    @FXML
    private ImageView ImagePub;
    @FXML
    private TextField Imagepath;
    @FXML
    private Label titreerror;
    @FXML
    private Label descriptionerror;
    @FXML
    private Label imageerror;
    String filename = "";
    private static Product product;
    @FXML
    private TextField tfPrice;
    @FXML
    private ComboBox<String> cbCategory;
    @FXML
    private Label categoryerror;
    @FXML
    private Label priceerror;
    private Boolean toEditImage = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCategoryProductImpl service = new ServiceCategoryProductImpl();
        cbCategory.getItems().addAll(service.getCategoryNameAndId());
        titreerror.setVisible(false);
        descriptionerror.setVisible(false);
        imageerror.setVisible(false);
        categoryerror.setVisible(false);
        priceerror.setVisible(false);

        if (product != null) {
            filename = product.getFileName();
            ltitre.setText("Modifier un produit");
            tfName.setText(product.getName());
            tfDescription.setText(product.getDescription());
            tfPrice.setText(product.getPrice());
            if (product.getCategory_id() != 0) {
                cbCategory.setValue(product.getCategory_id() + "-" + product.getCategorie());
            }
            Image im = new Image("http://localhost/images/" + product.getFileName());
            ImagePub.setImage(im);
        }
    }

    @FXML
    private void SaveProduct(ActionEvent event) throws IOException {
        if (this.checkFields()) {
            int category_id = 0;

            if (cbCategory.getValue() != null) {
                category_id = Integer.valueOf(cbCategory.getValue().substring(0, cbCategory.getValue().indexOf("-")));
            }

            if (product != null) {
                ServiceProductImpl sp = new ServiceProductImpl();
                Product p = new Product(product.getId(), tfName.getText(), tfPrice.getText(), tfDescription.getText(), category_id);
                if (toEditImage) {
                    p.setFileName(filename);
                } else {
                    p.setFileName("");

                }
                sp.modifier(p);
                JOptionPane.showMessageDialog(null, "Produit modifiée !");
            } else {
                ServiceProductImpl sp = new ServiceProductImpl();
                Product p = new Product(tfName.getText(), tfPrice.getText(), tfDescription.getText(), category_id);
                p.setFileName(filename);
                sp.ajouter(p);
                JOptionPane.showMessageDialog(null, "Produit ajoutée !");
            }

            Parent root = FXMLLoader.load(getClass().getResource("../../views/product/index.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
        }
    }

    @FXML
    private void OpenFileMan(ActionEvent event) throws IOException {
        toEditImage = true;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            filename = "pro-" + UUID.randomUUID().toString() + ".png";

            Imagepath.setText(file.getAbsolutePath());
            Path source = Paths.get(Imagepath.getText());
            Path dest = Paths.get("C:\\wamp64\\www\\images\\" + filename);
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
            Image im = new Image("http://localhost/images/" + filename);
            ImagePub.setImage(im);
        }
    }

    public static Product getProduct() {
        return product;
    }

    public static void setProduct(Product product) {
        formController.product = product;
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

    public boolean checkFields() {
        titreerror.setVisible(false);
        descriptionerror.setVisible(false);
        imageerror.setVisible(false);
        categoryerror.setVisible(false);
        priceerror.setVisible(false);
        Boolean isvalid = true;
        if (tfName.getText().isEmpty()) {
            titreerror.setVisible(true);
            isvalid = false;
        }
        if (tfDescription.getText().isEmpty()) {
            descriptionerror.setVisible(true);
            isvalid = false;
        }
        if (tfPrice.getText().isEmpty()) {
            priceerror.setVisible(true);
            isvalid = false;
        }
        if (cbCategory.getValue() == null) {
            categoryerror.setVisible(true);
            isvalid = false;
        }
        if (filename.isEmpty()) {
            imageerror.setVisible(true);
            isvalid = false;

        }

        return isvalid;
    }

}
