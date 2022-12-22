package com.example.courseworkcomputershop.data.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseworkcomputershop.R;

public class UserHolder extends RecyclerView.ViewHolder
{
    TextView name;
    TextView login;
    Button deleteButton;
    Context context;

    public UserHolder(@NonNull View itemView)
    {
        super(itemView);
        context = itemView.getContext();
        name = itemView.findViewById(R.id.userListItem_FIO);
        login = itemView.findViewById(R.id.userListItem_Login);
        deleteButton = itemView.findViewById(R.id.buttonDeleteUser);
    }
}
