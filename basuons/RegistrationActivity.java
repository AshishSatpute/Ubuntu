package com.xlix.basuons;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    private static String TAG = RegistrationActivity.class.getSimpleName();
    EditText fullname,email,password,taddress,paddress,pincode,city,country ,state, reference,whatspp;
    Button submit;
    RadioGroup gender;
    String type="";
    String gender2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //get type format code here
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");
        Log.i(TAG, "type1 "+type);
        findviewbyid();
        onclick();
    }

    private void onclick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedata();

            }
        });


        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_male:
                      gender2="male";
                      break;
                    case R.id.radio_female:
                        gender2 = "female";
                        break;

                }
            }
        });
    }

    private void sharedata() {

                String getfullname = fullname.getText().toString();
                String getemail = email.getText().toString();
                String gettype = type;
                String getpassword = password.getText().toString();
                String getpaddress = paddress.getText().toString();
                String gettaddress = taddress.getText().toString();
                String getpincode = pincode.getText().toString();
                String getcity = city.getText().toString();
                String getstate = state.getText().toString();
                String getcountry = country.getText().toString();
                String getreference = reference.getText().toString();
                String getwhatspp = whatspp.getText().toString();

                boolean cancel = false;
                View focusView = null;

                if (TextUtils.isEmpty(getfullname)) {
                    fullname.setTextColor(getResources().getColor(R.color.red_900));
                    focusView = fullname;
                    cancel = true;
                }

                if (TextUtils.isEmpty(getemail)) {
                    focusView = email;
                    cancel = true;
                } else if (!isEmailValid(getemail)) {
                    email.setText(getResources().getString(R.string.invalide_email_address));
                    email.setTextColor(getResources().getColor(R.color.red_900));
                    focusView = email;
                    cancel = true;
                }

                if (TextUtils.isEmpty(getpassword)) {
                    password.setTextColor(getResources().getColor(R.color.red_900));
                    focusView = password;
                    cancel = true;
                } else if (!isPasswordValid(getpassword)) {
                    password.setText(getResources().getString(R.string.password_too_short));
                    password.setTextColor(getResources().getColor(R.color.red_900));
                    focusView = password;
                    cancel = true;
                }

                if (TextUtils.isEmpty(getpincode)) {
                    pincode.setTextColor(getResources().getColor(R.color.red_900));
                    focusView = pincode;
                    cancel = true;
                }
                if (TextUtils.isEmpty(getcity)) {
                    city.setTextColor(getResources().getColor(R.color.red_900));
                    focusView = city;
                    cancel = true;
                }
                if (TextUtils.isEmpty(getstate)) {
                    state.setTextColor(getResources().getColor(R.color.red_900));
                    focusView = state;
                    cancel = true;
                }
                if (TextUtils.isEmpty(getcountry)) {
                    country.setTextColor(getResources().getColor(R.color.red_900));
                    focusView = country;
                    cancel = true;
                }

                if (TextUtils.isEmpty(getwhatspp)) {
                    whatspp.setTextColor(getResources().getColor(R.color.red_900));
                    focusView = whatspp;
                    cancel = true;
                }

               if (cancel) {
                   if (focusView != null)
                        focusView.requestFocus();
                } else {



        Intent share = new Intent(RegistrationActivity.this,MobileOTP.class);
                    share.putExtra("fullname",getfullname);
                    share.putExtra("email",getemail);
                    share.putExtra("type",type);
                    share.putExtra("password",getpassword);
                    share.putExtra("paddress",getpaddress);
                    share.putExtra("taddress",gettaddress);
                    share.putExtra("pincode",getpincode);
                    share.putExtra("city",getcity);
                    share.putExtra("state",getstate);
                    share.putExtra("country",getcountry);
                    share.putExtra("reference",getreference);
                    share.putExtra("whatspp",getwhatspp);
                    share.putExtra("gender",gender2);
        startActivity(share);
                 }


    }


    private boolean isEmailValid(String getemail) {
        return getemail.contains("@");
    }

    private boolean isPasswordValid(String getpassword) {
        return getpassword.length() > 4;
    }

    private void findviewbyid() {
        fullname = (EditText)findViewById(R.id.fullname);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        taddress = (EditText)findViewById(R.id.address2);
        paddress = (EditText)findViewById(R.id.address1);
        pincode = (EditText)findViewById(R.id.pincode);
        city = (EditText)findViewById(R.id.city);
        state = (EditText)findViewById(R.id.state);
        country = (EditText)findViewById(R.id.country);
        reference = (EditText)findViewById(R.id.refered);
        submit = (Button) findViewById(R.id.email_sign_in_button);
        whatspp = (EditText)findViewById(R.id.whatsapp);
        gender = (RadioGroup)findViewById(R.id.gender);


    }
}
