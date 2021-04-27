/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.chart.PieChart;
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
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EventsBackController implements Initializable {

    @FXML
    private Button EventsBack;
    @FXML
    private Button CategoryEventsBack;
    @FXML
    private TableView<Event> tvEventsBack;
    @FXML
    private Label eventBackNom;
    @FXML
    private Label eventBackDatePub;
    @FXML
    private Label eventBackDateDeb;
    @FXML
    private Label eventBackDateFin;
    @FXML
    private ImageView eventBackImg;
    @FXML
    private Label eventBackDesc;
    @FXML
    private Button btnEventBackDelete;
    @FXML
    private Label eventShowTitle;
    @FXML
    private TextField eventBackSearch;
    @FXML
    private Button EventStatButton;
    @FXML
    private PieChart EventPieChart;
    @FXML
    private Label eventBackCategory;
    
    private final ObservableList<Event> eventList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> eventsBackColNom;
    @FXML
    private TableColumn<?, ?> eventsBackColLieu;
    @FXML
    private TableColumn<?, ?> eventsBackColDateDebut;
    @FXML
    private TableColumn<?, ?> eventsBackColDateFin;
    @FXML
    private TableColumn<?, ?> eventsBackColPrix;
    @FXML
    private TableColumn<?, ?> eventsBackColEtat;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEvents();
        loadData();
        eventBackNom.setText(" ");
        eventBackDatePub.setText("");
        eventBackDateDeb.setText("");
        eventBackDateFin.setText("");
        eventBackDesc.setText("");
        eventBackCategory.setText("");
        eventBackCategory.setVisible(false);
        eventBackImg.setImage(null);
        btnEventBackDelete.setVisible(false);
        btnEventBackDelete.setDisable(true);
        EventPieChart.setVisible(false);


        //search//
        FilteredList<Event> filter = new FilteredList<>(eventList, b->true);
        eventBackSearch.textProperty().addListener((observable, oldValue, newValue )-> {

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
        sort.comparatorProperty().bind(tvEventsBack.comparatorProperty());
        
        tvEventsBack.setItems(sort);

    }    

    public ObservableList<Event> getEventsList(){
        Connection cnx = DataSource.getInstance().getCnx();
        String Query = "SELECT * FROM event";
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
       eventsBackColNom.setCellValueFactory((new PropertyValueFactory <>("nom") ));
       eventsBackColLieu.setCellValueFactory((new PropertyValueFactory <>("lieu") ));
       eventsBackColDateDebut.setCellValueFactory((new PropertyValueFactory <>("dateDebut") ));
       eventsBackColDateFin.setCellValueFactory((new PropertyValueFactory <>("dateFin") ));
       eventsBackColPrix.setCellValueFactory((new PropertyValueFactory <>("prix") ));
       eventsBackColEtat.setCellValueFactory((new PropertyValueFactory <>("etat") ));

       tvEventsBack.setItems(list);
   }
    
    @FXML
    void showSelectedEvent(MouseEvent event) throws SQLException {
        Event e = tvEventsBack.getSelectionModel().getSelectedItem();
        if (e != null) {
            eventBackNom.setText(String.valueOf(e.getNom()));
            eventBackDatePub.setText("Publié par : "+ String.valueOf(e.getUserId())+ " le " + String.valueOf(e.getDatePub()));
            eventBackDateDeb.setText(String.valueOf(e.getDateDebut()));
            eventBackDateFin.setText(String.valueOf(e.getDateFin()));
            eventBackDesc.setText(String.valueOf(e.getDescription()));
            
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement ps;
            ps = cnx.prepareStatement("SELECT name FROM categorie_event WHERE id = ?");
            ps.setString(1, String.valueOf(e.getCategoryId()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            eventBackCategory.setText(rs.getString(1));
            eventBackCategory.setVisible(true);

            eventBackImg.setImage(new Image("file:///"+String.valueOf(e.getImg())));
            btnEventBackDelete.setVisible(true);
            btnEventBackDelete.setDisable(false);
            EventPieChart.setVisible(false);
            if(e.getEtat().equals("enabled"))
                btnEventBackDelete.setText("Disable");
            else
                btnEventBackDelete.setText("Enable");

            //eventShowImg.setImage(new Image("file:///C:\\wamp64\\www\\dsimage\\uploads\\"+String.valueOf(e.getImg())));
        }
    }

     @FXML
    private void deleteEvent() throws SQLException {
          Event e = tvEventsBack.getSelectionModel().getSelectedItem();
          if(e.getEtat().equals("enabled"))
          {
              ServiceEvent se = new ServiceEvent();
              se.supprimer(e);
              JOptionPane.showMessageDialog(null, "Event disabled");
              btnEventBackDelete.setText("Enable");

          }else{
              ServiceEvent se = new ServiceEvent();
              se.enable(e);
              JOptionPane.showMessageDialog(null, "Event enabled");
              btnEventBackDelete.setText("Disbale");

          }
         
          list.clear();
          showEvents();
          loadData();
          eventBackNom.setText(" ");
          eventBackDatePub.setText("");
          eventBackDateDeb.setText("");
          eventBackDateFin.setText("");
          eventBackDesc.setText("");
          eventBackCategory.setText("");
          eventBackCategory.setVisible(false);
          eventBackImg.setImage(null);
          btnEventBackDelete.setVisible(false);
          btnEventBackDelete.setDisable(true);
    }
  
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
    
    @FXML
    private void ShowHideStat(ActionEvent event) {
        if(EventPieChart.isVisible())
            EventPieChart.setVisible(false);
        else
        {
            EventPieChart.setVisible(true);
            eventBackNom.setText(" ");
            eventBackDatePub.setText("");
            eventBackDateDeb.setText("");
            eventBackDateFin.setText("");
            eventBackDesc.setText("");
            eventBackCategory.setText("");
            eventBackCategory.setVisible(false);
            eventBackImg.setImage(null);
            btnEventBackDelete.setVisible(false);
            btnEventBackDelete.setDisable(true);
        }
    }

    @FXML
    private void GoToCategoryEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Category Event Back.fxml"));
        
        Parent root = loader.load();
        eventShowTitle.getScene().setRoot(root);
        
        CategoryEventBackController cebc = loader.getController();
    }
    
}
