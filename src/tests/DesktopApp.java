/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Date;
import models.CategoryEvent;
import models.Event;
import services.ServiceCategoryEvent;
import services.ServiceEvent;
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
        ServiceCategoryEvent sce = new ServiceCategoryEvent();
        sce.ajouter(new CategoryEvent("category1"));
        sce.afficher().forEach(System.out::println);
        
        Event myEvent = new Event("naaame","lieuuu","descpp",20,"imgg",5,new Date(),new Date(),new Date(),"enabled",4,1);
        
        ServiceEvent se = new ServiceEvent();
        se.ajouter (myEvent);
        se.afficher().forEach(System.out::println);

        }
    
}
