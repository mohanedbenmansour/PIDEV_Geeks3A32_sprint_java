package views;

import utils.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.User;
import models.Admin;
import services.serviceUser;
import services.serviceAdmin;
//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;

public class MainLoginController {
   @FXML
   private TextField tfEmailID;
   @FXML
   private PasswordField pfPassword;
   private ChoiceBox cbUser;
    @FXML
    private Label lab_con;

   
   @FXML
   private void loginButtonClick(Event event) throws SQLException {
      
         String userName = this.tfEmailID.getText().trim();
         String password = this.pfPassword.getText();
         
         Parent teacherParent;
         Scene teacherScene;
         Stage teacherStage;
         
            if (this.isValidCredentials(userName, password)) {
               
                  User user = new User();
                  user.setUsername(userName);
//                  System.out.println("valid Credentials");
               
               
            }
         
      

   }

   private boolean isValidCredentials(String userName, String password) throws SQLException {
      boolean userPassOk = false;
//      if(userType.equals("User"))
//      {

      serviceAdmin ServiceAdmin =new serviceAdmin();
      Admin admin=ServiceAdmin.login(userName, password);
      //System.out.println("isvalidcred");
      if(admin.getId()!=-1)
      {
//          System.out.println("admin connecté");
          FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../views/ShowGamers.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
           Stage nStage = new Stage();
        nStage.setScene(scene);
 nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
      }
      
      serviceUser ServiceUser =new serviceUser();
      User user=ServiceUser.login(userName, password);
      
      
      
      
      

      
         if (user != null) {
            userPassOk = true;
            
         }
         else 
         {
             userPassOk = false;
         }
      

//      if (!userPassOk) {
//         NotificationType notificationType = NotificationType.ERROR;
//         TrayNotification tray = new TrayNotification();
//         tray.setTitle("Wrong Information");
//         tray.setMessage("Incorrect email or password");
//         tray.setNotificationType(notificationType);
//         tray.showAndDismiss(Duration.millis(3000.0D));
//         this.tfEmailID.clear();
//         this.pfPassword.clear();
//         userPassOk = false;
//      }
//      }
      
//      if(userType.equals("Admin"))
//      {
      
      
      
      

      
         if (admin != null) {
            userPassOk = true;
         }
         else 
         {
             userPassOk = false;
         }
      

//      }

      return userPassOk;
   }

//   private boolean isAllFieldFillup() {
//      boolean fillup;
//      if (!this.tfEmailID.getText().trim().isEmpty() && !this.pfPassword.getText().isEmpty()) {
//         fillup = true;
//      } else {
//         NotificationType notificationType = NotificationType.INFORMATION;
//         TrayNotification tray = new TrayNotification();
//         tray.setTitle("Attention!!!");
//         tray.setMessage("Email or Password should not Empty.");
//         tray.setNotificationType(notificationType);
//         tray.showAndDismiss(Duration.millis(3000.0D));
//         fillup = false;
//      }
//
//      return fillup;
//   }

    @FXML
    private void GoToRegister(MouseEvent event) {
    }

    @FXML
    private void Chose_reset(MouseEvent event) {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../views/ChoseReset.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
           Stage nStage = new Stage();
        nStage.setScene(scene);
 nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
