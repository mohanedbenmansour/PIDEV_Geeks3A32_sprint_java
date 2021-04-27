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
public class Gamers {
    int id;
    String name="";
    String age="";
    String description="";
    String player_photo="";
    String gamer_twitch="";
    String gamer_facebook="";
    String gamer_team="";
    
    

    public Gamers() {
    }

    public Gamers(int id, String name, String age, String description, String player_photo, String gamer_twitch, String gamer_facebook, String gamer_team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.description = description;
        this.player_photo = player_photo;
        this.gamer_twitch = gamer_twitch;
        this.gamer_facebook = gamer_facebook;
        this.gamer_team = gamer_team;
    }

    public Gamers(String name, String age, String description, String player_photo, String gamer_twitch, String gamer_facebook, String gamer_team) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.player_photo = player_photo;
        this.gamer_twitch = gamer_twitch;
        this.gamer_facebook = gamer_facebook;
        this.gamer_team = gamer_team;
    }
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlayer_photo() {
        return player_photo;
    }

    public void setPlayer_photo(String player_photo) {
        this.player_photo = player_photo;
    }

    public String getGamer_twitch() {
        return gamer_twitch;
    }

    public void setGamer_twitch(String gamer_twitch) {
        this.gamer_twitch = gamer_twitch;
    }

    public String getGamer_facebook() {
        return gamer_facebook;
    }

    public void setGamer_facebook(String gamer_facebook) {
        this.gamer_facebook = gamer_facebook;
    }

    public String getGamer_team() {
        return gamer_team;
    }

    public void setGamer_team(String gamer_team) {
        this.gamer_team = gamer_team;
    }
    
    
    @Override
    public String toString() {
        return "Gamers{" + "id=" + id + ", nom=" + name + ", age=" + age + ", description=" + description + ", player photo=" + player_photo + ", player twitch=" + gamer_twitch +", player facebook=" + gamer_facebook + ", player team=" + gamer_team + '}';
    }
    
}
