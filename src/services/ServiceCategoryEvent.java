/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.CategoryEvent;
import utils.DataSource;

/**
 *
 * @author asus
 */
public class ServiceCategoryEvent implements IService<CategoryEvent>{
    
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(CategoryEvent t) {
        try {
            String requete = "INSERT INTO categorie_event (name) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
           
            pst.executeUpdate();
            System.out.println("Categorie ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(CategoryEvent t) {
        try {
            String requete = "DELETE FROM categorie_event WHERE id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(CategoryEvent t) {
        try {
            String requete = "UPDATE categorie_event SET name=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());
            pst.setString(1, t.getNom());
            
            pst.executeUpdate();
            System.out.println("Categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<CategoryEvent> afficher() {
        List<CategoryEvent> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM categorie_event";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new CategoryEvent(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
