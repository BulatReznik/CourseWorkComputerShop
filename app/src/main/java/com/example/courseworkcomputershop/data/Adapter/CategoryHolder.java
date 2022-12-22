package com.example.courseworkcomputershop.data.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseworkcomputershop.R;

public class CategoryHolder extends RecyclerView.ViewHolder
{
    TextView name;
    Button updateButton;
    Button deleteButton;
    Context context;

    public CategoryHolder(@NonNull View itemView)
    {
        super(itemView);
        context = itemView.getContext();
        name = itemView.findViewById(R.id.categoryListItem_name);
        updateButton = itemView.findViewById(R.id.buttonUpdateCategory);
        deleteButton = itemView.findViewById(R.id.buttonDeleteCategory);
    }
}
