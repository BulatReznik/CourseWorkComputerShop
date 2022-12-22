package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.courseworkcomputershop.R;

public class MainActivityAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        Button buttonConsignment = findViewById(R.id.buttonConsignment2);
        Button buttonOrder = findViewById(R.id.buttonOrder2);
        Button buttonCategory = findViewById(R.id.buttonCategorys2);
        Button buttonUser = findViewById(R.id.buttonUsers);
        Button buttonBack = findViewById(R.id.buttonLogOutAdmin);
        boolean admin = true;

        buttonConsignment.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(MainActivityAdmin.this, ConsignmentActivity.class);
            //changeActivity.putExtra("admin", admin);
            startActivity(changeActivity);
        });
        buttonOrder.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(MainActivityAdmin.this, OrderActivity.class);
            //changeActivity.putExtra("admin", admin);
            startActivity(changeActivity);
        });
        buttonCategory.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(MainActivityAdmin.this, CategoryActivity.class);
            //changeActivity.putExtra("admin", admin);
            startActivity(changeActivity);
        });

        buttonUser.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(MainActivityAdmin.this, UserActivity.class);
            startActivity(changeActivity);
        });

        buttonBack.setOnClickListener(view -> {
            Intent changeActivity = new Intent(MainActivityAdmin.this, SignUpActivity.class);
            startActivity(changeActivity);
        });
    }
}