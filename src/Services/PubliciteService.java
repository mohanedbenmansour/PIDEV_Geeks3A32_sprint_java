/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Publicite;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import utiles.DataBase;
import javafx.scene.image.Image;



/**
 *
 * @author USER
 */
public class PubliciteService {
    
 private Connection con;
    private Statement ste;

    public PubliciteService() {
        con = DataBase.getInstance().getConnection();

    }
    
    public void add(Publicite p) throws SQLException {

        ste = con.createStatement();
        int etat  = (p.isEtat()) ? 1 : 0;
        String requeteInsert ="Insert Into publicite (content, image, 	date_debut, date_fin,etat ) VALUES ('"+p.getContent()+"',  '"+p.getImage()+"', '"+p.getDateDebut()+"',  '"+p.getDateFin()+"',  '"+""+etat+"')";
   
        ste.executeUpdate(requeteInsert);
    }
    
    public List<Publicite> readAll() throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       List<Publicite> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from publicite ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String content=rs.getString("content");
               String image=rs.getString("image");
               Date dateDebut=rs.getDate("date_debut");
               Date dateFin=rs.getDate("date_fin");
               boolean etat=rs.getBoolean("etat");

               
               Publicite p=new Publicite(id,content, image, dateDebut, dateFin, etat);
     arr.add(p);
     }
    return arr;
    }
    
    public boolean delete(int id) throws SQLException {
        if (exist(id))
     {  
        ste = con.createStatement();
        String requetedelete="delete from publicite where `id`='"+id+"';";
        ste.execute(requetedelete);
        return true;
     }
     else 
     {
      System.out.println("not here");
      return false ;
     }
    }
    
    public Boolean exist(int id) throws SQLException {
         if(getById(id)!=null)
        {
            return true;
        }
        return false;  
    }
    
    public Publicite getById(int id) throws SQLException {
        ste=con.createStatement();
        String query="select * from publicite where id='"+id+"'";
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            Publicite p=new Publicite(rs.getInt("id"),rs.getString("content"),rs.getString("image"),rs.getDate("date_debut"),rs.getDate("date_fin"),rs.getBoolean("etat"));
            return p;
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean update(int id,String content, String image, Date dateDebut, Date dateFin, boolean etat) throws SQLException {
        if (exist(id))
           {
        int status = (etat ? 1 :0);
        String requeteUpadte="update publicite set content='"+content+"',image='"+image+"',date_debut='"+dateDebut+"',date_fin='"+dateFin+"',etat='"+status+"' where id='"+id+"'";
        ste.executeUpdate(requeteUpadte);
        return true ;}
         else 
             return false ;

    }
    
    public List<Publicite> search(String s) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(readAll().stream().filter(e->e.getContent().startsWith(s)).collect(Collectors.toList()).isEmpty())
          {
            System.out.println("Class does not exist");
          }
            return readAll().stream().filter(e->e.getContent().startsWith(s)).collect(Collectors.toList());
          }
    
    public Set<Publicite> tree(Publicite e) throws SQLException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      Set<Publicite> set=new TreeSet<>((a,b)->a.getContent().compareTo(b.getContent()));
        set.addAll(readAll());
        return set ;
    }
    public Image getImagebyId(int id) throws SQLException, FileNotFoundException, IOException {
     String sql="select path from publicite where id=? ";
      
     PreparedStatement pre=con.prepareStatement(sql);
     pre.setInt(1, id);
     ResultSet rs=pre.executeQuery();
       while(rs.next())
                {
         try {
             InputStream is = rs.getBinaryStream("path") ;
             OutputStream os = new FileOutputStream(new File("photo.jpg"));
             byte[]content = new byte[1024];
             int size = 0;
             while((size=is.read(content))!= -1)
             {
                 os.write(content,0,size);
             }
             os.close();
             is.close();
             
             Image img = new Image("file:photo.jpg",150,200,true,true) {
                 public int getWidth(ImageObserver observer) {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 public int getHeight(ImageObserver observer) {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 public ImageProducer getSource() {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 public Graphics getGraphics() {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 public Object getProperty(String name, ImageObserver observer) {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }
             };
             return img;
         } catch (IOException ex) {
             //Logger.getLogger(ServiceParticipation.class.getName()).log(Level.SEVERE, null, ex);
         }
                }
       rs.close();
       pre.close();
       return null ;
 
 }
    
}
