/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author asus
 */
public class Event {
        
    private int id;
    private String nom;
    private String lieu;
    private String description;
    private float prix;
    private String img;
    private int nbParticipants;
    private Date datePub;
    private Date dateDebut;
    private Date dateFin;
    private String etat;
    private int userId;
    private int categoryId;

    public Event(int id, String nom, String lieu, String description, float prix, String img,int nbParticipants, Date datePub, Date dateDebut, Date dateFin, String etat, int userId, int categoryId) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.nbParticipants = nbParticipants;
        this.datePub = datePub;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.etat = etat;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Event(String nom, String lieu, String description, float prix, String img,int nbParticipants, Date datePub, Date dateDebut, Date dateFin, String etat, int userId, int categoryId) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.nbParticipants = nbParticipants;
        this.datePub = datePub;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.etat = etat;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }
    
    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", description=" + description + ", prix=" + prix + ", img=" + img + ", datePub=" + datePub + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", etat=" + etat + ", userId=" + userId + ", categoryId=" + categoryId + '}';
    }



    
    
    
}
