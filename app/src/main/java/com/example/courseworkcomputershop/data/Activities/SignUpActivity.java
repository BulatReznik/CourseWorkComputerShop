package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Models.User;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;
import com.example.courseworkcomputershop.data.Retrofit.UserApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity
{
    List<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button registrationButton = findViewById(R.id.buttonActivityRegistration);
        Button signupButton = findViewById(R.id.buttonSignUp);
        TextView textViewLogin = findViewById(R.id.editTextTextEmailAddressSignUp);
        TextView textViewPassword = findViewById(R.id.editTextTextPasswordSignUp);
        loadUsers();


        registrationButton.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(SignUpActivity.this, RegistrationActivity.class);
            startActivity(changeActivity);
        });

        signupButton.setOnClickListener(view ->
        {
            String login = textViewLogin.getText().toString();
            String password = textViewPassword.getText().toString();
            String loginConfirm="";
            String passwordConfirm="";
            if(!login.isEmpty() && login!=null && !password.isEmpty() && password!=null)
            {
                if(login.equals("admin") && password.equals("admin"))
                {
                    Intent changeActivity = new Intent(SignUpActivity.this, MainActivityAdmin.class);
                    startActivity(changeActivity);
                }
                else
                {
                    for(int i = 0; i < userList.size(); i++)
                    {
                        loginConfirm = userList.get(i).getLogin();
                        passwordConfirm = userList.get(i).getPassword();
                        if( loginConfirm.equals(login) &&  passwordConfirm.equals(password))
                        {
                            Intent changeActivity = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(changeActivity);
                            break;
                        }

                    }
                }

            } else
            {
                Toast.makeText(this, "Вы не ввели логин или пароль", Toast.LENGTH_LONG).show();
            }

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
                       userList = response.body();
                    }
                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t)
                    {
                    }
                });
    }
}