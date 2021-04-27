/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Lenovo
 */


import java.sql.SQLException;
import models.Admin;
        
interface IServiceAdmin {
      void register(Admin t)throws SQLException;
    boolean update(Admin t, int id)throws SQLException;
    boolean ResetPassword(String pass, int id)throws SQLException;
    Admin login(String email, String password) throws SQLException;
    boolean delete(Admin t) throws SQLException;
}
