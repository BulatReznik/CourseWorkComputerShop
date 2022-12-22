package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Models.Category;
import com.example.courseworkcomputershop.data.Models.Consignment;
import com.example.courseworkcomputershop.data.Retrofit.CategoryApi;
import com.example.courseworkcomputershop.data.Retrofit.ConsignmentApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditConsignmentActivity extends AppCompatActivity
{
    private Consignment consignmentUpdate;
    private boolean admin;
    private List<Category> categoryList;
    private ArrayAdapter<Category> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_consignment);

        RetrofitService retrofitService = new RetrofitService();

        ConsignmentApi consignmentApi = retrofitService.getRetrofit().create(ConsignmentApi.class);
        CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);

        Bundle arguments = getIntent().getExtras();
        consignmentUpdate = (Consignment) arguments.get("updateConsignment");
        //admin = (boolean) arguments.get("admin");

        Button buttonConsignmentToConsignments = findViewById(R.id.buttonConsigmentToConsigments);
        Button buttonSaveConsignment = findViewById(R.id.buttonSaveConsignment);
        Button buttonLoadCategory = findViewById(R.id.buttonLoadCategory);
        TextView textViewName = findViewById(R.id.editTextConsigmentName);
        TextView textViewPrice = findViewById(R.id.editTextConsigmentPrice);
        TextView textViewDescription = findViewById(R.id.editTextConsigmentDescription);
        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);

        if (!consignmentUpdate.getName().equals("Null"))
        {
            textViewName.setText(consignmentUpdate.getName());
            textViewDescription.setText(consignmentUpdate.getDescription());
            textViewPrice.setText(""+consignmentUpdate.getPrice());
        }

        categoryApi.getAllCategories().enqueue(new Callback<List<Category>>()
        {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response)
            {
                populateListViewCategory(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t)
            {
            }

        });

        buttonLoadCategory.setOnClickListener(view ->
        {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);

            if (!consignmentUpdate.getName().equals("Null"))
            {
                for (int i = 0; i < categoryList.size(); ++i)
                {
                    if(consignmentUpdate.getCategory()!=null)
                    {
                        if (categoryList.get(i).getId() == consignmentUpdate.getCategory().getId())
                        {
                            spinnerCategory.setSelection(i);
                            break;
                        }
                    }
                }
            }
            spinnerCategory.setAdapter(adapter);
            /*consignmentListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            loadConsignmentList = orderUpdate.getConsignments();
            ArrayList<Integer> ids= new ArrayList<>();

            for(int i = 0; i <= loadConsignmentList.size()-1; i++)
            {
                ids.add(loadConsignmentList.get(i).getId());
            }

            for(int i = 0; i <= consignmentList.size()-1; i++)
            {
                if(ids.contains(consignmentList.get(i).getId()))
                {
                    consignmentListView.setItemChecked(i, true);
                }
            }*/
        });

        buttonSaveConsignment.setOnClickListener(view ->
        {
            Consignment consignment = new Consignment();

            if (!consignmentUpdate.getName().equals("Null"))
            {
                consignment.setId(consignmentUpdate.getId());
            }

            String name = textViewName.getText().toString();
            String price = textViewPrice.getText().toString();
            String description = textViewDescription.getText().toString();

            //Category category = adapter.getItem((int)spinnerCategory.getSelectedItemId());
            //consignment.setCategory(category);

            consignment.setName(name);
            consignment.setDescription(description);
            consignment.setPrice(Integer.parseInt(price));

            consignmentApi.save(consignment).enqueue(new Callback<Consignment>()
            {
                @Override
                public void onResponse(Call<Consignment> call, Response<Consignment> response)
                {
                    Intent changeActivity;
                    changeActivity = new Intent(EditConsignmentActivity.this, ConsignmentActivity.class);
                    //changeActivity.putExtra("admin", admin);
                    startActivity(changeActivity);
                }

                @Override
                public void onFailure(Call<Consignment> call, Throwable t)
                {
                    Logger.getLogger(EditConsignmentActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    Intent changeActivity;
                    changeActivity = new Intent(EditConsignmentActivity.this, ConsignmentActivity.class);
                    //changeActivity.putExtra("admin", admin);
                    startActivity(changeActivity);
                }

            });

        });


        buttonConsignmentToConsignments.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(EditConsignmentActivity.this, ConsignmentActivity.class);
            startActivity(changeActivity);
        });
    }

    private void populateListViewCategory(List<Category> categoryList1){
     categoryList = categoryList1;
    }
}