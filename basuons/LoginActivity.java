package com.xlix.basuons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView daily,montly,merchant;
    ImageView piechart;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findviewbyid();
        animation();
        onclick();

    }

    private void onclick() {
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"daily button clicked",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(LoginActivity.this,DailyLogin.class);
                startActivity(intent1);
            }
        });
        montly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"montly button clicked",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(LoginActivity.this,MonthlyDraw.class);
                startActivity(intent2);
            }
        });
        merchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"merchant button clicked",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(LoginActivity.this,MerchantLogin.class);
                startActivity(intent3);
            }
        });
    }

    private void animation() {

        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_rotate);
        piechart.startAnimation(anim);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              //  daily.setVisibility(View.VISIBLE);
              //  montly.setVisibility(View.VISIBLE);
              //  merchant.setVisibility(View.VISIBLE);
            }
        }, 6500);

    }

    private void findviewbyid() {
        daily = (TextView)findViewById(R.id.dailydraw);
        montly = (TextView)findViewById(R.id.montlydraw);
        merchant = (TextView)findViewById(R.id.merchant);
        piechart = (ImageView)findViewById(R.id.piecircle);

    }


}
