package com.example.crudfirebase;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.crudfirebase.model.Requests;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private DatabaseReference database;

    private ArrayList<Requests> daftarReq;
    //private RequestAdapterRecyclerView requestAdapterRecyclerView;

    private RecyclerView listItem;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }
}
