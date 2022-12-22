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

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button buttonToSignUp = findViewById(R.id.buttonActivitySignUp);
        Button buttonRegistration = findViewById(R.id.buttonRegistration);
        TextView textViewLogin = findViewById(R.id.editTextTextEmailAddressReg);
        TextView textViewName = findViewById(R.id.editTextTextPersonName5);
        TextView textViewPassword1 = findViewById(R.id.editTextTextPasswordReg);
        TextView textViewPassword2 = findViewById(R.id.editTextTextPasswordReg2);


        buttonRegistration.setOnClickListener(view ->
        {
            String login = textViewLogin.getText().toString();
            String name = textViewName.getText().toString();
            String password1 = textViewPassword1.getText().toString();
            String password2 = textViewPassword2.getText().toString();
            if(!login.isEmpty() && login!=null && !name.isEmpty() && name!=null && !password1.isEmpty() && password1!=null && !password2.isEmpty() && password2!=null)
            {
                if(password1.equals(password2))
                {
                    RetrofitService retrofitService = new RetrofitService();
                    UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

                    User user = new User();
                    user.setLogin(login);
                    user.setFio(name);
                    user.setPassword(password1);
                    userApi.save(user).enqueue(new Callback<User>()
                    {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response)
                        {
                            Intent changeActivity = new Intent(RegistrationActivity.this, SignUpActivity.class);
                            startActivity(changeActivity);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t)
                        {
                            Intent changeActivity = new Intent(RegistrationActivity.this, SignUpActivity.class);
                            startActivity(changeActivity);
                            Logger.getLogger(RegistrationActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });
                }
                else
                {
                    Toast.makeText(RegistrationActivity.this,
                            "Пароли не совпадают!", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(RegistrationActivity.this,
                        "Не все данные были введены!", Toast.LENGTH_LONG).show();
            }
        });
        buttonToSignUp.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(RegistrationActivity.this, SignUpActivity.class);
            startActivity(changeActivity);
        });

    }
}