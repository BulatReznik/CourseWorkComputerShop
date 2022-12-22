package com.example.courseworkcomputershop.data.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseworkcomputershop.data.Activities.EditOrderActivity;
import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Models.Order;
import com.example.courseworkcomputershop.data.Retrofit.OrderApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder>
{
    private List<Order> orderList;
    private Order updateOrder;
    private Order deleteOrder;

    public OrderAdapter(List<Order> orderList)
    {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_order_item, parent, false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position)
    {
        RetrofitService retrofitService = new RetrofitService();
        OrderApi orderApi = retrofitService.getRetrofit().create(OrderApi.class);

        Order order = orderList.get(position);
        holder.name.setText(order.getName());
        holder.date.setText("Дата заказа:\n" + order.getDate());

        holder.name.setText(String.valueOf(order.getName()));

        holder.updateButton.setOnClickListener(view ->
        {
            updateOrder = orderList.get(position);
            Intent intent = new Intent(holder.context, EditOrderActivity.class);
            intent.putExtra("updateOrder", updateOrder);
            holder.context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(view ->
        {
            deleteOrder = orderList.get(position);
            int id = deleteOrder.getId();
            orderApi.delete(id).enqueue(new Callback<Void>()
            {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    Intent i = new Intent(holder.context, holder.context.getClass());
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
    public int getItemCount()
    {
        return orderList.size();
    }
}
