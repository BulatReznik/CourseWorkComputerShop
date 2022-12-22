package com.example.courseworkcomputershop.data.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseworkcomputershop.data.Activities.EditConsignmentActivity;
import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Models.Consignment;
import com.example.courseworkcomputershop.data.Retrofit.ConsignmentApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsignmentAdapter extends RecyclerView.Adapter<ConsignmentHolder>
{
    private List<Consignment> consignmentList;
    private Consignment updateConsignment;
    private Consignment deleteConsignment;

    public ConsignmentAdapter(List<Consignment> consignmentList)
    {
        this.consignmentList = consignmentList;
    }

    @NonNull
    @Override
    public ConsignmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_consigment_item, parent, false);
        return new ConsignmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsignmentHolder holder, int position)
    {
        Consignment consignment = consignmentList.get(position);
        holder.name.setText(consignment.getName());
        holder.price.setText("Цена: " + String.valueOf(consignment.getPrice()));
        holder.description.setText(consignment.getDescription());

        RetrofitService retrofitService = new RetrofitService();
        ConsignmentApi consignmentApi = retrofitService.getRetrofit().create(ConsignmentApi.class);

        holder.updateButton.setOnClickListener(view ->
        {
            updateConsignment = consignmentList.get(position);
            Intent intent = new Intent(holder.context, EditConsignmentActivity.class);
            intent.putExtra("updateConsignment", updateConsignment);
            holder.context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(view ->
        {
            deleteConsignment = consignmentList.get(position);
            int id = deleteConsignment.getId();
            consignmentApi.delete(id).enqueue(new Callback<Void>()
            {
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
        return consignmentList.size();
    }
}
