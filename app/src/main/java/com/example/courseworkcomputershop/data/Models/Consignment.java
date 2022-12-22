package com.example.courseworkcomputershop.data.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Consignment implements Serializable
{
    private int id;
    private int price;
    private String description;
    private String name;
    private Category category;
    private List<ConsignmentOrder> consignmentOrders = new ArrayList<>();

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Category getCategory() {return category;}
    public void setCategory(Category category)
    {
        if(category!=null)
        {
            this.category = category;
            if(!category.getConsignments().contains(this))
            {
                category.getConsignments().add(this);
            }
        }
    }

    public List<ConsignmentOrder> getConsignmentOrders() {return consignmentOrders;}
    public void setConsignmentOrders(List<ConsignmentOrder> consignmentOrders) {this.consignmentOrders = consignmentOrders;}
    public void addConsignmentOrders(ConsignmentOrder consignmentOrder){this.consignmentOrders.add(consignmentOrder);}

    @Override
    public String toString()
    {
        return "Consignment: " +
                "Identifier: " + id +
                ", Name: " + name +
                ", Price: " + price +
                ", Description: " + description + '\'';
    }
}
