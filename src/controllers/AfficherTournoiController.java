/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Category;
import entity.Tournoi;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceCategory;
import services.ServiceTrounoi;

/**
 * FXML Controller class
 *
 * @author Fourat
 */
public class AfficherTournoiController implements Initializable {

    @FXML
    private TableView<Tournoi> tournoi;
    @FXML
    private TableColumn<Tournoi, String> colnom;
    @FXML
    private TableColumn<Tournoi, String> coldesc;
    @FXML
    private TableColumn<Tournoi, String> coladresse;
    @FXML
    private TableColumn<Tournoi, Integer> colnbmax;
    
    
    @FXML
    private Button bt_ajout;
    @FXML
    private Button bt_mod;
    @FXML
    private Button bt_supp;
    
    
        ObservableList<Tournoi>observableList;
    private TextField tfnomT;
    private TextField tfdescription;
    private TextField tfadresse;
    private TextField tfnbmax;
   
   
    @FXML
    private TextField filterField;

  Tournoi tournois;
    @FXML
    private Button bt_tri;
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private Button bt_aff;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showTournoi();
        
        
       ObservableList<Tournoi> list = getTournoiList(); 
        colnom.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nom"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("description"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("adresse"));
        colnbmax.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("nb_max"));
       


        
        
      ObservableList<Tournoi> dataList = FXCollections.observableArrayList(list);

        FilteredList<Tournoi> filteredData= new FilteredList<>(dataList,b->true);
            filterField.setOnKeyReleased(e->{
            filterField.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate((Predicate<? super Tournoi>)Tournoi->{
                  if(newValue == null || newValue.isEmpty()){
                      return true ;
                  }  
                  
                  String lowerCaseFilter= newValue.toLowerCase();
                  
                  if(Tournoi.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1){
                      return true ;
                  }
                  else if(Tournoi.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1){
                       return true ;   
                  }else if (Tournoi.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1)
                        return true  ; 
                        else
                            return false;
                });
            });
            SortedList<Tournoi> SortedData = new SortedList<>(filteredData);
            SortedData.comparatorProperty().bind(tournoi.comparatorProperty());
            tournoi.setItems(SortedData);
            });
              
            
    }    
    
    
     public Connection getConnection(){
        Connection conn ;
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/geeks","root","");
            return conn ;
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
            return null ;
        }
    }

   public ObservableList<Tournoi> getTournoiList(){
       ObservableList<Tournoi> tournoiList = FXCollections.observableArrayList();
       Connection conn = getConnection();
       String query ="SELECT * FROM `tournoi` where active = 1";
       Statement st;
       ResultSet rs;
       
       try{
           st= conn.createStatement();
           rs=st.executeQuery(query);
           Tournoi tournois;
           while (rs.next()){
               tournois = new Tournoi(rs.getString("nom"),rs.getString("description"),rs.getString("adresse"),rs.getDate("date_publication"),rs.getDate("date_tournoi"),rs.getInt("nb_max"),rs.getInt("category_id"),rs.getString("image"), rs.getString("lien_youtube"),rs.getInt("active"),rs.getInt("user_t_id"));
               tournoiList.add(tournois);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
       return tournoiList; 
   }
   
   
    public void showTournoi(){
        ObservableList<Tournoi> list = getTournoiList(); 
        colnom.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nom"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("description"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("adresse"));
        colnbmax.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("nb_max"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_tournoi"));

       


        
        tournoi.setItems(list);
    }
    
 
        @FXML
        private void AjouterTournoi(ActionEvent event) throws IOException {
       
                 Parent root = FXMLLoader.load(getClass().getResource("../views/AjoutTournoi.fxml"));
        
                Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
                }
        
           @FXML
    private void update(ActionEvent event) throws IOException, SQLException {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/UpdateTournoi.fxml"));
        
        Parent root = loader.load();
        Scene scene = new Scene(root); 
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        
        UpdateTournoiController atc = loader.getController();
        Tournoi T = tournoi.getSelectionModel().getSelectedItem();
        atc.init(T);   
        
    }

         
           
   public ObservableList<Tournoi> getTournoiListTrier(){
          ObservableList<Tournoi> tournoiList = FXCollections.observableArrayList();
       Connection conn = getConnection();
       String query ="SELECT * FROM `tournoi` where active = 1 order by nb_max";
       Statement st;
       ResultSet rs;
       
       try{
           st= conn.createStatement();
           rs=st.executeQuery(query);
           Tournoi tournois;
           while (rs.next()){
               tournois = new Tournoi(rs.getString("nom"),rs.getString("description"),rs.getString("adresse"),rs.getDate("date_publication"),rs.getDate("date_tournoi"),rs.getInt("nb_max"),rs.getInt("category_id"),rs.getString("image"), rs.getString("lien_youtube"),rs.getInt("active"),rs.getInt("user_t_id"));
               tournoiList.add(tournois);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
              return tournoiList; 

    }
           @FXML
    private void Trier(ActionEvent event) {
        
      ObservableList<Tournoi> list = getTournoiListTrier(); 
        colnom.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nom"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("description"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("adresse"));
        colnbmax.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("nb_max"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_tournoi"));


        
        tournoi.setItems(list);
    }

        
        
        
        
      @FXML
    private void supp(ActionEvent event) {
        ServiceTrounoi sp = new ServiceTrounoi();
        if (tournoi.getSelectionModel().getSelectedItem()!=null){
        sp.supprimer(tournoi.getSelectionModel().getSelectedItem());
       showTournoi();
      
        observableList.clear();
         observableList = getTournoiList();
         tournoi.setItems(observableList);
    
    }
    }
   private void executeQuery(String query){
       Connection conn=getConnection();
       Statement st;
       try{
           st=conn.createStatement();
           st.executeUpdate(query);
       }catch(Exception ex){
                      ex.printStackTrace();

       }
   }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Tournoi tournois = tournoi.getSelectionModel().getSelectedItem();
        tfnomT.setText(tournois.getNom());
        tfdescription.setText(tournois.getDescription());
        tfadresse.setText(tournois.getAdresse());
        tfnbmax.setText(""+tournois.getNb_max());
        
    }

    @FXML
    private void GoToAfficheTr(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/afficheT.fxml"));
        
        Parent root = loader.load();
        Scene scene = new Scene(root); 
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        
        AfficheTController atc = loader.getController();
        Tournoi T = tournoi.getSelectionModel().getSelectedItem();
        atc.init(T);   

    }
    
    
   
   
}
