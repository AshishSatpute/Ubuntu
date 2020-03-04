package com.xlix.basuons;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import URLhelper.BaseHelper;
import URLhelper.SharedPref;

public class Withdraw extends AppCompatActivity {
    TextView walletrs, error;
    EditText amounted;
    String wal= "0";
    Button withdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        findviewbyid();
        onclick();
        walle();
    }

    private void walle() {
        StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.withdrawRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try{
                    obj = new JSONObject(response);
                    boolean status = obj.getBoolean("status");
                    if (status == true){
                        String data = obj.getString("data");

                        walletrs.setText(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", SharedPref.getString(getApplication(),"user_id"));
                params.put("withdraw_amount", "0");
                Log.d("TAG", "Error = " + params.toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }

    private void onclick() {

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wal= amounted.getText().toString().trim();

                if (Integer.parseInt(wal) >= 100 ){
                    error.setVisibility(View.GONE);
                    withdrawamount();
                    Log.i("TAG", "ftt"+wal);
                }else{
                    error.setVisibility(View.VISIBLE);
                   error.setText("minimum amount should be more than 100");
                }


            }
        });

    }

    private void withdrawamount() {
        StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.withdrawRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try{
                    obj = new JSONObject(response);
                    boolean status = obj.getBoolean("status");
                    if (status == true){
                       String data = obj.getString("data");
                        String msg = obj.getString("msg");
                       Toast.makeText(Withdraw.this, "your transaction is"+msg, Toast.LENGTH_SHORT).show();
                        error.setVisibility(View.VISIBLE);
                        error.setText("Request Sended to admin");
                        walletrs.setText(data);
                        Log.i("TAG", "datat"+data);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", SharedPref.getString(getApplication(),"user_id"));
                params.put("withdraw_amount", wal);
                Log.d("TAG", "Error = " + params.toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }

    private void findviewbyid() {
        walletrs = findViewById(R.id.walletrs);
        amounted = findViewById(R.id.witamt);
        error = findViewById(R.id.error);
        withdraw = findViewById(R.id.withnow);
    }
}
