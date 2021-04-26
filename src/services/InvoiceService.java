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
import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Invoice;
import models.Ordre;

import utils.DataSource;

/**
 *
 * @author mohan
 */
public class InvoiceService  implements IServiceInvoice<Invoice>{
Connection cnx=DataSource.getInstance().getConnection();
private Statement ste;
    @Override
    public void ajouter(Invoice o) {

 int id=0;
        try {
    String request = "INSERT INTO `invoice` (`id`, `name`, `email`, `phone`, `subtotal`, `tax`, `total_cost`, `creation_date`) VALUES (NULL,?,?,?,?,?,?,?) ";
//"INSERT INTO order (id,user_id,user_adress,checkout_date,user_phone,status,city,state,zipcode,total_price ) VALUES (NULL,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(request,Statement.RETURN_GENERATED_KEYS);
        
        
            pst.setString(1, o.getName());
            pst.setString(2, o.getEmail());
            pst.setInt(3, o.getPhone());
            pst.setInt(4, o.getSubtotal());
            pst.setInt(5, o.getTax());
            pst.setInt(6, o.getSubtotal());
            pst.setString(7, o.getCreationDate());
          
           

            pst.executeUpdate();
              
            System.out.println("facture ajoutée !");
            
       

        }
        catch(SQLException ex){
              System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Invoice o) {
try{
        String request="DELETE FROM `invoice` WHERE `invoice`.`id` = ?";
   PreparedStatement pst = cnx.prepareStatement(request);
   pst.setInt(1, o.getId());
   pst.executeUpdate();
      System.out.println("invoice supprimé !");

}
catch(SQLException ex){
              System.err.println(ex.getMessage());

}

    }

    @Override
    public void modifier(Invoice o) {

 try{
        String request = "UPDATE invoice set name=?,email=?,phone=?,subtotal=?,tax=?,total_cost=?,creation_date=? where id=?";
 PreparedStatement pst = cnx.prepareStatement(request);
      
          
            
            pst.setString(1, o.getName());
            pst.setString(2, o.getEmail());
            pst.setInt(3, o.getPhone());
            pst.setInt(4, o.getSubtotal());
            pst.setInt(5, o.getTax());
            pst.setInt(6, o.getSubtotal());
            pst.setString(7, o.getCreationDate());
            pst.setInt(8, o.getId());
            pst.executeUpdate();
            System.out.println("invoice updated !");
    
    }
    catch(SQLException ex){
              System.err.println(ex.getMessage());

}    }

    @Override
    public ObservableList<Invoice> afficher() {
    
     ObservableList<Invoice> list=FXCollections.observableArrayList();
try {
            String request = "SELECT * FROM `invoice";
            PreparedStatement pst = cnx.prepareStatement(request);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;    }
    
    
    @Override
    public ObservableList<Invoice> afficherParOrdre() {
    
     ObservableList<Invoice> list=FXCollections.observableArrayList();
          ObservableList<Invoice> sortedlist=FXCollections.observableArrayList();

try {
            String request = "SELECT * FROM `invoice";
            PreparedStatement pst = cnx.prepareStatement(request);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8)));
            }
     sortedlist = list.stream()
  .sorted(Comparator.comparing(Invoice::getTotalCost))
  .collect(Collectors.toCollection(FXCollections::observableArrayList));
             return sortedlist;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;    }
    
    @Override
    public ObservableList<Invoice> rechercher(String name ) {
    
     ObservableList<Invoice> list=FXCollections.observableArrayList();
          ObservableList<Invoice> searchResult=FXCollections.observableArrayList();

try {
            String request = "SELECT * FROM `invoice";
            PreparedStatement pst = cnx.prepareStatement(request);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8)));
            }
            
           
   searchResult = list.stream()
  .filter(a -> a.getName().equals( name)) 
  .collect(Collectors.toCollection(FXCollections::observableArrayList));
             return searchResult;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;    }
    
}
