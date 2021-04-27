/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Event;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aissa
 */

public class ServiceEvent implements IService<Event> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Event t) {
        try {
            String requete = "INSERT INTO event (name,lieu,description,prix,img,nb_participants,"
                    + "date_pub,date_debut,date_fin,etat,user_id,category_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getLieu());
            pst.setString(3, t.getDescription());
            pst.setFloat(4, t.getPrix());
            pst.setString(5, t.getImg());
            pst.setInt(6, t.getNbParticipants());
            pst.setDate(7, new java.sql.Date(t.getDatePub().getTime()));
            pst.setDate(8, new java.sql.Date(t.getDateDebut().getTime()));
            pst.setDate(9, new java.sql.Date(t.getDateFin().getTime()));
            pst.setString(10, t.getEtat());
            pst.setInt(11, t.getUserId());
            pst.setInt(12, t.getCategoryId());

            pst.executeUpdate();
            System.out.println("Event ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Event t) {
        try {
            
            String requete = "UPDATE event SET etat='disabled' WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Event supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void enable(Event t) {
        try {
            
            String requete = "UPDATE event SET etat='enabled' WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Event activé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Event t) {
        try {
            String requete = "UPDATE event SET name=?,lieu=?,description=?,prix=?,img=?,nb_participants=?,"
                    + "date_pub=?,date_debut=?,date_fin=?,etat=?,user_id=?,category_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(13, t.getId());
            pst.setString(1, t.getNom());
            pst.setString(2, t.getLieu());
            pst.setString(3, t.getDescription());
            pst.setFloat(4, t.getPrix());
            pst.setString(5, t.getImg());
            pst.setInt(6, t.getNbParticipants());
            pst.setDate(7, new java.sql.Date(t.getDatePub().getTime()));
            pst.setDate(8, new java.sql.Date(t.getDateDebut().getTime()));
            pst.setDate(9, new java.sql.Date(t.getDateFin().getTime()));
            pst.setString(10, t.getEtat());
            pst.setInt(11, t.getUserId());
            pst.setInt(12, t.getCategoryId());
            
            pst.executeUpdate();
            System.out.println("Event modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Event> afficher() {
        List<Event> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM event WHERE etat='enabled'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Event(rs.getInt(1), rs.getString("name"), 
                        rs.getString("lieu"), rs.getString("description"), 
                        rs.getFloat("prix"), rs.getString("img"), rs.getInt("nb_participants"), 
                        new java.util.Date(rs.getTimestamp("date_pub").getTime()), 
                        new java.util.Date(rs.getTimestamp("date_debut").getTime()), 
                        new java.util.Date(rs.getTimestamp("date_fin").getTime()), rs.getString("etat"), 
                        rs.getInt("user_id"), rs.getInt("category_id")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

   

}

