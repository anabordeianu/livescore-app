package com.example.livescore_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

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
        
        loadData();
    }

    private void loadData() {
        //bara de progres care va fi afisata in timp ce sunt primite datele meciurilor
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Meciurile sunt incarcate...");
        pd.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            pd.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(response);
                String gazde = jsonObject.getString("team-1");
                String oaspeti = jsonObject.getString("team-2");
                String statusMeci = jsonObject.getString("matchStarted");
                if(statusMeci.equals("true")){
                    statusMeci="Meciul a inceput";
                }
                else{
                    statusMeci="Meciul nu a inceput";
                }
                mEchipa1_tv.setText(gazde);
                mEchipa2_tv.setText(oaspeti);
                mStatusMeci_tv.setText(statusMeci);
                try {
                    //aceste valori vor fi primite doar daca meciul a inceput
                    //asa ca trebuie trecute intr-un try catch separat
                    String scor=jsonObject.getString("score");
                    String descriere=jsonObject.getString("description");
                    mScor_tv.setText(scor);
                    mDescriere_tv.setText(descriere);
                }
                catch(Exception e){
                    Toast.makeText(DetaliiMeci.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                Toast.makeText(DetaliiMeci.this,""+e.getMessage() ,Toast.LENGTH_SHORT).show();
            }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetaliiMeci.this,"Ai eroare, pariorule: "+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); //te intoarce la activitatea precedenta
        return super.onSupportNavigateUp();
    }
}