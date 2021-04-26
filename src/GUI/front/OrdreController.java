/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.front;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.OrderDetail;
import models.Ordre;
import services.OrdreService;

/**
 * FXML Controller class
 *
 * @author mohan
 */
public class OrdreController implements Initializable {

    @FXML
    private TextField adressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField codeField;
    @FXML
    private Button ordrebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
OrdreService ordreService=new OrdreService();
    @FXML
    private void addOrder(ActionEvent event) {
       String adress=adressField.getText();
       String phone=phoneField.getText();
       String city=cityField.getText();
       String state=stateField.getText();    
       String code=codeField.getText();    
      

 
    Ordre ordre=new Ordre("1", adress,"4/20/2021",
                Integer.parseInt(phone), true,city, state,Integer.parseInt(code),
                222);
 OrderDetail oD= new OrderDetail(1,"20",1,20);
    ordreService.ajouter(ordre,oD);

    }
    
}
