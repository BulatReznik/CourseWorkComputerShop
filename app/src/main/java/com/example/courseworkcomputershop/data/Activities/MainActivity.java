package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.courseworkcomputershop.R;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonConsignment = findViewById(R.id.buttonConsignment);
        Button buttonOrder = findViewById(R.id.buttonOrder);
        Button buttonCategory = findViewById(R.id.buttonCategorys);
        Button buttonReport = findViewById(R.id.buttonReport);
        Button buttonBack = findViewById(R.id.buttonLogOut);

        buttonConsignment.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(MainActivity.this, ConsignmentActivity.class);
            startActivity(changeActivity);
        });
        buttonOrder.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(changeActivity);
        });
        buttonCategory.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(changeActivity);
        });

        buttonReport.setOnClickListener(view -> {
            Intent changeActivity = new Intent(MainActivity.this, ReportActivity.class);
            startActivity(changeActivity);
        });

        buttonBack.setOnClickListener(view -> {
            Intent changeActivity = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(changeActivity);
        });
    }
}