/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Lenovo
 */
public class User {
    int id;
    String username;
    String roles;
    String password;
    String email;
    String phone_number;
    String image;
    String photocover;
    String isverified;
    String nom;
    String prenom;
    String facebookProfil;
    String twitch_profil;
    String youtube_channel;

    public User() {
    }

    public User(int id, String username, String roles, String password, String email, String phone_number, String image, String photocover, String isverified, String nom, String prenom, String facebookProfil, String twitch_profil, String youtube_channel) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.image = image;
        this.photocover = photocover;
        this.isverified = isverified;
        this.nom = nom;
        this.prenom = prenom;
        this.facebookProfil = facebookProfil;
        this.twitch_profil = twitch_profil;
        this.youtube_channel = youtube_channel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhotocover() {
        return photocover;
    }

    public void setPhotocover(String photocover) {
        this.photocover = photocover;
    }

    public String getIsverified() {
        return isverified;
    }

    public void setIsverified(String isverified) {
        this.isverified = isverified;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFacebookProfil() {
        return facebookProfil;
    }

    public void setFacebookProfil(String facebookProfil) {
        this.facebookProfil = facebookProfil;
    }

    public String getTwitch_profil() {
        return twitch_profil;
    }

    public void setTwitch_profil(String twitch_profil) {
        this.twitch_profil = twitch_profil;
    }

    public String getYoutube_channel() {
        return youtube_channel;
    }

    public void setYoutube_channel(String youtube_channel) {
        this.youtube_channel = youtube_channel;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", role=" + roles + ", image=" + image + '}';
    }
    
}
