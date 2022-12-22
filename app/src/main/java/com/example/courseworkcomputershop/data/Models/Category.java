package com.example.courseworkcomputershop.data.Models;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable
{
    private int id;
    private String name;
    private List<Consignment> consignments;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public void addConsignment(Consignment consignment)
    {
        consignments.add(consignment);
        if(consignment.getCategory()!= this){
            consignment.setCategory(this);
        }
    }
    public List<Consignment> getConsignments(){return this.consignments;}

    public void setConsignments(List<Consignment> consignments) {this.consignments = consignments; }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
