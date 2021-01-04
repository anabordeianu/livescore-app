package com.example.livescore_app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Meciuri extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private String url ="https://cricapi.com/api/matches?apikey=Sf9m2Tn8gsbCZMRlNUqBWRxzZJS2";


    private RecyclerView.Adapter mAdapter;
    private List<Model> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_meciuri);

        //recyclerView
        mRecyclerView=findViewById(R.id.recyclerView);
        //setarea proprietatilor
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        modelList=new ArrayList<>();

        //functie pentru preluarea datelor de pe site

        loadUrlData();
    }

    private void loadUrlData() {
        //bara de progres care va fi afisata in timp ce sunt primite datele meciurilor
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Meciurile sunt incarcate...");
        pd.show();

        //cerere de date

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            pd.dismiss();
            try {
                JSONArray jsonArray= new JSONObject(response).getJSONArray("matches");
                for(int i=0;i<jsonArray.length();i++){
                    try {
                        String uniqueID= jsonArray.getJSONObject(i).getString("unique_id");
                        String gazde= jsonArray.getJSONObject(i).getString("team-1");
                        String oaspeti= jsonArray.getJSONObject(i).getString("team-2");
                        String meciTip= jsonArray.getJSONObject(i).getString("type");
                        String status= jsonArray.getJSONObject(i).getString("matchStarted");
                        if (status.equals("true")) {
                            status = "Meciul a inceput";
                        }
                        else
                        {
                            status="Meciul nu a inceput";
                        }
                        String TimpGMT=jsonArray.getJSONObject(i).getString("dateTimeGMT");
                        SimpleDateFormat format1=new SimpleDateFormat(" yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        format1.setTimeZone(TimeZone.getTimeZone(TimpGMT));
                        Date data = format1.parse(TimpGMT);
                        //conversia la formatul zi/luna/an
                        SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        format2.setTimeZone(TimeZone.getTimeZone("GMT"));
                        String dateTime=format2.format(data);

                        //seteaza data

                        Model model=new Model(uniqueID,gazde, oaspeti, meciTip, status, dateTime);
                        modelList.add(model);
                    }
                    catch (Exception e){
                        Toast.makeText(Meciuri.this," "+e.getMessage(),Toast.LENGTH_SHORT);
                    }
                }
                mAdapter=new Adaptor(modelList,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }
            catch (Exception e){
                Toast.makeText(Meciuri.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //in caz de eroare, un mesaj va fi afisat
                Toast.makeText(Meciuri.this, "Eroare: "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
