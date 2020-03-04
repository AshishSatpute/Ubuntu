package com.xlix.basuons;

import android.content.Intent;
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

public class PasswordActivity extends AppCompatActivity {
    EditText mobiler;
    TextView text;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
         mobiler = findViewById(R.id.mobilev);
         submit = findViewById(R.id.submitfed);
         text = findViewById(R.id.textView3);


        final String mobile1 = mobiler.getText().toString();

         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.forgot_password, new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         Log.d("TAG","Response = "+response);
                         JSONObject obj = null;
                         try{
                             obj = new JSONObject(response);
                             boolean status = obj.getBoolean("response");

                             if (status == true){
                                 String msg = obj.getString("msg");
                                 Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                                 text.setText(msg);
                                 Intent intnet = new Intent(PasswordActivity.this,LoginActivity.class);
                                 startActivity(intnet);
                                 // where to go now
                             }else{
                                 String msg = obj.getString("msg");
                                 Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                                 text.setText(msg);
                                 //text.setTextColor(getResources().getColor(R.color.red_900));

                             }

                         }catch (JSONException e){

                         }

                     }
                 }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Log.d("TAG","Error = "+error.getMessage());

                     }
                 }){
                     @Override
                     protected Map<String, String> getParams() throws AuthFailureError {
                         Map<String, String> params = new HashMap<String, String>();
                         params.put("username", mobile1);

                         Log.d("TAG","Error = "+params.toString());
                         return params;
                     }
                 };
                 RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                 queue.add(request);

             }
         });

    }
}
