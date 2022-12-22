package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Adapter.ConsignmentAdapter;
import com.example.courseworkcomputershop.data.Models.Consignment;
import com.example.courseworkcomputershop.data.Retrofit.ConsignmentApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsignmentActivity extends AppCompatActivity
{
    private RecyclerView consignmentRecyclerView;
    Consignment consignment;
    private boolean admin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consigment);

        Bundle arguments = getIntent().getExtras();
        //admin = (boolean) arguments.get("admin");

        consignmentRecyclerView = findViewById(R.id.consignmentsListView);
        consignmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadConsignments();

        Button buttonAddConsignment = findViewById(R.id.buttonAddConsigment);
        buttonAddConsignment.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(ConsignmentActivity.this, EditConsignmentActivity.class);
            consignment = new Consignment();
            consignment.setName("Null");
            changeActivity.putExtra("updateConsignment", consignment);
            changeActivity.putExtra("admin", admin);
            startActivity(changeActivity);
        });

        Button buttonBackToMain = findViewById(R.id.buttonConsigmentToMain);
        buttonBackToMain.setOnClickListener(view ->
        {
            Intent changeActivity;
            if(admin)
            {
                changeActivity = new Intent(ConsignmentActivity.this, MainActivityAdmin.class);
            }
            else
            {
                changeActivity = new Intent(ConsignmentActivity.this, MainActivity.class);
            }
            startActivity(changeActivity);
        });
    }
    private void loadConsignments()
    {
        RetrofitService retrofitService = new RetrofitService();
        ConsignmentApi consignmentApi = retrofitService.getRetrofit().create(ConsignmentApi.class);
        consignmentApi.getAllConsignments()
                .enqueue(new Callback<List<Consignment>>()
                {
                    @Override
                    public void onResponse(Call<List<Consignment>> call, Response<List<Consignment>> response)
                    {
                        populateListView(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Consignment>> call, Throwable t)
                    {
                        Logger.getLogger(EditConsignmentActivity.class.getName()).log(Level.SEVERE, "Failed to load consignments", t);
                    }
                });
    }

    private void populateListView(List<Consignment> consignmentList)
    {
        ConsignmentAdapter consignmentAdapter = new ConsignmentAdapter(consignmentList);
        consignmentRecyclerView.setAdapter(consignmentAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadConsignments();
    }
}