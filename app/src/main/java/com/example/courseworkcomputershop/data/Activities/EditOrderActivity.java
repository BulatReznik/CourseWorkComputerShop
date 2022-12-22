package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Models.Consignment;
import com.example.courseworkcomputershop.data.Models.ConsignmentOrder;
import com.example.courseworkcomputershop.data.Models.Order;
import com.example.courseworkcomputershop.data.Retrofit.ConsignmentApi;
import com.example.courseworkcomputershop.data.Retrofit.OrderApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditOrderActivity extends AppCompatActivity
{
    private Order orderUpdate;
    private ArrayAdapter<Consignment> adapter;
    private boolean admin;
    private List<Consignment> consignmentList;
    private List<Consignment> checkedConsignmentList;
    private List<ConsignmentOrder> loadConsignmentList;
    private List<ConsignmentOrder> consignmentOrderList;
    private ConsignmentOrder consignmentOrder;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        Bundle arguments = getIntent().getExtras();
        orderUpdate = (Order) arguments.get("updateOrder");
        //admin = (boolean) arguments.get("admin");

        RetrofitService retrofitService = new RetrofitService();
        OrderApi orderApi = retrofitService.getRetrofit().create(OrderApi.class);
        ConsignmentApi consignmentApi = retrofitService.getRetrofit().create(ConsignmentApi.class);

        TextView textViewName = findViewById(R.id.editTextOrderName);
        Button buttonSaveOrder = findViewById(R.id.buttonSaveOrder);
        ListView consignmentListView = findViewById(R.id.consignmentOrderListView);
        Button buttonLoad = findViewById(R.id.buttonLoad);
        consignmentList = new ArrayList<>();
        checkedConsignmentList = new ArrayList<>();
        loadConsignmentList = new ArrayList<>();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

        consignmentApi.getAllConsignments().enqueue(new Callback<List<Consignment>>()
        {
            @Override
            public void onResponse(Call<List<Consignment>> call, Response<List<Consignment>> response)
            {
                populateListViewConsignment(response.body());
            }

            @Override
            public void onFailure(Call<List<Consignment>> call, Throwable t)
            {
            }

        });
        buttonLoad.setOnClickListener(view -> {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, consignmentList);
            consignmentListView.setAdapter(adapter);
            consignmentListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
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
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////

        if (!orderUpdate.getName().equals("Null"))
        {
            textViewName.setText(orderUpdate.getName());
        }

        buttonSaveOrder.setOnClickListener(view ->
        {
            Order order = new Order();

            for (int i = consignmentListView.getCount() -1; i >= 0; i--)
            {
                if(consignmentListView.isItemChecked(i))
                {
                    checkedConsignmentList.add((Consignment) consignmentListView.getAdapter().getItem(i));
                }
            }

            if (!orderUpdate.getName().equals("Null"))
            {
                order.setId(orderUpdate.getId());
            }
            String name = textViewName.getText().toString();
            order.setName(name);

            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy 'в' HH:mm");
            Date date = new Date(System.currentTimeMillis());
            String s = formatter.format(date);
            order.setDate(s);

            orderApi.save(order).enqueue(new Callback<Order>()
            {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response)
                {
                    Intent changeActivity;
                    changeActivity = new Intent(EditOrderActivity.this, OrderActivity.class);
                    //changeActivity.putExtra("admin", admin);
                    startActivity(changeActivity);
                    /*
                    for(int i = checkedConsignmentList.size(); i <= 0 ; i--)
                    {
                        orderApi.addConsignmentToOrder(order.getId(),checkedConsignmentList.get(i).getId(), 1).enqueue(new Callback<OrderDto>()
                        {
                            @Override
                            public void onResponse(Call<OrderDto> call, Response<OrderDto> response)
                            {
                                if(checkedConsignmentList.size()==count)
                                {

                                }
                                count++;
                            }

                            @Override
                            public void onFailure(Call<OrderDto> call, Throwable t)
                            {
                            }
                        });
                        count++;
                    }
                     */
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t)
                {
                    /*
                    Logger.getLogger(EditOrderActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    Intent changeActivity;
                    changeActivity = new Intent(EditOrderActivity.this, OrderActivity.class);
                    //changeActivity.putExtra("admin", admin);
                    startActivity(changeActivity);*/
                }
            });
            /*
            Intent changeActivity = new Intent(EditOrderActivity.this, OrderActivity.class);
            startActivity(changeActivity);*/
        });


        Button buttonOrderToOrders = findViewById(R.id.buttonOrderToOrders);
        buttonOrderToOrders.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(EditOrderActivity.this, OrderActivity.class);
            startActivity(changeActivity);
        });
    }
    private void populateListViewConsignment(List<Consignment> consignmentList2)
    {
        consignmentList = consignmentList2;
    }
}