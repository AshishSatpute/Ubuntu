package com.xlix.basuons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import URLhelper.BaseHelper;

public class MonthlyDraw extends AppCompatActivity {
    EditText monthly_username, monthly_password;
    TextView monthly_forgot, monthly_register;
    Button monthly_signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_draw);
        findviewbyid();
        onclick();
    }

    private void onclick() {
        monthly_register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent1 = new Intent(MonthlyDraw.this,RegistrationActivity.class);
            intent1.putExtra("type","monthly");
            startActivity(intent1);
            //type format code here
        }
    });

        monthly_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MonthlyDraw.this,PasswordActivity.class);
                startActivity(intent3);
                //type format code here
            }
        });

        monthly_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptlogin();
            }
        });
    }

    private void attemptlogin() {

        String getpassword = monthly_password.getText().toString();
        String getemail = monthly_username.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(getpassword)) {
            monthly_password.setTextColor(getResources().getColor(R.color.black));
            focusView = monthly_password;
            cancel = true;
        } else if (!isPasswordValid(getpassword)) {
            monthly_password.setText(getResources().getString(R.string.password_too_short));
            monthly_password.setTextColor(getResources().getColor(R.color.black));
            focusView = monthly_password;
            cancel = true;
        }

        if (TextUtils.isEmpty(getemail)) {
            monthly_username.setTextColor(getResources().getColor(R.color.black));
            focusView = monthly_username;
            cancel = true;
        } else if (!isEmailValid(getemail)) {
            monthly_username.setText(getResources().getString(R.string.invalide_email_address));
            monthly_username.setTextColor(getResources().getColor(R.color.black));
            focusView = monthly_username;
            cancel = true;
        }

        if (cancel) {
            if (focusView != null)
                focusView.requestFocus();
        } else {
            makeLoginRequest(getemail, getpassword);
        }

    }

    private void makeLoginRequest(final String monthly_username,final String monthly_password) {
        //json code

        StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG","Response = "+response);
                JSONObject obj = null;
                try{
                    obj = new JSONObject(response);
                    boolean status = obj.getBoolean("response");
                    if (status == true){

                        JSONArray jsonArray = obj.getJSONArray("data");

                        JSONObject object = jsonArray.getJSONObject(0);

                        String id = object.getString("user_id");
                        String full_name = object.getString("full_name");
                        String mobile = object.getString("mobile");
                        String email = object.getString("email");
                        String type = object.getString("type");
                        String status1 = object.getString("status");
                        String created_at = object.getString("created_at");
                        String updated_at = object.getString("updated_at");
                        String ifsc_code = object.getString("ifsc_code");
                        String acc_name = object.getString("acc_name");
                        String acc_no = object.getString("acc_no");
                        String acc_branchname = object.getString("acc_branchname");
                        String address_one = object.getString("address_one");
                        String address_two = object.getString("address_two");
                        String city = object.getString("city");
                        String state = object.getString("state");
                        String country = object.getString("country");
                        String password = object.getString("password");
                        String pincode = object.getString("pincode");
                        String wallet_amount = object.getString("wallet_amount");
                        String winning_amount = object.getString("winning_amount");
                        String comission_amount = object.getString("comission_amount");
                        String no_of_units = object.getString("no_of_units");
                        String referral_code = object.getString("referral_code");
                        String refered_code = object.getString("refered_code");
                        String kyc_status = object.getString("kyc_status");
                        String aadharno = object.getString("aadharno");
                        String panno = object.getString("panno");
                        String whats_app_no = object.getString("whats_app_no");
                        String gender = object.getString("gender");

                        SessionManagement sessionManagement = new SessionManagement(MonthlyDraw.this);
                        sessionManagement.createLoginSession(id, full_name, mobile, email, type, status1, created_at,
                                updated_at, ifsc_code, acc_name, acc_no, acc_branchname, address_one, address_two, city, state,
                                country, password, pincode, wallet_amount, winning_amount, comission_amount, no_of_units,
                                referral_code, refered_code, kyc_status, aadharno, panno, whats_app_no, gender);

                        Intent i = new Intent(MonthlyDraw.this,MainActivity.class);
                        startActivity(i);
                        finish();
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
                params.put("username", monthly_username);
                params.put("password", monthly_password);
                Log.d("TAG","Error = "+params.toString());
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);



    }

    private boolean isEmailValid(String getemail) {
        return getemail.contains("");
    }

    private boolean isPasswordValid(String getpassword) {
        return getpassword.length() > 4;
    }

    private void findviewbyid() {
        monthly_username = (EditText)findViewById(R.id.monthlyusername);
        monthly_password = (EditText)findViewById(R.id.monthly_edit_text_password);
        monthly_forgot = (TextView)findViewById(R.id.monthly_text_view_forgot_password);
        monthly_register = (TextView)findViewById(R.id.monthly_text_view_sign_up);
        monthly_signin = (Button)findViewById(R.id.monthly_button_sign_in);
    }
}
