package com.xlix.basuons;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdatebankDetailsFragment extends Fragment {

    public static final String TAG = "UpdatebankDetailsFragment";
    String id;

    EditText aadharno,
            panno,
            ifsc_code,
            acc_no,
            acc_branchname,
            acc_name;

    Button submit;


    public UpdatebankDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_updatebank_details, container, false);

        findbyid(view);

        id = SharedPref.getString(getContext(), "user_id");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBannkDetails();
            }
        });


        return view;
    }

    @SuppressLint("LongLogTag")
    private void updateBannkDetails() {

        validateData();

    }

    private void validateData() {


        String getaadharno = aadharno.getText().toString();
        String getpanno = panno.getText().toString();
        String getifsc_code = ifsc_code.getText().toString();
        String getacc_no = acc_no.getText().toString();
        String getacc_branchname = acc_branchname.getText().toString();
        String getacc_name = acc_name.getText().toString();


        if (TextUtils.isEmpty(getaadharno)) {
            Toast.makeText(getContext(), "aadharno Empty", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(getpanno)) {
            Toast.makeText(getContext(), "panno Empty", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(getifsc_code)) {
            Toast.makeText(getContext(), "ifsc_code Empty", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(getacc_no)) {
            Toast.makeText(getContext(), "acc_no Empty", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(getacc_branchname)) {
            Toast.makeText(getContext(), "acc_branchname Empty", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(getacc_name)) {
            Toast.makeText(getContext(), "acc_name Empty", Toast.LENGTH_SHORT).show();
        } else {
            //updateprofile
            //  updaterequest(getfullname, getmobile, getemail, getadd1, getadd2, getcity, getstate, getpincode, getcountry, getwhats);

            sendData(

                    getaadharno,
                    getpanno,
                    getifsc_code,
                    getacc_no,
                    getacc_branchname,
                    getacc_name

            );

        }


    }

    private void sendData(final String getaadharno, final String getpanno, final String getifsc_code, final String getacc_no, final String getacc_branchname, final String getacc_name) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST,
                BaseHelper.update_bank_details,
                new Response.Listener<String>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(String response) {
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            boolean status = obj.getBoolean("response");
                            String msg = obj.getString("msg");
                            Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onResponse: " + obj);

                            if (status == true) {
                                FragmentManager manager = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.fragment, new HomeFragment());
                                transaction.commit();
                            }
                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: " + error.getMessage());

            }
        }) {
            @SuppressLint("LongLogTag")
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("aadharno", getaadharno);
                params.put("panno", getpanno);
                params.put("ifsc_code", getifsc_code);
                params.put("acc_no", getacc_no);
                params.put("acc_branchname", getacc_branchname);
                params.put("acc_name", getacc_name);
                params.put("user_id", SharedPref.getString(getActivity(),"user_id"));
                return params;
            }
        };

        queue.add(request);
    }


    private void findbyid(View view) {
        aadharno = view.findViewById(R.id.aadharno);
        panno = view.findViewById(R.id.panno);
        ifsc_code = view.findViewById(R.id.ifsc_code);
        acc_no = view.findViewById(R.id.acc_no);
        acc_branchname = view.findViewById(R.id.acc_branchname);
        acc_name = view.findViewById(R.id.acc_name);
        submit = view.findViewById(R.id.submit);
    }

}
