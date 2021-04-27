/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Date;

import models.Gamers;
import services.serviceGamer;
import utils.DataSource;

/**
 *
 * @author asus
 */
public class DesktopApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
//        Gamers gamer = new Gamers(50,"naaame","lieuuu","descpp","descpp","descpp","descpp","descpp");
        
        Gamers ss = new Gamers("aa", "20", "cc", "dd", "ee", "ff", "gg");
        
        serviceGamer se = new serviceGamer();
        se.supprimer(ss);
        se.afficher().forEach(System.out::println);

        }
    
}
