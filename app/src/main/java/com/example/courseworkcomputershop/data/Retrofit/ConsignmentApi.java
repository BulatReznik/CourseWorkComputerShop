package com.example.courseworkcomputershop.data.Retrofit;

import com.example.courseworkcomputershop.data.Models.Consignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.Path;

public interface ConsignmentApi
{
    @GET("/consignment/get-all")
    Call<List<Consignment>> getAllConsignments();

    @POST("/consignment/save")
    Call<Consignment> save(@Body Consignment consignment);

    @DELETE("/consignment/delete/{id}")
    Call<Void> delete(@Path("id")  int id);

}
