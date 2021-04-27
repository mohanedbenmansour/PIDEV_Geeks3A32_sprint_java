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
public class Promotion {
    private int id;
    private int event_id;
    private String event;
    private int product_id;
    private String product;
    private String descriptionpromotion;
    private Date start_date_promotion;
    private Date end_date_promotion;
    private float pourcentage;

    public Promotion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getDescriptionpromotion() {
        return descriptionpromotion;
    }

    public void setDescriptionpromotion(String descriptionpromotion) {
        this.descriptionpromotion = descriptionpromotion;
    }

    public Date getStart_date_promotion() {
        return start_date_promotion;
    }

    public void setStart_date_promotion(Date start_date_promotion) {
        this.start_date_promotion = start_date_promotion;
    }

    public Date getEnd_date_promotion() {
        return end_date_promotion;
    }

    public void setEnd_date_promotion(Date end_date_promotion) {
        this.end_date_promotion = end_date_promotion;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Promotion(int id, int event_id, int product_id, String descriptionpromotion, Date start_date_promotion, Date end_date_promotion, float pourcentage) {
        this.id = id;
        this.event_id = event_id;
        this.product_id = product_id;
        this.descriptionpromotion = descriptionpromotion;
        this.start_date_promotion = start_date_promotion;
        this.end_date_promotion = end_date_promotion;
        this.pourcentage = pourcentage;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Promotion(int id, int event_id, String event, int product_id, String product, String descriptionpromotion, Date start_date_promotion, Date end_date_promotion, float pourcentage) {
        this.id = id;
        this.event_id = event_id;
        this.event = event;
        this.product_id = product_id;
        this.product = product;
        this.descriptionpromotion = descriptionpromotion;
        this.start_date_promotion = start_date_promotion;
        this.end_date_promotion = end_date_promotion;
        this.pourcentage = pourcentage;
    }

 

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", event_id=" + event_id + ", product_id=" + product_id + ", descriptionpromotion=" + descriptionpromotion + ", start_date_promotion=" + start_date_promotion + ", end_date_promotion=" + end_date_promotion + ", pourcentage=" + pourcentage + '}';
    }

    public Promotion(int event_id,  int product_id,  String descriptionpromotion, Date start_date_promotion, Date end_date_promotion, float pourcentage) {
        this.event_id = event_id;
        this.product_id = product_id;
        this.descriptionpromotion = descriptionpromotion;
        this.start_date_promotion = start_date_promotion;
        this.end_date_promotion = end_date_promotion;
        this.pourcentage = pourcentage;
    }
    
    
}
