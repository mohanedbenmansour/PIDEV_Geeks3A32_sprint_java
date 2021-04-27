/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;




/**
 *
 * @author Fourat
 */
public class Tournoi {
    
    private int id;
    private String nom;
    private String description;
    private String adresse;
    private Date date_tournoi;
    private Date date_publication;
    private int nb_max;
    private int category_Id;
    private String image;
    private String lien_youtube;
    private int active ;
    private int user_t_id;

    public Tournoi(int id, String nom, String description, String adresse, Date date_tournoi, Date date_publication, int nb_max, int category_Id, String image, String lien_youtube, int active, int user_t_id) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.date_tournoi = date_tournoi;
        this.date_publication = date_publication;
        this.nb_max = nb_max;
        this.category_Id = category_Id;
        this.image = image;
        this.lien_youtube = lien_youtube;
        this.active = active;
        this.user_t_id = user_t_id;
    }

    public Tournoi(String nom, String description, String adresse, Date date_tournoi, Date date_publication, int nb_max, int category_Id, String image, String lien_youtube, int active, int user_t_id) {
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.date_tournoi = date_tournoi;
        this.date_publication = date_publication;
        this.nb_max = nb_max;
        this.category_Id = category_Id;
        this.image = image;
        this.lien_youtube = lien_youtube;
        this.active = active;
        this.user_t_id = user_t_id;
    }

    public Tournoi(String nom, String description, String adresse, Date date_tournoi, int nb_max, int category_Id) {
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.date_tournoi = date_tournoi;
        this.nb_max = nb_max;
        this.category_Id = category_Id;
    }


    

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate_tournoi() {
        return date_tournoi;
    }

    public void setDate_tournoi(Date date_tournoi) {
        this.date_tournoi = date_tournoi;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLien_youtube() {
        return lien_youtube;
    }

    public void setLien_youtube(String lien_youtube) {
        this.lien_youtube = lien_youtube;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getUser_t_id() {
        return user_t_id;
    }

    public void setUser_t_id(int user_t_id) {
        this.user_t_id = user_t_id;
    }


    

    public int getNb_max() {
        return nb_max;
    }

    public void setNb_max(int nb_max) {
        this.nb_max = nb_max;
    }

    public int getCategory_Id() {
        return category_Id;
    }

    public void setCategory_Id(int Category_Id) {
        this.category_Id = Category_Id;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", adresse=" + adresse + ", date_tournoi=" + date_tournoi + ", date_publication=" + date_publication + ", nb_max=" + nb_max + ", category_Id=" + category_Id + ", image=" + image + ", lien_youtube=" + lien_youtube + ", active=" + active + ", user_t_id=" + user_t_id + '}';
    }




}
