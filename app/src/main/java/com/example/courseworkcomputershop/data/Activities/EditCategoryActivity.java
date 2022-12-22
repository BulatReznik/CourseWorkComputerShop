package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Models.Category;
import com.example.courseworkcomputershop.data.Retrofit.CategoryApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCategoryActivity extends AppCompatActivity
{
    private Category categoryUpdate;
    private boolean admin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        Bundle arguments = getIntent().getExtras();
        categoryUpdate = (Category) arguments.get("updateCategory");
        //admin = (boolean) arguments.get("admin");

        RetrofitService retrofitService = new RetrofitService();
        CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);

        TextView textViewName = findViewById(R.id.editTextCategoryName);
        Button buttonSaveCategory = findViewById(R.id.buttonSaveCategory);

        if (!categoryUpdate.getName().equals("Null")) {
            textViewName.setText(categoryUpdate.getName());
        }

        buttonSaveCategory.setOnClickListener(view ->
        {
            Category category = new Category();
            if (!categoryUpdate.getName().equals("Null"))
            {
                category.setId(categoryUpdate.getId());
            }
            String name = textViewName.getText().toString();
            category.setName(name);

            categoryApi.save(category).enqueue(new Callback<Category>()
            {
                @Override
                public void onResponse(Call<Category> call, Response<Category> response)
                {
                    Intent changeActivity;
                    changeActivity = new Intent(EditCategoryActivity.this, CategoryActivity.class);
                    changeActivity.putExtra("admin", admin);
                    startActivity(changeActivity);
                }

                @Override
                public void onFailure(Call<Category> call, Throwable t)
                {
                    Logger.getLogger(EditCategoryActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    Intent changeActivity;
                    changeActivity = new Intent(EditCategoryActivity.this, CategoryActivity.class);
                    //changeActivity.putExtra("admin", admin);
                    startActivity(changeActivity);
                }
            });


        });

        Button buttonCategoryToCategories = findViewById(R.id.buttonCategoryToCaterories);
        buttonCategoryToCategories.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(EditCategoryActivity.this, CategoryActivity.class);
            startActivity(changeActivity);
        });
    }

    /*
    private void loadCategory(int update)
    {
        RetrofitService retrofitService = new RetrofitService();
        CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);
        categoryApi.getCategory(update)
                .enqueue(new Callback<Category>()
                {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response)
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
    */


}