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
import models.Ordre;
import models.OrderDetail;
import utils.DataSource;
//sms

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
//pdf
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.Path;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Invoice;
/**
 *
 * @author mohan
 */
public class OrdreService implements IService<Ordre,OrderDetail> {
Connection cnx=DataSource.getInstance().getConnection();
private Statement ste;

    @Override
    public void ajouter(Ordre o,OrderDetail oD) {
        int id=0;
        try {
    String request = "INSERT INTO `order` (`id`, `user_id`, `user_adress`, `checkout_date`, `user_phone`, `status`, `city`, `state`, `zipcode`, `total_price`) VALUES (NULL,?,?,?,?,?,?,?,?,?) ";
//"INSERT INTO order (id,user_id,user_adress,checkout_date,user_phone,status,city,state,zipcode,total_price ) VALUES (NULL,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(request,Statement.RETURN_GENERATED_KEYS);
        
            pst.setInt(1, o.getUserId());
            pst.setString(2, o.getUserAdress());
            pst.setString(3, o.getCheckoutDate());
            pst.setInt(4, o.getUserPhone());
            pst.setBoolean(5, o.getStatus());
            pst.setString(6, o.getCity());
            pst.setString(7, o.getState());
            pst.setInt(8, o.getZipcode());
            pst.setInt(9, o.getTotalPrice());
           

            pst.executeUpdate();
               ResultSet rs = pst.getGeneratedKeys();
                     if (rs.next()) {
                id = rs.getInt(1);
                 
}
            System.out.println("ordre ajoutée !");
            
       request = "INSERT INTO order_detail (product_id,quantity,payment,orderr_id ) VALUES (?,?,?,?)";
        pst = cnx.prepareStatement(request);

       pst.setInt(1, oD.getProduct());
            pst.setString(2, oD.getQuantity());
            pst.setInt(3, oD.getPayment());
            pst.setInt(4, id);
     pst.executeUpdate();
                 System.out.println("ordreDetail ajoutée !");

        }
        catch(SQLException ex){
              System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Ordre o) {
try{
        String request="DELETE FROM `order` WHERE `order`.`id=?";
   PreparedStatement pst = cnx.prepareStatement(request);
   pst.setInt(1, o.getId());
   pst.executeUpdate();
      System.out.println("ordre supprimé !");

  request="DELETE FROM order_detail where orderr_id=?";
    pst = cnx.prepareStatement(request);
   pst.setInt(1, o.getId());
   pst.executeUpdate();
   System.out.println("ordreDetail supprimé !");
}
catch(SQLException ex){
              System.err.println(ex.getMessage());

}
    }

    @Override
    public void modifier(Ordre o) {
    try{
        String request = "UPDATE order set user_id=?,user_adress=?,checkout_date=?,user_phone=?,status=?,city=?,state=?,zipcode=?,orderdetail=?, total_price=? where id=?";
 PreparedStatement pst = cnx.prepareStatement(request);
      
        
            pst.setString(1, o.getUserAdress());
            pst.setString(2, o.getCheckoutDate());
            pst.setInt(3, o.getUserPhone());
            pst.setBoolean(4, o.getStatus());
            pst.setString(5, o.getCity());
            pst.setString(6, o.getState());
            pst.setInt(7, o.getZipcode());
            pst.setInt(8, o.getTotalPrice());
   pst.setInt(9, o.getUserId());
            pst.executeUpdate();
            System.out.println("ordre updated !");
    
    }
    catch(SQLException ex){
              System.err.println(ex.getMessage());

}

    }

    @Override
    public   ObservableList<Ordre> afficher() {
           ObservableList<Ordre> list=FXCollections.observableArrayList();
try {
            String request = "SELECT * FROM `order`";
            PreparedStatement pst = cnx.prepareStatement(request);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Ordre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
            }

        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public ObservableList<Ordre> afficherParOrdre() {
    
       ObservableList<Ordre> list = FXCollections.observableArrayList();
         ObservableList<Ordre> sortedlist = FXCollections.observableArrayList();
try {
            String request = "SELECT * FROM `order";
            PreparedStatement pst = cnx.prepareStatement(request);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Ordre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
          //sort list by date
            
         
            }
              sortedlist = list.stream()
  .sorted(Comparator.comparing(Ordre::getCheckoutDate))
  .collect(Collectors.toCollection(FXCollections::observableArrayList));
             return sortedlist;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return sortedlist;
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
    
    @Override
    public void orderPdf(Ordre o){
        try{

    Document document=new Document();
    PdfWriter.getInstance(document,new FileOutputStream("d://ordrePdf.pdf"));
    document.open();
   Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

Chunk chunk = new Chunk("Your invoice ", font);

document.add(chunk);


Image image=Image.getInstance("logo.png");
document.add(image);
document.add(new Paragraph("\n\n"));

chunk = new Chunk("userid: "+o.getId(), font);
document.add(chunk);
document.add(new Paragraph("\n"));
chunk = new Chunk("user adress: "+o.getUserAdress(), font);
document.add(chunk);
document.add(new Paragraph("\n"));
chunk = new Chunk("user phone :"+o.getUserPhone(), font);
document.add(chunk);
document.add(new Paragraph("\n"));
chunk = new Chunk("total price :"+o.getTotalPrice(), font);
document.add(chunk);

  
document.close();
System.out.println("pdf ajouter !");
        }catch(Exception e){
        System.out.println("Error PDF "+e);
        
        }
    
    }
    
@Override
   public void riotApi(String nickname){
       BufferedReader reader;
String line;
StringBuffer responseContent;
    responseContent = new StringBuffer();
    try {
        HttpURLConnection conn = (HttpURLConnection) new URL("https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+nickname+"?api_key=RGAPI-c17075fa-59f9-4fc7-ada2-6c4f9c20a687").openConnection();
    conn.setRequestMethod("GET");
    conn.setConnectTimeout(5000);
    conn.setReadTimeout(5000);
int status=conn.getResponseCode();
if(status==200){

reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
while( (line = reader.readLine()) != null){
responseContent.append(line);
}
reader.close();
System.out.println(responseContent.toString());
}else{
System.out.println("error:"+status);
}
    } catch (MalformedURLException ex) {
        Logger.getLogger(OrdreService.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(OrdreService.class.getName()).log(Level.SEVERE, null, ex);
    }

   
   
   }
   
   
   @Override
    public ObservableList<Ordre> rechercher(String adress) {
    
       ObservableList<Ordre> list = FXCollections.observableArrayList();
         ObservableList<Ordre> searchResult = FXCollections.observableArrayList();
try {
            String request = "SELECT * FROM `order";
            PreparedStatement pst = cnx.prepareStatement(request);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Ordre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
          //sort list by date
            
         
            }
          searchResult = list.stream()
  .filter(a -> a.getCity().equals( adress)) 
  .collect(Collectors.toCollection(FXCollections::observableArrayList));
             return searchResult;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
   
   
   
   
    }
    

