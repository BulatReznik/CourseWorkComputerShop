package com.example.courseworkcomputershop.data.Retrofit;
import com.example.courseworkcomputershop.data.Models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi
{
    @GET("/order/get-all")
    Call<List<Order>> getAllOrders();

    @POST("/order/save")
    Call<Order> save(@Body Order order);

    @DELETE("/order/delete/{id}")
    Call<Void> delete(@Path("id")  int id);


    @POST("/order/{orderId}/consignment/{consignmentId}/{count}/add")
    Call<OrderDto> addConsignmentToOrder(@Path("orderId") int orderId, @Path("consignmentId") int consignmentId, @Path("count") int count);
}
