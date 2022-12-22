package com.example.courseworkcomputershop.data.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseworkcomputershop.data.Activities.EditCategoryActivity;
import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Models.Category;
import com.example.courseworkcomputershop.data.Retrofit.CategoryApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder>
{
    private List<Category> categoryList;
    private Category updateCategory;
    private Category deleteCategory;

    public CategoryAdapter(List<Category> categoryList)
    {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_category_item, parent, false);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position)
    {
        RetrofitService retrofitService = new RetrofitService();
        CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);

        Category category = categoryList.get(position);
        holder.name.setText(String.valueOf(category.getName()));
        holder.updateButton.setOnClickListener(view ->
        {
            updateCategory = categoryList.get(position);
            Intent intent = new Intent(holder.context, EditCategoryActivity.class);
            intent.putExtra("updateCategory", updateCategory);
            holder.context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(view -> {
            deleteCategory = categoryList.get(position);
            int id = deleteCategory.getId();
            categoryApi.delete(id).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    Intent i = new Intent(holder.context , holder.context.getClass() );
                    holder.context.startActivity(i);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t)
                {

                }
            });

        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
