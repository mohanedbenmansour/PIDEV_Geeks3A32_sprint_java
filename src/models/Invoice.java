/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.stream.Stream;

/**
 *
 * @author mohan
 */
public class Invoice {

    public static Stream<Invoice> equals(String name, String name0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      private int id ,phone ,subtotal, tax ,totalCost;
      private String name, email,   creationDate ;
       public Invoice (String name,String email,int phone,int subtotal,int tax,int totalCost,String creationDate){
           this.name=name;
           this.email=email;
           this.creationDate=creationDate;
           this.subtotal=subtotal;
           this.tax=tax;
           this.totalCost=totalCost;
           this.phone=phone;
       }  
        public Invoice (int id,String name,String email,int phone,int subtotal,int tax,int totalCost,String creationDate){
          this.id=id;
            this.name=name;
           this.email=email;
           this.creationDate=creationDate;
           this.subtotal=subtotal;
           this.tax=tax;
           this.totalCost=totalCost;
           this.phone=phone;
       }  
          public Invoice (){
          
       }  

    @Override
    public String toString() {
        return "invoice{" + "id=" + id + ", phone=" + phone + ", subtotal=" + subtotal + ", tax=" + tax + ", totalCost=" + totalCost + ", name=" + name + ", email=" + email + ", creationDate=" + creationDate + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public int getPhone() {
        return phone;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public int getTax() {
        return tax;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
