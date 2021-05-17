package com.example.movierecommendo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private RequestQueue q;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.input);


        Button search = (Button)findViewById(R.id.search);



        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movie = et.getText().toString();
                String url = "https://tastedive.com/api/similar?q=" + movie + "&type=movies&limit=5";

                jsonParse(t1,t2,t3,t4,t5,url);
            }
        });

    }
    private void jsonParse(TextView t1, TextView t2, TextView t3, TextView t4, TextView t5, String url){
        q = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray arr = response.getJSONObject("Similar").getJSONArray("Results");
                            t1.setText(arr.getJSONObject(0).getString("Name"));
                            t2.setText(arr.getJSONObject(1).getString("Name"));
                            t3.setText(arr.getJSONObject(2).getString("Name"));
                            t4.setText(arr.getJSONObject(3).getString("Name"));
                            t5.setText(arr.getJSONObject(4).getString("Name"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        q.add(request);
    }
}
