package com.example.courseworkcomputershop.data.Retrofit;

import com.example.courseworkcomputershop.data.Models.Category;
import com.example.courseworkcomputershop.data.Models.Consignment;

import java.lang.reflect.Method;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryApi
{
    @GET("/category/category")
    Call<Category> getCategory(@Field("int") int id);

    @GET("/category/get-all")
    Call<List<Category>> getAllCategories();

    @POST("/category/save")
    Call<Category> save(@Body Category category);

    @DELETE("/category/delete/{id}")
    Call<Void> delete(@Path("id")  int id);
}
