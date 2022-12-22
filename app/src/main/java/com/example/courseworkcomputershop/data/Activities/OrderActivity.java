package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Adapter.OrderAdapter;
import com.example.courseworkcomputershop.data.Models.Order;
import com.example.courseworkcomputershop.data.Retrofit.OrderApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity
{
    private RecyclerView orderRecyclerView;
    private Order order;
    private boolean admin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Bundle arguments = getIntent().getExtras();
        //admin = (boolean) arguments.get("admin");

        orderRecyclerView = findViewById(R.id.ordersListView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadOrders();

        Button buttonAddOrder = findViewById(R.id.buttonAddOrder);
        buttonAddOrder.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(OrderActivity.this, EditOrderActivity.class);
            order = new Order();
            order.setName("Null");
            changeActivity.putExtra("updateOrder", order);
            //changeActivity.putExtra("admin", admin);
            startActivity(changeActivity);
        });

        Button buttonBackToMain = findViewById(R.id.buttonOrderToMain);
        buttonBackToMain.setOnClickListener(view ->
        {
            Intent changeActivity;
            if(admin)
            {
                changeActivity = new Intent(OrderActivity.this, MainActivityAdmin.class);
            }
            else
            {
                changeActivity = new Intent(OrderActivity.this, MainActivity.class);
            }
            startActivity(changeActivity);
        });
    }

    private void loadOrders()
    {
        RetrofitService retrofitService = new RetrofitService();
        OrderApi orderApi = retrofitService.getRetrofit().create(OrderApi.class);
        orderApi.getAllOrders()
                .enqueue(new Callback<List<Order>>()
                {
                    @Override
                    public void onResponse(Call<List<Order>> call, Response<List<Order>> response)
                    {
                        populateListView(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t)
                    {
                        Logger.getLogger(OrderActivity.class.getName()).log(Level.SEVERE, "Failed to load orders", t);
                    }
                });
    }

    private void populateListView(List<Order> orderList)
    {
        OrderAdapter orderAdapter = new OrderAdapter(orderList);
        orderRecyclerView.setAdapter(orderAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadOrders();
    }
}