
package views;
import javafx.collections.transformation.SortedList;
import models.User;
import utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class RechercheAvanceeController implements Initializable {

    @FXML
    private AnchorPane RechInterface;
    @FXML
    private TextField recherche;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> pseudo;
//    @FXML
//    private TableColumn<User, String> date;
    @FXML
    private TableColumn<User, String> email;
//    @FXML
//    private TableColumn<User, String> type;
    @FXML
    private final ObservableList<User> list = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));       
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));        
        pseudo.setCellValueFactory(new PropertyValueFactory<>("username"));        
//        date.setCellValueFactory(new PropertyValueFactory<>("Date de naissance"));        
        email.setCellValueFactory(new PropertyValueFactory<>("Email")); 
//        type.setCellValueFactory(new PropertyValueFactory<>("Type du compte"));
        Connection con=DataSource.getInstance().getCnx();
        String requete="select * from utilisateur";
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
          User U= new User();
          U.setId(rs.getInt("id"));
          U.setNom(rs.getString("nom"));
          U.setPrenom(rs.getString("prenom"));
          U.setUsername(rs.getString("username"));
//          U.setDate_naiss(rs.getDate("date_naiss"));
//          U.setType_compte(rs.getString("type_compte"));
          U.setEmail(rs.getString("email"));
          U.setPassword(rs.getString("password"));
//          U.setNb_abonnes(rs.getInt("nb_abonnes"));
//          U.setNb_abonnements(rs.getInt("nb_abonnements"));
//          U.setNb_pub(rs.getInt("Nb_pub"));
           list.add(U);
    }    
     // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Utilisateur -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Utilisateur.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Utilisateur.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
//				else if (String.valueOf(Utilisateur.getDate_naiss()).indexOf(lowerCaseFilter)!=-1)
//				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<User> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
               
}       catch (SQLException ex) {
            Logger.getLogger(RechercheAvanceeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}