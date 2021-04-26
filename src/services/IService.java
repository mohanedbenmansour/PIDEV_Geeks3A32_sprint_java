/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import javafx.collections.ObservableList;


/**
 *
 * @author mohan
 * @param <T>
 * @param <U>
 */
public interface IService <T,U>{
    public void ajouter(T t,U u);

    
    public void supprimer(T t);
    
    public void modifier(T t);
    
    public  ObservableList<T> afficher();
    public  ObservableList<T> afficherParOrdre();
        public ObservableList<T> rechercher(String s);

    public String sendSms();
    public void orderPdf(T t);
    public void riotApi(String s);

}
