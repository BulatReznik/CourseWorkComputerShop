package com.example.courseworkcomputershop.data.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;

import com.example.courseworkcomputershop.R;
import com.example.courseworkcomputershop.data.Models.Consignment;
import com.example.courseworkcomputershop.data.Models.Order;
import com.example.courseworkcomputershop.data.Retrofit.ConsignmentApi;
import com.example.courseworkcomputershop.data.Retrofit.OrderApi;
import com.example.courseworkcomputershop.data.Retrofit.RetrofitService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportActivity extends AppCompatActivity
{
    List<Consignment> consignmentList = new ArrayList<>();
    List<Order> orderList = new ArrayList<>();
    String filenameConsignment="";
    String filenameOrder="";
    String dateTo;
    String dateFrom;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Button buttonSaveConsignment = findViewById(R.id.buttonSaveReport);
        Button buttonSaveOrder = findViewById(R.id.buttonSaveOrderReport);
        Button buttonToMain = findViewById(R.id.buttonReportToMain);

        TextView textViewFileNameConsignment = findViewById(R.id.editTextFileNameConsignment);
        TextView textViewFileNameOrder= findViewById(R.id.editTextReportOrder);
        TextView textViewDateTo = findViewById(R.id.editTextFromDate);
        TextView textViewDateFrom = findViewById(R.id.editTextToDate);

        buttonSaveConsignment.setOnClickListener(view ->
        {
            filenameConsignment = textViewFileNameConsignment.getText().toString();
            loadConsignments();
        });

        buttonSaveOrder.setOnClickListener(view ->
        {
            filenameOrder = textViewFileNameOrder.getText().toString();
            dateTo = textViewDateTo.getText().toString();
            dateFrom = textViewDateFrom.getText().toString();
            loadOrders();
        });
        buttonToMain.setOnClickListener(view ->
        {
            Intent changeActivity = new Intent(ReportActivity.this, MainActivity.class);
            startActivity(changeActivity);
        });
    }
    public void createPDFConsignment(List<Consignment> consignmentList, String name) throws IOException, DocumentException
    {
        Document document = new Document();  // create the document
        File root = new File(Environment.getExternalStorageDirectory(), "Notes");
        if (!root.exists()) {
            root.mkdirs();   // create root directory in sdcard
        }
        File file = new File("/storage/emulated/0/Download/PDF/"+ name +".pdf");
        PdfWriter.getInstance(document,new FileOutputStream(file));
        document.open();  // open the directory

        java.util.List<Paragraph> paragraphs = new ArrayList<>();

        for(Consignment receiving:consignmentList)
        {
            Paragraph p1 = new Paragraph();
            p1.add(receiving.toString());
            paragraphs.add(p1);

        }
        Paragraph p2 = new Paragraph();
        p2.add("Consignments:");
        document.add(p2);
        for(Paragraph paragraph:paragraphs){
            document.add(paragraph);
        }
        document.addCreationDate();
        document.close();
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
                        consignmentList = response.body();
                        try
                        {
                            createPDFConsignment(consignmentList, filenameConsignment);
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        } catch (DocumentException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Consignment>> call, Throwable t)
                    {
                        Logger.getLogger(EditConsignmentActivity.class.getName()).log(Level.SEVERE, "Failed to load consignments", t);
                    }
                });
    }

    private void createPDFOrder(List<Order> orderList, String name, String dateFrom, String dateTo) throws IOException, DocumentException {

        Document document = new Document();  // create the document
        File root = new File(Environment.getExternalStorageDirectory(), "Notes");
        if (!root.exists()) {
            root.mkdirs();   // create root directory in sdcard
        }
        File file = new File("/storage/emulated/0/Download/PDF/"+ name +".pdf");
        PdfWriter.getInstance(document,new FileOutputStream(file));
        document.open();

        java.util.List<Paragraph> paragraphs = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy 'в' HH:mm");
        DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
        Date startDate = new Date();
        Date finishDate = new Date();
        try {
            startDate = df.parse(dateTo);
            finishDate = df.parse(dateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Order> newOrderList = new ArrayList<>();
        for(int i = 0; i<orderList.size(); i++)
        {

            try {
                Date date = format.parse(orderList.get(i).getDate());
                if((date.after(startDate)) && date.before(finishDate))
                {
                    newOrderList.add(orderList.get(i));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        for(Order receiving:newOrderList)
        {
            Paragraph p1 = new Paragraph();
            p1.add(receiving.toString());
            paragraphs.add(p1);

        }
        Paragraph p2 = new Paragraph();
        p2.add("Orders:");
        document.add(p2);
        for(Paragraph paragraph:paragraphs){
            document.add(paragraph);
        }
        document.addCreationDate();
        document.close();
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
                        orderList = response.body();
                        try
                        {
                            createPDFOrder(orderList, filenameOrder, dateFrom, dateTo);
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        } catch (DocumentException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t)
                    {
                        Logger.getLogger(EditConsignmentActivity.class.getName()).log(Level.SEVERE, "Failed to load consignments", t);
                    }
                });
    }


}