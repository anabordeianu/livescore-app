package com.example.livescore_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetaliiMeci extends AppCompatActivity {

    TextView mEchipa1_tv, mEchipa2_tv, mStatusMeci_tv, mScor_tv,mDescriere_tv,mData_tv;
    private String url="https://cricapi.com/api/cricketScore/?apikey=Sf9m2Tn8gsbCZMRlNUqBWRxzZJS2&unique_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_meci);

        //action bar

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Detalii meci");
        //activarea butonului in action bar
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        String id=intent.getStringExtra("meci_id");
        String data=intent.getStringExtra("data");
        url= url +id;

        mEchipa1_tv=findViewById(R.id.gazde_tv);
        mEchipa2_tv=findViewById(R.id.oaspeti_tv);
        mStatusMeci_tv=findViewById(R.id.statusMeci_tv);
        mScor_tv=findViewById(R.id.scor_tv);
        mDescriere_tv=findViewById(R.id.descriere_tv);
        mData_tv=findViewById(R.id.dataMeci_tv);

        mData_tv.setText(data); 
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); //te intoarce la activitatea precedenta
        return super.onSupportNavigateUp();
    }
}