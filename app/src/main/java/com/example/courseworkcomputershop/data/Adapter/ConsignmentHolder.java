package com.example.courseworkcomputershop.data.Adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseworkcomputershop.R;

public class ConsignmentHolder extends RecyclerView.ViewHolder
{
    TextView name;
    TextView price;
    TextView description;
    Button updateButton;
    Button deleteButton;
    Context context;

    public ConsignmentHolder(@NonNull View itemView)
    {
     super(itemView);
     context = itemView.getContext();
     name = itemView.findViewById(R.id.consignmentListItem_name);
     price = itemView.findViewById(R.id.consignmentListItem_price);
     description = itemView.findViewById(R.id.consignmentListItem_description);
     updateButton = itemView.findViewById(R.id.buttonUpdateConsignment);
     deleteButton = itemView.findViewById(R.id.buttonDeleteConsignment);
    }
}
