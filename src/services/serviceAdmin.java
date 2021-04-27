/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import models.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Lenovo
 */
public class serviceAdmin implements IServiceAdmin{
    
    private Connection con;
    private Statement ste;

    public serviceAdmin() {
        con = DataSource.getInstance().getCnx();
    }
    
    
    @Override
    public void register(Admin t) throws SQLException {
         ste = con.createStatement();
        String requeteInsert = "INSERT INTO `geeks`.`utilisateur` (`username`, `roles`, `password` , `email` , `phone_number` , `image`,`phoyocover`,`is_verified`,`nom`,`prenom`,`facebookprofil`,`twitch_profil`,`youtube_channel`) VALUES (NULL, '" + t.getNom() + "' , '" + t.getPrenom() + "' , '" + t.getEmail() + "', '" + t.getPassword() + "', '" + t.getRoles()+ "', '" + t.getImage() + "');";
        ste.executeUpdate(requeteInsert); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Admin t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ResetPassword(String pass, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Admin login(String username, String password) throws SQLException {
           Admin u = new Admin();
        try {
            String sql = "SELECT * from utilisateur WHERE username ='" + username + "' AND password='" + password + "'" + " AND roles like '%ROLE_ADMIN%'";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next() == true) {
                int id = rs.getInt(1);
//                String username = rs.getString(2);
//                String roles = rs.getString(3);
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
                u = new Admin(id, username, Password, Email, phone_number, image, photocover, isverified, nom, prenom, facebookProfil, twitch_profil, youtube_channel);
                System.out.println(" |||  Admin  authentifié  |||");
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
    public boolean delete(Admin t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
       
    
    
}
