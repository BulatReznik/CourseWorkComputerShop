package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Adapter.CategoryAdapter;
import com.example.courseworkcomputershop.data.Models.Category;
import com.example.courseworkcomputershop.data.Retrofit.CategoryApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity
{
    private RecyclerView categoryRecyclerView;
    private Category category;
    private boolean admin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        categoryRecyclerView = findViewById(R.id.categoriesListView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadCategories();

        Button buttonAddCategory = findViewById(R.id.buttonAddCategory);
        buttonAddCategory.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(CategoryActivity.this, EditCategoryActivity.class);
            category = new Category();
            category.setName("Null");
            changeActivity.putExtra("updateCategory", category);
            startActivity(changeActivity);
        });

        Button buttonBackToMain = findViewById(R.id.buttonCategoryToMain);
        buttonBackToMain.setOnClickListener(view ->
        {
            Intent changeActivity;
            if(admin)
            {
                changeActivity = new Intent(CategoryActivity.this, MainActivityAdmin.class);
            }
            else
            {
                changeActivity = new Intent(CategoryActivity.this, MainActivity.class);
            }
            startActivity(changeActivity);
        });
    }

    private void loadCategories()
    {
        RetrofitService retrofitService = new RetrofitService();
        CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);
        categoryApi.getAllCategories()
                .enqueue(new Callback<List<Category>>()
                {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response)
                    {
                        populateListView(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t)
                    {
                        Logger.getLogger(Category.class.getName()).log(Level.SEVERE, "Failed to load consignments", t);
                    }
                });
    }

    private void populateListView(List<Category> categoryList)
    {
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCategories();
    }
}