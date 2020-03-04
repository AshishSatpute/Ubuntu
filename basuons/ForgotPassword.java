package com.xlix.basuons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {
    EditText mobile,opass,npass;
    Button submit;
    TextView tre1,tre2;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        findviewbyid();
        onclick();

    }

    private void onclick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update api code code
                forgotpass();
            }
        });
    }

    private void forgotpass() {
    }

    private void findviewbyid() {
        mobile = findViewById(R.id.mobilev);
        opass = findViewById(R.id.opass);
        npass = findViewById(R.id.cpass);
        submit = findViewById(R.id.submitfed);
        tre1 = findViewById(R.id.tr1);
        tre2 = findViewById(R.id.tr2);
    }
}
