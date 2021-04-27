/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Fourat
 */
public class Connector {

    private static Connector instance;
    private Connection cnx;

    private final String URL = "jdbc:mysql://localhost:3306/geeks";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private Connector() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conncting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}