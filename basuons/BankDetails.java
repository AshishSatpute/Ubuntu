package com.xlix.basuons;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class BankDetails extends AppCompatActivity {
    EditText ifsc,acctno,branch,hname,adhar,pan;
    Button submit;
    String fullname = "", email = "",type = "", password = "", paddress = "", taddress = "", pincode = "", city = "", state = ""
            ,country = "",  reference = "", whatspp = "", gender = "",mobile = "";

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);

        Bundle bundle = getIntent().getExtras();
        fullname = bundle.getString("fullname");
        email = bundle.getString("email");
        type = bundle.getString("type");
        password = bundle.getString("password");
        paddress = bundle.getString("paddress");
        taddress = bundle.getString("taddress");
        pincode = bundle.getString("pincode");
        city = bundle.getString("city");
        state = bundle.getString("state");
        country = bundle.getString("country");
        reference = bundle.getString("reference");
        whatspp = bundle.getString("whatspp");
        gender = bundle.getString("gender");
        mobile = bundle.getString("mobile");
        Log.i("TAG", "type234"+type);

        findviewbyid();
        onclick();


    }

    private void onclick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(BankDetails.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                attemptregister();
            }
        });
    }

    private void attemptregister() {
        String getifsc = ifsc.getText().toString();
        String getacctno = acctno.getText().toString();
        String getbranch = branch.getText().toString();
        String gethname = hname.getText().toString();
        String getadhar = adhar.getText().toString();
        String getpan = pan.getText().toString();

        Log.d("TAG","Regisstion milind "  );


            registerap( fullname, mobile,email,getifsc,gethname ,getacctno,getbranch,taddress,paddress,city,state,country,password,pincode,reference,getadhar,getpan,whatspp,gender);


        
    }

    private void registerap(final String fullname,final  String mobile,final String email,final String getifsc,
                            final String gethname,
                            final String getacctno,
                            final String getbranch,
                            final String taddress,
                            final String paddress
            ,final String city,final String state,final String country,final String password,
                            final String pincode,final String reference,final String getadhar,
                            final String getpan,final String whatspp,final String gender) {

        StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.registration, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(response);
                    boolean status = obj.getBoolean("response");
                    Log.i("TAG", "status"+status);
                    if (status == true){
                       String msg = obj.getString("msg");
                       Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                       //where to go no
                        Intent innt = new Intent(BankDetails.this,LoginActivity.class);
                        startActivity(innt);
                        finish();

                    }
                    if (status == false){
                        String msg = obj.getString("msg");
                        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }


                }catch (JSONException e){
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("full_name", fullname);
                params.put("mobile",mobile);
                params.put("email",email);
                params.put("type",type);
                params.put("ifsc_code",getifsc);
                params.put("acc_name",gethname);
                params.put("acc_no",getacctno);
                params.put("acc_branchname",getbranch);
                params.put("address_one",paddress);
                params.put("address_two",taddress);
                params.put("city",city);
                params.put("state",state);
                params.put("country",country);
                params.put("password",password);
                params.put("pincode",pincode);
                params.put("refered_code",reference);
                params.put("aadharno",getadhar);
                params.put("panno",getpan);
                params.put("whats_app_no",whatspp);
                params.put("gender",gender);
              //  params.put("type","true");
                Log.i("TAG", "type309"+params.toString());
                return params;
            }

    }; RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }


    private void findviewbyid() {
        ifsc = (EditText)findViewById(R.id.ifsc);
        acctno = (EditText)findViewById(R.id.accountno);
        branch = (EditText)findViewById(R.id.branchname);
        hname = (EditText)findViewById(R.id.holdername);
        adhar = (EditText)findViewById(R.id.adharnumber);
        pan = (EditText)findViewById(R.id.pannumber);
        submit = (Button)findViewById(R.id.submit);

    }
}
