package com.example.courseworkcomputershop.data.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Models.User;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;
import com.example.courseworkcomputershop.data.Retrofit.UserApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserHolder>
{
    private List<User> userList;
    private User deleteUser;

    public UserAdapter(List<User> userList)
    {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user_item, parent, false);

        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position)
    {
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        User user = userList.get(position);
        holder.name.setText(String.valueOf(user.getFio()));
        holder.login.setText(String.valueOf(user.getLogin()));

        holder.deleteButton.setOnClickListener(view -> {
            deleteUser = userList.get(position);
            int id = deleteUser.getId();
            userApi.delete(id).enqueue(new Callback<Void>() {
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
        return userList.size();
    }
}
