/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.event;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.chart.PieChart;
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
import models.Event;
import services.ServiceEvent;
import tests.MainGUI;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ShowEventsController implements Initializable {

    @FXML
    private TableView<Event> tvEvents;
    @FXML
    private TableColumn<?, ?> eventsColNom;
    @FXML
    private TableColumn<?, ?> eventsColLieu;
    @FXML
    private TableColumn<?, ?> eventsColDateDebut;
    @FXML
    private TableColumn<?, ?> eventsColDateFin;
    @FXML
    private TableColumn<?, ?> eventsColPrix;
    @FXML
    private Label eventShowNom;
    @FXML
    private Label eventShowDatePub;
    @FXML
    private Label eventShowDateDeb;
    @FXML
    private Label eventShowDateFin;
    @FXML
    private ImageView eventShowImg;
    @FXML
    private Label eventShowDesc;
    @FXML
    private Button btnEventAdd;
    @FXML
    private Label eventShowTitle;
    @FXML
    private Button btnEventDelete;
    @FXML
    private Button btnEventEdit;
    @FXML
    private TextField eventSearch;
    
    private final ObservableList<Event> eventList = FXCollections.observableArrayList();
    //private PieChart EventPieChart;
    @FXML
    private Label eventShowCategory;

	
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEvents();
        //loadData();
        eventShowNom.setText(" ");
        eventShowDatePub.setText("");
        eventShowDateDeb.setText("");
        eventShowDateFin.setText("");
        eventShowDesc.setText("");
        eventShowCategory.setText("");
        eventShowCategory.setVisible(false);
        eventShowImg.setImage(null);
        btnEventDelete.setVisible(false);
        btnEventDelete.setDisable(true);
        btnEventEdit.setVisible(false);
        btnEventEdit.setDisable(true);
        //EventPieChart.setVisible(false);


        //search//
        FilteredList<Event> filter = new FilteredList<>(eventList, b->true);
        eventSearch.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(event -> {
            if(newValue.isEmpty() || newValue==null) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else if (event.getLieu().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }else 
            return false;
        });
        });
        SortedList<Event> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tvEvents.comparatorProperty());
        
        tvEvents.setItems(sort);

    }    
 
    public ObservableList<Event> getEventsList(){
        /*Event e = tvEvents.getSelectionModel().getSelectedItem();
        ServiceEvent se = new ServiceEvent();
        se.afficher();*/
        Connection cnx = DataSource.getInstance().getCnx();
        String Query = "SELECT * FROM event WHERE etat='enabled'";
        Statement st;
        ResultSet rs;

        try{    
            st= cnx.createStatement();
            rs = st.executeQuery(Query);
            Event events;
            while(rs.next())
            {
            events = new Event(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("lieu"),
                    rs.getString("description"),
                    rs.getFloat("prix"),
                    rs.getString("img"),
                    rs.getInt("nb_participants"),
                    rs.getDate("date_pub"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin"),
                    rs.getString("etat"),
                    rs.getInt("user_id"),
                    rs.getInt("category_id"));
           eventList.add(events);
            }
        } catch (Exception ex) {
        ex.printStackTrace();
        }
        return eventList;
    }
    
    ObservableList<Event> list ;

    public void showEvents(){
       
       list = getEventsList();
       eventsColNom.setCellValueFactory((new PropertyValueFactory <>("nom") ));
       eventsColLieu.setCellValueFactory((new PropertyValueFactory <>("lieu") ));
       eventsColDateDebut.setCellValueFactory((new PropertyValueFactory <>("dateDebut") ));
       eventsColDateFin.setCellValueFactory((new PropertyValueFactory <>("dateFin") ));
       eventsColPrix.setCellValueFactory((new PropertyValueFactory <>("prix") ));
       
       tvEvents.setItems(list);
   } 
    
    @FXML
    void showSelectedEvent(MouseEvent event) throws SQLException {
        Event e = tvEvents.getSelectionModel().getSelectedItem();
        if (e != null) {
            eventShowNom.setText(String.valueOf(e.getNom()));
            eventShowDatePub.setText("Publié par : "+ String.valueOf(e.getUserId())+ " le " + String.valueOf(e.getDatePub()));
            eventShowDateDeb.setText(String.valueOf(e.getDateDebut()));
            eventShowDateFin.setText(String.valueOf(e.getDateFin()));
            eventShowDesc.setText(String.valueOf(e.getDescription()));
            
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement ps;
            ps = cnx.prepareStatement("SELECT name FROM categorie_event WHERE id = ?");
            ps.setString(1, String.valueOf(e.getCategoryId()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            eventShowCategory.setText(rs.getString(1));
            eventShowCategory.setVisible(true);

            eventShowImg.setImage(new Image("file:src/uploads/"+String.valueOf(e.getImg())));
            btnEventDelete.setVisible(true);
            btnEventDelete.setDisable(false);
            btnEventEdit.setVisible(true);
            btnEventEdit.setDisable(false);
            //EventPieChart.setVisible(false);

        }
    }

     @FXML
  private void deleteEvent() throws SQLException {
        Event e = tvEvents.getSelectionModel().getSelectedItem();
        ServiceEvent se = new ServiceEvent();
        se.supprimer(e);
        JOptionPane.showMessageDialog(null, "Event supprimé");
        
        list.clear();
        showEvents();
        //loadData();
        eventShowNom.setText(" ");
        eventShowDatePub.setText("");
        eventShowDateDeb.setText("");
        eventShowDateFin.setText("");
        eventShowDesc.setText("");
        eventShowCategory.setText("");
        eventShowCategory.setVisible(false);
        eventShowImg.setImage(null);
        btnEventDelete.setVisible(false);
        btnEventDelete.setDisable(true);
        btnEventEdit.setVisible(false);
        btnEventEdit.setDisable(true);
        

    }

    @FXML
    private void GoToAddEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/event/Add Event.fxml"));
        
        Parent root = loader.load();
        eventShowTitle.getScene().setRoot(root);
        
        AddEventController aec = loader.getController();
    }

    @FXML
    private void GoToEditEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../views/event/Edit Event.fxml"));
        
        Parent root = loader.load();
        eventShowTitle.getScene().setRoot(root);
        
        EditEventController eec = loader.getController();
        Event e = tvEvents.getSelectionModel().getSelectedItem();
        eec.init(e);   
    }
/*
    private void loadData(){
        ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
        String query  = " SELECT c.name,COUNT(e.id) Nbr FROM categorie_event c, event e WHERE e.category_id=c.id GROUP BY c.name ";
        Statement st;
        ResultSet rs;
            
        try{
            st = DataSource.getInstance().getCnx().createStatement();
            rs=st.executeQuery(query);
            while(rs.next()){
              pieChartData.add(new PieChart.Data(rs.getString(1),rs.getInt(2)));
            }
        }catch(Exception ex){
        ex.printStackTrace();
        }
        EventPieChart.setData(pieChartData);
        EventPieChart.setStartAngle(90);
        EventPieChart.getData().stream().forEach((data) -> {
            data.nameProperty().set(data.getName()+" : "+(int)data.getPieValue()+ "  Events ");
        });
    }

    private void ShowHideStat(ActionEvent event) {
        if(EventPieChart.isVisible())
            EventPieChart.setVisible(false);
        else
        {
            EventPieChart.setVisible(true);
            eventShowNom.setText(" ");
            eventShowDatePub.setText("");
            eventShowDateDeb.setText("");
            eventShowDateFin.setText("");
            eventShowDesc.setText("");
            eventShowCategory.setText("");
            eventShowCategory.setVisible(false);
            eventShowImg.setImage(null);
            btnEventDelete.setVisible(false);
            btnEventDelete.setDisable(true);
            btnEventEdit.setVisible(false);
            btnEventEdit.setDisable(true);
        }
    }
*/
    @FXML
    private void ShowEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/event/Show Events.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
    }

    @FXML
    private void ShowShop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/product/market.fxml"));
        Scene scene = new Scene(root);
        MainGUI.pStage.setScene(scene);
        MainGUI.pStage.show();
    }
}