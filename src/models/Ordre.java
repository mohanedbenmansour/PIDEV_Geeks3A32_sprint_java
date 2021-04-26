/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author mohan
 */
public class Ordre implements Comparable<Ordre>{
    private int id;
    private String userId;
    private String userAdress;
    private String checkoutDate ;
    private int  userPhone;
    private boolean  status;
    private String city;
    private String state;
    private int zipcode;
    private int totalPrice;
    
    public Ordre(int id,String userId,String userAdress,String checkoutDate,
    int  userPhone, boolean  status,String city,String state,int zipcode,
   int totalPrice 
    ){
        this.id=id; 
        this.userId=userId;
        this.userAdress=userAdress;
        this.checkoutDate=checkoutDate;
        this.userPhone=userPhone;
        this.status=status;
        this.city=city;
        this.state=state;
        this.zipcode=zipcode;
        this.totalPrice=totalPrice;
           
    }
    public Ordre(String userId,String userAdress,String checkoutDate,
    int  userPhone, boolean  status,String city,String state,int zipcode,
   int totalPrice 
    ){
     
        this.userId=userId;
        this.userAdress=userAdress;
        this.checkoutDate=checkoutDate;
        this.userPhone=userPhone;
        this.status=status;
        this.city=city;
        this.state=state;
        this.zipcode=zipcode;
        this.totalPrice=totalPrice;
           
    }
    
     @Override
    public String toString() {
        return "ordre{" + "id=" + id + ", userid=" + userId + ", userAdress=" + userAdress + ", checkoutDate=" + checkoutDate + ", userPhone=" + userPhone + ", status=" + status + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode +" , totalPrice=" + totalPrice +'}';
    }

    public int getId(){return this.id;}
    public int getUserId(){return this.id;}
    public String getUserAdress(){return this.userAdress;}
    public String getCheckoutDate(){return this.checkoutDate;}
    public int getUserPhone(){return this.userPhone;}
    public boolean getStatus(){return this.status;}
    public String getCity(){return this.city;}
    public String getState(){return this.state;}
    public int getZipcode(){return this.zipcode;}
    public int getTotalPrice(){return this.totalPrice;}

    
    @Override
  public int compareTo(Ordre o) {
    if (getCheckoutDate() == null || o.getCheckoutDate() == null) {
      return 0;
    }
    return getCheckoutDate().compareTo(o.getCheckoutDate());
  }
}
