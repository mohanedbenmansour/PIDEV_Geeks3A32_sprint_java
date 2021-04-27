/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Tournoi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Connector;

/**
 *
 * @author Fourat
 */
public class ServiceTrounoi implements Iservice<Tournoi> {
            Connection cnx = Connector.getInstance().getCnx();

 
    @Override
    public void ajouter(Tournoi t) {
          try {
            String requete = "INSERT INTO Tournoi (nom,description,adresse,date_publication,date_tournoi,nb_max,category_id,image,lien_youtube,active,user_t_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getAdresse());
            pst.setDate(4, new java.sql.Date(t.getDate_publication().getTime()));
            pst.setDate(5, new java.sql.Date(t.getDate_tournoi().getTime()));
            pst.setInt(6, t.getNb_max());
            pst.setInt(7, t.getCategory_Id());
            pst.setString(8, t.getImage());
            pst.setString(9,t.getLien_youtube());
            pst.setInt(10, t.getActive());
            pst.setInt(11, t.getUser_t_id());

            
            pst.executeUpdate();
            System.out.println("Tournoi ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Tournoi t) {
            try {
            String requete = "DELETE FROM Tournoi WHERE nom=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.executeUpdate();
            System.out.println("Tournoi supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Tournoi t) {
  try {
            String requete = "UPDATE Tournoi SET nom=?,description=? ,adresse=?,date_tournoi=?,date_publication=?,nb_max=?,category_id=?,image=?,lien_youtube=?,active=?,user_t_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getDescription());
            pst.setString(4, t.getAdresse());
            pst.setDate(5, new java.sql.Date(t.getDate_publication().getTime()));
            pst.setDate(6, new java.sql.Date(t.getDate_tournoi().getTime()));
            pst.setInt(7, t.getNb_max());
            pst.setInt(8, t.getCategory_Id());
            pst.setString(9, t.getImage());
            pst.setString(10,t.getLien_youtube());
            pst.setInt(11, t.getActive());
            pst.setInt(12, t.getUser_t_id());

            pst.executeUpdate();
            System.out.println("Tournoi modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<Tournoi> afficher() {
 List<Tournoi> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Tournoi";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Tournoi(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;    }

}
