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
import java.util.ArrayList;
import java.util.List;
import models.Gamers;
import utils.DataSource;

/**
 *
 * @author Lenovo
 */
public class serviceGamer implements IService<Gamers>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Gamers t) {
        try {
            String requete = "INSERT INTO gamers (name,age,description,player_photo,gamer_twitch,gamer_facebook,gamer_team) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setInt(0, t.getId());
            pst.setString(1, t.getName());
            pst.setString(2, t.getAge());
            pst.setString(3, t.getDescription());
            pst.setString(4, t.getPlayer_photo());
            pst.setString(5, t.getGamer_twitch());
            pst.setString(6, t.getGamer_facebook());
            pst.setString(7, t.getGamer_team());

            pst.executeUpdate();
            System.out.println("Gamer ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Gamers t) {
        try {
            
            String requete = "DELETE FROM gamers WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("gamer supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Gamers t) {
        try {
            String requete = "UPDATE gamers SET name=?,age=?,description=?,player_photo=?,gamer_twitch=?,gamer_facebook=?,"
                    + "gamer_team=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(13, t.getId());
            pst.setString(1, t.getName());
            pst.setString(2, t.getAge());
            pst.setString(3, t.getDescription());
            pst.setString(4, t.getPlayer_photo());
            pst.setString(5, t.getGamer_twitch());
            pst.setString(6, t.getGamer_facebook());
            pst.setString(7, t.getGamer_team());
            
            pst.executeUpdate();
            System.out.println("gamer modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Gamers> afficher() {
        List<Gamers> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM gamers";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Gamers(rs.getInt(1), rs.getString("name"),
                        rs.getString("age"), rs.getString("description"), 
                        rs.getString("player_photo"), rs.getString("gamer_twitch"), rs.getString("gamer_facebook"),
                        rs.getString("gamer_team")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}
