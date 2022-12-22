package com.example.courseworkcomputershop.data.Adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseworkcomputershop.R;

public class OrderHolder extends RecyclerView.ViewHolder
{
    TextView name;
    TextView date;
    Button updateButton;
    Button deleteButton;
    Context context;

    public OrderHolder(@NonNull View itemView)
    {
        super(itemView);
        context = itemView.getContext();
        name = itemView.findViewById(R.id.orderListItem_Name);
        date = itemView.findViewById(R.id.orderListItem_date);
        updateButton = itemView.findViewById(R.id.buttonUpdateOrder);
        deleteButton = itemView.findViewById(R.id.buttonDeleteOrder);
    }
}
