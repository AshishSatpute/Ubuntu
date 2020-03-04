package com.xlix.basuons;

import android.os.Bundle;
import android.util.Log;
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


public class UnitSell extends Fragment {
    EditText receiverid, amounrt;
    Button submit;
    String amt = "0", id = "0";
    View view;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public UnitSell() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UnitSell.
     */
    // TODO: Rename and change types and number of parameters
    public static UnitSell newInstance(String param1, String param2) {
        UnitSell fragment = new UnitSell();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_unit_sell, container, false);
            receiverid = view.findViewById(R.id.reciever_id);
             amounrt = view.findViewById(R.id.winning_amount);
             submit = view.findViewById(R.id.unitbtn);
             submit.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     amt = amounrt.getText().toString().trim();
                     id = receiverid.getText().toString().trim();
                     transferbalance();
                 }
             });

        return view;
    }

    private void transferbalance() {
        StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.transferbalance, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try{
                    obj = new JSONObject(response);
                    boolean status = obj.getBoolean("status");
                    if (status == true){
                        String data = obj.getString("msg");
                        Toast.makeText(getContext(),data,Toast.LENGTH_SHORT).show();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put("sender_id", SharedPref.getString(getActivity(),"user_id"));
                params.put("reciever_id", id);
                params.put("winning_amount", amt);
                params.put("trxId", "transaction_id");
                Log.d("TAG", "Error = " + params.toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);


    }


}
