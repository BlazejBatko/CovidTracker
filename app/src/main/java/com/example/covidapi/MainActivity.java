package com.example.covidapi;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    private TextView dzisiaj, wyleczonychPL, zakazenPL, zgonowPL, wyleczonychSW, zakazenSW, zgonowSW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        wyleczonychPL = findViewById(R.id.WyleczonychPL);
        zakazenPL = findViewById(R.id.ZakazonychPL);
        zgonowPL = findViewById(R.id.ZgonowPL);


        wyleczonychSW = findViewById(R.id.WyleczonychSW);
        zakazenSW = findViewById(R.id.ZakazonychSW);
        zgonowSW = findViewById(R.id.ZgonowSW);

        String date = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());

        dzisiaj = findViewById(R.id.data);

        dzisiaj.setText("na dzień: "+date);



        //URL REQUEST

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://corona.lmao.ninja/v2/all";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response.toString());

                            String wyleczonychPLL= jsonObject.getString("todayRecovered");
                            String zakazenPLL = jsonObject.getString("todayCases");
                            String zgonowPLL = jsonObject.getString("todayDeaths");

                            wyleczonychSW.setText(wyleczonychPLL);
                            zakazenSW.setText(zakazenPLL);
                            zgonowSW.setText(zgonowPLL);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        String urlpl = "https://corona.lmao.ninja/v2/countries/pl";

        JsonObjectRequest jsonObjectRequestPL = new JsonObjectRequest
                (Request.Method.GET, urlpl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response.toString());

                            String wyleczonychPLL= jsonObject.getString("todayRecovered");
                            String zakazenPLL = jsonObject.getString("todayCases");
                            String zgonowPLL = jsonObject.getString("todayDeaths");

                            wyleczonychPL.setText(wyleczonychPLL);
                            zakazenPL.setText(zakazenPLL);
                            zgonowPL.setText(zgonowPLL);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        requestQueue.add(jsonObjectRequest);
        requestQueue.add(jsonObjectRequestPL);
        


        


    }


}