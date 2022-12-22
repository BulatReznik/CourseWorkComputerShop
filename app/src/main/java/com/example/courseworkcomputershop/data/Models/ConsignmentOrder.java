package com.example.courseworkcomputershop.data.Models;

import java.io.Serializable;

public class ConsignmentOrder implements Serializable
{
    private int id;
    private Consignment consignment;
    private Order order;
    private int count;
    private float subtotal;

    public ConsignmentOrder(){}


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public Consignment getConsignment() {return consignment;}
    public void setConsignment(Consignment consignment) {this.consignment = consignment;}

    public Order getOrder() {return order;}
    public void setOrder(Order order) {this.order = order;}

    public int getCount() {return count;}
    public void setCount(int count) {this.count = count;}

    public float getSubtotal() {return subtotal;}
    public void setSubtotal(float subtotal) {this.subtotal = subtotal;}

}
