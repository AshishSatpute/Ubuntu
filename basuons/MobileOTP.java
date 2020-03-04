package com.xlix.basuons;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MobileOTP extends AppCompatActivity {
    private static final String TAG = MobileOTP.class.getSimpleName();
    EditText mobile1,otptext;
    Button otp,submit;
    TextView messageopt,text1;
    TextView tv_coundown;
    private CountDownTimer countDownTimer;
    String getmobile;
    String fullname = "", email = "",type = "", password = "", paddress = "", taddress = "", pincode = "", city = "", state = ""
            ,country = "",  reference = "", whatspp = "", gender = "";

    String URLMOBILE = "http://mybasuons.com/api/User/sendSMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_otp);


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

        Log.i(TAG, "full "+type);
        findviewbyid();
        onclick();



    }

    private void onclick() {
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //otp code
                otptext.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                tv_coundown.setVisibility(View.VISIBLE);
                text1.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                phoneLogin();
                countDownTimer();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // submit.setVisibility(View.GONE);
                getmobile = mobile1.getText().toString();
                    if (getmobile.isEmpty()){
                        Toast.makeText(getApplicationContext(),"please submit your OTP",Toast.LENGTH_LONG).show();
                    }
                    else if(getmobile.length()<6){
                        verifyOtp();
                    }
                verifyOtp();

            }
        });
    }

    private void countDownTimer() {
        countDownTimer = new CountDownTimer(1000 * 60 * 10, 1000) {
            @Override
            public void onTick(long l) {
                String text = String.format(Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(l) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(l) % 60);
                tv_coundown.setText(text);
            }

            @Override
            public void onFinish() {
                tv_coundown.setText("00:00");
                mobile1.setText("");
            }
        };
        countDownTimer.start();
    }

    private void verifyOtp() {
        Log.i(TAG, "verifyOtp: ");

        final String mobile = mobile1.getText().toString().trim();
        final String otp = otptext.getText().toString().trim();

        String URLOTPVIRFY = "http://mybasuons.com/api/User/verify_otp" + "?mobile=" + mobile + "&otp=" + otp;


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.show();

        StringRequest requestone = new StringRequest(Request.Method.POST,URLOTPVIRFY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            Log.i("login", "verifyOtp: " + response);
                            if (object.getString("msg").equals("success")) {
                                if (object.getString("status").equals("1")) {
                                    messageopt.setText("Your Otp Verified Successful");
                                    Toast.makeText(getApplicationContext(), "Your Otp Verified Successful", Toast.LENGTH_SHORT);
                                    bankpage();
                                }
                            } else if (object.getString("msg").equals("fails")) {
                                if (object.getString("status").equals("1")) {
                                    messageopt.setText("Wrong OTP");

                                    Toast.makeText(getApplicationContext(), "Wrong OTP", Toast.LENGTH_SHORT);
                                }
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.i(TAG, "verifyOtp E: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mobile);
                params.put("otp", otp);

                Log.i(TAG, "getParams: " + params);
                return params;
            }
        };
        RequestQueue object = Volley.newRequestQueue(this);
        object.add(requestone);
    }

    private void bankpage() {
        Toast.makeText(getApplicationContext(),"otp submitted succesfully", Toast.LENGTH_LONG).show();

        Intent bank = new Intent(MobileOTP.this,BankDetails.class);
        bank.putExtra("fullname",fullname);
        bank.putExtra("email",email);
        bank.putExtra("type",type);
        bank.putExtra("password",password);
        bank.putExtra("paddress",paddress);
        bank.putExtra("taddress",taddress);
        bank.putExtra("pincode",pincode);
        bank.putExtra("city",city);
        bank.putExtra("state",state);
        bank.putExtra("country",country);
        bank.putExtra("reference",reference);
        bank.putExtra("whatspp",whatspp);
        bank.putExtra("gender",gender);
        bank.putExtra("mobile",getmobile);
        startActivity(bank);

    }

    private void phoneLogin() {

        final String mobile = mobile1.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, URLMOBILE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{
                    Log.i(TAG, "res"+mobile);
                    JSONObject object = new JSONObject(response);
                    String s = object.getString("message");
                    if(s.equals("OTP SentSuccessfully.")){
                        String s1 = object.getString("status");
                        if (s1.equals("1")){
                            Toast.makeText(getApplicationContext(),"Your mobile number  register",Toast.LENGTH_SHORT).show();
                            messageopt.setText("Your mobile number  register");
                        }else if (s.equals("User not Registered with this Mobile no.")) {
                            Toast.makeText(getApplicationContext(),"Your mobile number not register",Toast.LENGTH_SHORT).show();

                            messageopt.setText("Your mobile number not register");
                        } else if (s.equals("Mobile no Not Valid")) {
                            Toast.makeText(getApplicationContext(),"Your mobile number not Valid",Toast.LENGTH_SHORT).show();

                            messageopt.setText("Your mobile number not Valid");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.i(TAG, "phoneLogin: E " + error.getMessage());

            }
        }){
            protected Map<String, String>getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mobile);
                return params;
            }
        };
        RequestQueue object = Volley.newRequestQueue(this);
        object.add(request);
    }

    private void findviewbyid() {
        mobile1 = (EditText)findViewById(R.id.mobile);
        otptext = (EditText)findViewById(R.id.otptext);
        otp = (Button)findViewById(R.id.getotp);
        submit = (Button)findViewById(R.id.submitotp);
        messageopt = findViewById(R.id.fed);
        tv_coundown = (TextView) findViewById(R.id.tv_coundown);
        text1 =findViewById(R.id.text1);


    }
}
