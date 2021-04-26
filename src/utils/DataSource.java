/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author mohan
 */
public class DataSource {
    private static DataSource instance;
    private Connection cnx;
    private final String URL="JDBC:mysql://localhost:3306/geeks";
    private final String LOGIN="root";
    private final String PASSWORD="";
    
    private DataSource(){
    try{
        cnx=  (Connection) DriverManager.getConnection(URL,LOGIN,PASSWORD);
        System.out.println("connecting");
    }catch(SQLException ex){
    System.err.println(ex.getMessage());
    }
        }
    
    public static DataSource getInstance(){
    
    if(instance==null){
        instance=new DataSource();
    }
    return instance;
    }    
    public Connection getConnection(){
        return cnx;
    }
    
}
