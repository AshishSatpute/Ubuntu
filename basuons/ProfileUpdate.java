package com.xlix.basuons;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

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


public class ProfileUpdate extends Fragment {
    EditText fullname, mobile, email, addone, addtwo, city, state, pincode, country, whatspp;
    Button submit;
    View view;
    Context context;

    public ProfileUpdate() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_profile_update, container, false);
        findbyid();
        onclick();
        return view;
    }

    private void onclick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemprequest();
                // updaterequest();
            }
        });
    }

    private void attemprequest() {
        String getfullname = fullname.getText().toString();
        String getmobile = mobile.getText().toString();
        String getemail = email.getText().toString();
        String getadd1 = addone.getText().toString();
        String getadd2 = addtwo.getText().toString();
        String getcity = city.getText().toString();
        String getstate = state.getText().toString();
        String getpincode = pincode.getText().toString();
        String getcountry = country.getText().toString();
        String getwhats = whatspp.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(getfullname)) {
            fullname.setTextColor(getResources().getColor(R.color.red_900));
            focusView = fullname;
            cancel = true;
        }
        if (TextUtils.isEmpty(getmobile)) {
            mobile.setTextColor(getResources().getColor(R.color.red_900));
            focusView = email;
            cancel = true;
        }
        if (TextUtils.isEmpty(getemail)) {
            email.setTextColor(getResources().getColor(R.color.red_900));
            focusView = email;
            cancel = true;
        }
        if (TextUtils.isEmpty(getadd1)) {
            addone.setTextColor(getResources().getColor(R.color.red_900));
            focusView = addone;
            cancel = true;
        }
        if (TextUtils.isEmpty(getadd2)) {
            addtwo.setTextColor(getResources().getColor(R.color.red_900));
            focusView = addtwo;
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
        if (TextUtils.isEmpty(getpincode)) {
            pincode.setTextColor(getResources().getColor(R.color.red_900));
            focusView = pincode;
            cancel = true;
        }
        if (TextUtils.isEmpty(getcountry)) {
            country.setTextColor(getResources().getColor(R.color.red_900));
            focusView = country;
            cancel = true;
        }
        if (TextUtils.isEmpty(getwhats)) {
            whatspp.setTextColor(getResources().getColor(R.color.red_900));
            focusView = whatspp;
            cancel = true;
        }
        if (cancel) {
            if (focusView != null)
                focusView.requestFocus();
        } else {
            //updateprofile
            updaterequest(getfullname, getmobile, getemail, getadd1, getadd2, getcity, getstate, getpincode, getcountry, getwhats);


        }
    }

    private void updaterequest(final String getfullname, final String getmobile, final String getemail, final String getadd1, final String getadd2, final String getcity, final String getstate, final String getpincode, final String getcountry, final String getwhats) {
        StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.update_profile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(response);
                    boolean status = obj.getBoolean("response");
                    if (status == true) {
                        String msg = obj.getString("msg");
                        Toast.makeText(getActivity(), "profile update successfully", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {

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
                params.put("full_name", getfullname);
                params.put("mobile", getmobile);
                params.put("email", getemail);
                params.put("address_one", getadd1);
                params.put("address_two", getadd2);
                params.put("city", getcity);
                params.put("state", getstate);
                params.put("pincode", getpincode);
                params.put("country", getcountry);
                params.put("whats_app_no", getwhats);
                params.put("user_id", SharedPref.getString(getActivity(),"user_id"));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }


    private void findbyid() {
        fullname = view.findViewById(R.id.fullname);
        mobile = view.findViewById(R.id.mobile);
        email = view.findViewById(R.id.email);
        addone = view.findViewById(R.id.address_one);
        addtwo = view.findViewById(R.id.address_two);
        city = view.findViewById(R.id.city);
        state = view.findViewById(R.id.state);
        pincode = view.findViewById(R.id.pincode);
        country = view.findViewById(R.id.country);
        whatspp = view.findViewById(R.id.whatsno);
        submit = view.findViewById(R.id.submitup);

    }


}
