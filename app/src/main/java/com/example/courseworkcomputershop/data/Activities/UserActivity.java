package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Adapter.UserAdapter;
import com.example.courseworkcomputershop.data.Models.User;
import com.example.courseworkcomputershop.data.Retrofit.UserApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity
{
    private RecyclerView userRecyclerView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userRecyclerView = findViewById(R.id.usersListView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadUsers();

        Button buttonBackToMain = findViewById(R.id.buttonUserToMain);
        buttonBackToMain.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(UserActivity.this, MainActivityAdmin.class);
            startActivity(changeActivity);
        });
    }

    private void loadUsers()
    {
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        userApi.getAllUsers()
                .enqueue(new Callback<List<User>>()
                {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response)
                    {
                        populateListView(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t)
                    {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, "Failed to load consignments", t);
                    }
                });
    }

    private void populateListView(List<User> userList)
    {
        UserAdapter userAdapter = new UserAdapter(userList);
        userRecyclerView.setAdapter(userAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUsers();
    }
}