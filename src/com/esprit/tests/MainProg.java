/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import com.esprit.models.Promotion;
import com.esprit.models.Publicity;
import com.esprit.services.impl.ServiceCategoryProductImpl;

import java.sql.Date;

/**
 *
 * @author aissa
 */
public class MainProg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        ServiceCategoryProductImpl  s=new ServiceCategoryProductImpl();
        s.afficher().forEach(System.out::println);
    }
    
}
