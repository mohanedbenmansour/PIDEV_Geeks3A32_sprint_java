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
public class OrderDetail {
    private int id;
    private String quantity;
    private int payment;
    private Ordre orderId ;
    private int product ;

    public OrderDetail(int id,String quantity,int payment,int product){
        this.id=id; 
       this.quantity=quantity;
       this.payment=payment;
       this.product=product;
           
    }
    
     @Override
    public String toString() {
        return "ordreDetail{" + "id=" + id + ", quantity=" + quantity + ", payment=" + payment + ", orderId=" + orderId.getId() + ", product=" + product + '}';
    }

    public int getId(){return this.id;}
    public String getQuantity(){return this.quantity;}
    public int getPayment(){return this.payment;}
    public Ordre getOrderId(){return this.orderId;}
    public int getProduct(){return this.product;}

    //id,userId,userAdress,checkoutDate,userPhone,status,city,state,zipcode,orderdetail, totalPrice 
}
