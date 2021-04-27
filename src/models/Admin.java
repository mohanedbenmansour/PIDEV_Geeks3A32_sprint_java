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
public class Admin extends User{

    public Admin() {
        id=-1;
    }

    public Admin(int id, String username, String password, String email, String phone_number, String image, String photocover, String isverified, String nom, String prenom, String facebookProfil, String twitch_profil, String youtube_channel) {
        super(id, username,"ROLE_ADMIN", password, email, phone_number, image, photocover, isverified, nom, prenom, facebookProfil, twitch_profil, youtube_channel);
    }
    
    
}
