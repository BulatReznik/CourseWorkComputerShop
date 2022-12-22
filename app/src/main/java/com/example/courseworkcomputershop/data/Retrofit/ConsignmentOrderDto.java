package com.example.courseworkcomputershop.data.Retrofit;

import com.example.courseworkcomputershop.data.Models.ConsignmentOrder;

import java.io.Serializable;

public class ConsignmentOrderDto implements Serializable
{
    private int id;
    private String consignmentName;
    private int count;
    private float subtotal;

    public static ConsignmentOrderDto from(ConsignmentOrder consignmentOrder) {
        ConsignmentOrderDto consignmentOrderDto = new ConsignmentOrderDto();
        consignmentOrderDto.setId(consignmentOrder.getId());
        consignmentOrderDto.setConsignmentName(consignmentOrder.getConsignment().getName());
        consignmentOrderDto.setCount(consignmentOrder.getCount());
        consignmentOrderDto.setSubtotal(consignmentOrder.getConsignment().getPrice()*consignmentOrder.getCount());
        return consignmentOrderDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConsignmentName() {
        return consignmentName;
    }

    public void setConsignmentName(String consignmentName) {
        this.consignmentName = consignmentName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

}
