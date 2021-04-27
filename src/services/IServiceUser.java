/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import models.User;

/**
 *
 * @author Lenovo
 */
public interface IServiceUser {
    void register(User t)throws SQLException;
    boolean update(User t, int id)throws SQLException;
    boolean ResetPassword(String pass, int id)throws SQLException;
    User login(String email, String password) throws SQLException;
    boolean delete(User t) throws SQLException;
    User FindById(int id) throws SQLException;
    User FindByEmail(String email) throws SQLException;
    User FindByPohne(String phone) throws SQLException;
    void ResetPassword(User t)throws SQLException;
    public String sendSms() throws SQLException;
}
