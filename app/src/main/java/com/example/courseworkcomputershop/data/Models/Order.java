package com.example.courseworkcomputershop.data.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable
{
    private int id;
    private String name;
    private String date;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    private float total;

    private List<ConsignmentOrder> consignments = new ArrayList<>();

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public void addConsignmentOrder(ConsignmentOrder consignmentOrder) {consignments.add(consignmentOrder);}
    public List<ConsignmentOrder> getConsignments() {return consignments;}
    public void setConsignments(List<ConsignmentOrder> consignments) {this.consignments = consignments;}


    @Override
    public String toString() {
        return "Order: " +
                " number: " + id +
                ", name: " + name + '\'' +
                ", date: " + date + '\'';
    }
}
