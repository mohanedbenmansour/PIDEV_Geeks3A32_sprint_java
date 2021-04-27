/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

import java.sql.Date;



/**
 *
 * @author Malek
 */
public class Publicity {
    private int id;
    private String description_pub;
    private String title_pub;
    private String picture_pub;
    private String position;
    private String link;
    private Date start_date_pub;
    private Date end_date_pub;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription_pub() {
        return description_pub;
    }

    public void setDescription_pub(String description_pub) {
        this.description_pub = description_pub;
    }

    public String getTitle_pub() {
        return title_pub;
    }

    public void setTitle_pub(String title_pub) {
        this.title_pub = title_pub;
    }

    public String getPicture_pub() {
        return picture_pub;
    }

    public void setPicture_pub(String picture_pub) {
        this.picture_pub = picture_pub;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getStart_date_pub() {
        return start_date_pub;
    }

    public void setStart_date_pub(Date start_date_pub) {
        this.start_date_pub = start_date_pub;
    }

    public Date getEnd_date_pub() {
        return end_date_pub;
    }

    public void setEnd_date_pub(Date end_date_pub) {
        this.end_date_pub = end_date_pub;
    }

    @Override
    public String toString() {
        return "Publicity{" + "id=" + id + ", description_pub=" + description_pub + ", title_pub=" + title_pub + ", picture_pub=" + picture_pub + ", position=" + position + ", link=" + link + ", start_date_pub=" + start_date_pub + ", end_date_pub=" + end_date_pub + '}';
    }

    public Publicity( Date start_date_pub, Date end_date_pub,String description_pub,String picture_pub, String title_pub,  String position, String link) {
        this.description_pub = description_pub;
        this.title_pub = title_pub;
        this.picture_pub = picture_pub;
        this.position = position;
        this.link = link;
        this.start_date_pub = start_date_pub;
        this.end_date_pub = end_date_pub;
    }

    public Publicity(int id, Date start_date_pub, Date end_date_pub, String description_pub,String picture_pub, String title_pub, String position, String link) {
        this.id = id;
        this.description_pub = description_pub;
        this.title_pub = title_pub;
        this.picture_pub = picture_pub;
        this.position = position;
        this.link = link;
        this.start_date_pub = start_date_pub;
        this.end_date_pub = end_date_pub;
    }

    public Publicity() {
    }
    
    
    
}
