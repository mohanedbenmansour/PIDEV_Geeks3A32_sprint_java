/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;
import utils.DataSource;

/**
 *
 * @author Lenovo
 */
public class serviceUser implements IServiceUser{
    
    private Connection con;
    private Statement ste;

    public serviceUser() {
        con = DataSource.getInstance().getCnx();
    }
    
    public List<User> getListUtilisateur() {
    List<User> list = new ArrayList<>();
    String requete="select * from utilisateur";
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
          User U= new User();
          U.setId(rs.getInt("id_utilisateur"));
          U.setNom(rs.getString("nom"));
          U.setPrenom(rs.getString("prenom"));
          U.setUsername(rs.getString("username"));
//          U.setDate_naiss(rs.getDate("date_naiss"));
//          U.setType_compte(rs.getString("type_compte"));
          U.setEmail(rs.getString("email"));
          U.setPassword(rs.getString("password"));
//          U.set(rs.getInt("nb_abonnes"));
//          U.setNb_abonnements(rs.getInt("nb_abonnements"));
//          U.setNb_pub(rs.getInt("Nb_pub"));
          
          list.add(U);
        }
    }
    catch (SQLException ex) {
            Logger.getLogger(serviceUser.class.getName()).log(Level.SEVERE,null, ex);
    }
    return list;
}

    @Override
    public void register(User t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `geeek`.`utilisateur` (`username`, `password` , `roles`,`email` , `phone_number` , `nom`,`prenom`) VALUES ('" + t.getUsername()+ "' , '" + t.getPassword()+ "','[]' , '" + t.getEmail() + "', '" + t.getPhone_number()+ "', '" + t.getNom()+ "', '" + t.getPrenom()+ "');";
        ste.executeUpdate(requeteInsert); //To change body of generated methods, choose Tools | Templates.
        System.out.println(requeteInsert);
        
    }

    @Override
    public boolean update(User t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ResetPassword(String pass, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User login(String username, String password) throws SQLException {
        User u = new User();
        try {
            String sql = "SELECT * from utilisateur WHERE username ='" + username + "' AND password='" + password + "'";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next() == true) {
                int id = rs.getInt(1);
//                String username = rs.getString(2);
                String roles = rs.getString(3);
                String Password = rs.getString(4);
                String Email = rs.getString(5);
                String phone_number = rs.getString(6);
                String image= rs.getString(7);
                String photocover= rs.getString(8);
                String isverified= rs.getString(9);
                String nom= rs.getString(10);
                String prenom= rs.getString(11);
                String facebookProfil= rs.getString(12);
                String twitch_profil= rs.getString(13);
                String youtube_channel= rs.getString(14);
                u = new User(id, username, roles, Password, Email, phone_number, image, photocover, isverified, nom, prenom, facebookProfil, twitch_profil, youtube_channel);
                System.out.println(" |||  user  authentifié  |||");
                System.out.println(u);
              
            } else {
                System.out.println("non trouvé");
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(IServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(User t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `pidev3a`.`user` where id =? AND nom =?");
        pre.setInt(1, t.getId());
        pre.setString(2, t.getNom());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A User was deleted successfully!");
        }
        return true; //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public User FindById(int id) throws SQLException {
//        String req = "select * from utilisateur where id = ?";
//            User u = null;
//            try {
//                PreparedStatement ps = con.prepareStatement(req);
//                 ps.setInt(1, id);
//                ResultSet resultSet = ps.executeQuery();
//                if (resultSet.next()) {
//               
//                
//                    u = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13), resultSet.getString(14));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return u; //To change body of generated methods, choose Tools | Templates.
//    }
    
    public User ChercherUtilisateur(String pseudo) {
    String requete1 ="select * FROM utilisateur where pseudo='"+pseudo+"'";
    User U= new User();
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(requete1);
        while (rs.next()) {
         
          U.setId(rs.getInt("id_utilisateur"));
          U.setNom(rs.getString("nom"));
          U.setPrenom(rs.getString("prenom"));
          U.setUsername(rs.getString("pseudo"));
//          U.setDate_naiss(rs.getDate("date_naiss"));
//          U.setType_compte(rs.getString("type_compte"));
          U.setEmail(rs.getString("mail"));
          U.setPassword(rs.getString("mdp"));
//          U.setNb_abonnes(rs.getInt("nb_abonnes"));
//          U.setNb_abonnements(rs.getInt("nb_abonnements"));
//          U.setNb_pub(rs.getInt("Nb_pub"));
    
    }
    }   catch (SQLException ex) {
            Logger.getLogger(serviceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return U;
  }
  
  
  
    @Override
   public User FindById(int id) {
     User U= new User();
    String requete1 ="select * FROM utilisateur where id='"+id+"'";
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(requete1);
        while (rs.next()) {
         
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
          
         
    
    }
    }   catch (SQLException ex) {
            Logger.getLogger(serviceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return U;
  }

    @Override
    public User FindByEmail(String email) throws SQLException {
        User U= new User();
    String requete1 ="select * FROM utilisateur where email='"+email+"'";
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(requete1);
        while (rs.next()) {
         
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
          
         
    
    }
    }   catch (SQLException ex) {
            Logger.getLogger(serviceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return U; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User FindByPohne(String phone) throws SQLException {
        User U= new User();
    String requete1 ="select * FROM utilisateur where phone_number='"+phone+"'";
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(requete1);
        while (rs.next()) {
         
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
          
         
    
    }
    }   catch (SQLException ex) {
            Logger.getLogger(serviceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return U; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ResetPassword(User t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "UPDATE `utilisateur` SET `password` ='" + t.getPassword() + "' WHERE `utilisateur`.`email` = '" + t.getEmail() + "';";
        ste.executeUpdate(requeteInsert); //To change body of generated methods, choose Tools | Templates.
        System.out.println(requeteInsert); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String sendSms(){
    try {
			// Construct data
			String apiKey = "apikey=" + "YTY5NjU3MmU3MDE3YzMzZWZjNmQzNzU3YTQzNGUzZTE=";
			String message = "&message=" + "your order has been shipped";
			String sender = "&sender=" + "mohaned benmansour";
			String numbers = "&numbers=" + "+21627789426";

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
    
    }

    
    
    
}
