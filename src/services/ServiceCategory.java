/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import util.Connector;


/**
 *
 * @author Fourat
 */
public class ServiceCategory implements Iservice<Category> {

        Connection cnx = Connector.getInstance().getCnx();

    @Override
    public void ajouter(Category t) {
        try {
            String requete = "INSERT INTO category (type) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getType());
            pst.executeUpdate();
            System.out.println("Category ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Category t) {
           try {
            String requete = "DELETE FROM category WHERE type=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getType());
            pst.executeUpdate();
          

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Category t) {
        try {
            String requete = "UPDATE category SET nom=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, t.getId());
            pst.setString(1, t.getType());
            pst.executeUpdate();
            System.out.println("Category modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public  List<Category> afficher() {
     List<Category> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM category";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}
    

