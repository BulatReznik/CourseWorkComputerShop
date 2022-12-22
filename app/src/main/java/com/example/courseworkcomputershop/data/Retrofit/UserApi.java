package com.example.courseworkcomputershop.data.Retrofit;
import com.example.courseworkcomputershop.data.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi
{
    @GET("/user/get-all")
    Call<List<User>> getAllUsers();

    @POST("/user/save")
    Call<User> save(@Body User user);

    @DELETE("/user/delete/{id}")
    Call<Void> delete(@Path("id")  int id);

}
