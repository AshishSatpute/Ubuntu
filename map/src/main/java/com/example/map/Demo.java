package com.example.map;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by ashish on 30/11/17.
 */

public class Demo extends AppCompatActivity {

    private GoogleMap mMap;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText = (EditText)findViewById(R.id.editText);
                String search =  editText.getText().toString();

                Geocoder geocoder = new Geocoder(Demo.this,Locale.getDefault());

                Log.e("@Ashish", "geocoder obj created");

                List<Address> addressList = null;
                try {
                    addressList = geocoder.getFromLocationName(search, 1);
                    Log.e("@Ashish", "getFromLocationName");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                    Address address = addressList.get(0);
                    Log.e("@Ashish", "addresslist");
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("from geocoder"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            }
        });

    }
}
