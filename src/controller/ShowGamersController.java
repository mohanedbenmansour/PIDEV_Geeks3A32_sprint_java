/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import models.Gamers;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ShowGamersController implements Initializable {

    @FXML
    private TableView<Gamers> tvGamers;
    @FXML
    private Label gamerShowName;
    @FXML
    private ImageView gamerShowImg;
    @FXML
    private Label gamerShowDesc;
    @FXML
    private Button btnGamerAdd;
    @FXML
    private Label gamerShowTitle;
    @FXML
    private Button btnGamerDelete;
    @FXML
    private Button btnGamerEdit;
    @FXML
    private TextField gamerSearch;
    
    private final ObservableList<Gamers> gamersList = FXCollections.observableArrayList();
//    @FXML
//    private PieChart GamerPieChart;
//    @FXML
//    private Label eventShowCategory;
    @FXML
    private TableColumn<?, ?> gamerColName;
    @FXML
    private TableColumn<?, ?> gamerColAge;
    @FXML
    private TableColumn<?, ?> gamerColTwitch;
    @FXML
    private TableColumn<?, ?> gamerColFb;
    @FXML
    private TableColumn<?, ?> gamerColTeam;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showGamers();
//        loadData();
        gamerShowName.setText("");
        gamerShowDesc.setText("");
        
//        eventShowCategory.setText("");
//        eventShowCategory.setVisible(false);
        gamerShowImg.setImage(null);
        btnGamerDelete.setVisible(false);
        btnGamerDelete.setDisable(true);
        btnGamerEdit.setVisible(false);
        btnGamerEdit.setDisable(true);
//        GamerPieChart.setVisible(false);


        //search//
        FilteredList<Gamers> filter = new FilteredList<>(gamersList, b->true);
        gamerSearch.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(gamer -> {
            if(newValue.isEmpty() || newValue==null) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(gamer.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else if (gamer.getAge().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }else 
            return false;
        });
        });
        SortedList<Gamers> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tvGamers.comparatorProperty());
        
        tvGamers.setItems(sort);

    }    

    public ObservableList<Gamers> getGamersList(){
    Connection cnx = DataSource.getInstance().getCnx();
    String Query = "SELECT * FROM gamers";
    Statement st;
    ResultSet rs;
    
    try{    
        st= cnx.createStatement();
        rs = st.executeQuery(Query);
        Gamers gamers;
        while(rs.next())
        {
        gamers = new Gamers(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("age"),
                rs.getString("description"),
                rs.getString("player_photo"),
                rs.getString("gamer_twitch"),
                rs.getString("gamer_facebook"),
                rs.getString("gamer_team"));
       gamersList.add(gamers);
        }
    } catch (Exception ex) {
    ex.printStackTrace();
    }
    return gamersList;
    }
    
    ObservableList<Gamers> list ;

    public void showGamers(){
       list = getGamersList();
       gamerColName.setCellValueFactory((new PropertyValueFactory <>("name") ));
       gamerColAge.setCellValueFactory((new PropertyValueFactory <>("age") ));
       gamerColFb.setCellValueFactory((new PropertyValueFactory <>("gamer_facebook") ));
       gamerColTwitch.setCellValueFactory((new PropertyValueFactory <>("gamer_twitch") ));
       gamerColTeam.setCellValueFactory((new PropertyValueFactory <>("gamer_team") ));
       
       tvGamers.setItems(list);
   } 
    
    @FXML
    void showSelectedGamer(MouseEvent gamer) throws SQLException {
        Gamers e = tvGamers.getSelectionModel().getSelectedItem();
        if (e != null) {
            gamerShowName.setText(String.valueOf(e.getName()));
            gamerShowDesc.setText(String.valueOf(e.getDescription()));
            
            Connection cnx = DataSource.getInstance().getCnx();
            //PreparedStatement ps;
            //ps = cnx.prepareStatement("SELECT name FROM categorie_event WHERE id = ?");
//            ps.setString(1, String.valueOf(e.getCategoryId()));
            //ResultSet rs = ps.executeQuery();
            //rs.next();
//            eventShowCategory.setText(rs.getString(1));
//            eventShowCategory.setVisible(true);

            gamerShowImg.setImage(new Image("file:///"+String.valueOf(e.getPlayer_photo())));
            btnGamerDelete.setVisible(true);
            btnGamerDelete.setDisable(false);
            btnGamerEdit.setVisible(true);
            btnGamerEdit.setDisable(false);
//            GamerPieChart.setVisible(false);

//            gamerShowImg.setImage(new Image("file:///D:\\wamp64\\www\\dsimage\\uploads\\"+String.valueOf(e.getPlayer_photo())));
        }
    }

     @FXML
  private void deleteGamer() throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();
        Gamers e = tvGamers.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM gamers WHERE id = ?" ;
             try {
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(e.getId()));
            preparedStatement.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Gamer supprimé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        showGamers();
//        loadData();
        gamerShowName.setText("");
        gamerShowDesc.setText("");
//        eventShowCategory.setText("");
//        eventShowCategory.setVisible(false);
        gamerShowImg.setImage(null);
        btnGamerDelete.setVisible(false);
        btnGamerDelete.setDisable(true);
        btnGamerEdit.setVisible(false);
        btnGamerEdit.setDisable(true);

    }

    @FXML
    private void GoToAddGamers(ActionEvent gamer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Add Gamers.fxml"));
        
        Parent root = loader.load();
        gamerShowTitle.getScene().setRoot(root);
        
        //AddGamersController aec = loader.getController();
    }

    @FXML
    private void GoToEditGamers(ActionEvent gamer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Edit Gamers.fxml"));
        
        Parent root = loader.load();
        gamerShowTitle.getScene().setRoot(root);
        
        //EditGamersController eec = loader.getController();
        Gamers e = tvGamers.getSelectionModel().getSelectedItem();
        //eec.init(e);   
    }

//    private void loadData(){
//        ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
////        String query  = " SELECT c.name,COUNT(e.id) Nbr FROM categorie_event c, event e WHERE e.category_id=c.id GROUP BY c.name ";
//        Statement st;
//        ResultSet rs;
//            
//        try{
//            st = DataSource.getInstance().getCnx().createStatement();
////            rs=st.executeQuery(query);
////            while(rs.next()){
////              pieChartData.add(new PieChart.Data(rs.getString(1),rs.getInt(2)));
////            }
//        }catch(Exception ex){
//        ex.printStackTrace();
//        }
//        GamerPieChart.setData(pieChartData);
//        GamerPieChart.setStartAngle(90);
//        GamerPieChart.getData().stream().forEach((data) -> {
//            data.nameProperty().set(data.getName()+" : "+(int)data.getPieValue()+ "  gamers ");
//        });
//    }

//    @FXML
//    private void ShowHideStat(ActionEvent gamer) {
//        if(GamerPieChart.isVisible())
//            GamerPieChart.setVisible(false);
//        else
//        {
//            GamerPieChart.setVisible(true);
//            gamerShowName.setText(" ");
//            gamerShowAge.setText("");
//            gamerShowTwitch.setText("");
//            gamerShowFb.setText("");
//            gamerShowDesc.setText("");
////            eventShowCategory.setText("");
////            eventShowCategory.setVisible(false);
//            gamerShowImg.setImage(null);
//            btnGamerDelete.setVisible(false);
//            btnGamerDelete.setDisable(true);
//            btnGamerEdit.setVisible(false);
//            btnGamerEdit.setDisable(true);
//        }
//    }
}