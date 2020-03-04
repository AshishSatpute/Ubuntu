package com.xlix.basuons;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import static com.xlix.basuons.MainActivity.TAG;


public class HomeFragment extends Fragment {
LinearLayout drawbooking, withdrawreq,unitsell,winhistory;
Fragment fragment = null;
    String Wallet_Ba = "0", Win_Amm = "0",Com = "0", Uni = "0";
TextView Wallet_Balance, Win_Ammount, Comission, Unit_Balance;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar mProgressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        drawbooking = view.findViewById(R.id.drawbooking);
        withdrawreq = view.findViewById(R.id.withdraw);
        unitsell = view.findViewById(R.id.unitsell);
        Wallet_Balance = view.findViewById(R.id.tet1);
        Win_Ammount = view.findViewById(R.id.tet2);
        Comission = view.findViewById(R.id.tet3);
        Unit_Balance = view.findViewById(R.id.tet4);
        winhistory = view.findViewById(R.id.winhistory);
        mSwipeRefreshLayout = view.findViewById(R.id.swif);

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(1 * 1000);

                    // After 5 seconds redirect to another intent
                    walletupdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        background.start();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                walletupdate();
                mSwipeRefreshLayout.setRefreshing(false);

                return;
            }
        });

        walletupdate();
        drawbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               fragment = new DrawBooking();
               FragmentTransaction transaction = getFragmentManager().beginTransaction();
               transaction.replace(R.id.fragment, fragment);
               transaction.addToBackStack(null);
               transaction.commit();
            }
        });

        withdrawreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intr = new Intent(getActivity(),Withdraw.class);
                startActivity(intr);
            }
        });
        unitsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new UnitSell();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        winhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new WinHistory();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

return view;
    }



    private void walletupdate() {
        StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.getwallet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try{
                    obj = new JSONObject(response);
                    Log.i(TAG, "onResponse: "+response);
                    boolean status = obj.getBoolean("status");

                    if (status == true){
                        JSONObject object = obj.getJSONObject("data");
                         Wallet_Ba = object.getString("wallet_amount");
                        Log.i(TAG, "Wallet_Ba"+Wallet_Ba);
                        Win_Amm = object.getString("winning_amount");
                        Com = object.getString("comission_amount");
                        Uni = object.getString("no_of_units");
                        Wallet_Balance.setText(Wallet_Ba);
                        Win_Ammount.setText(Win_Amm);
                        Comission.setText(Com);
                        Unit_Balance.setText(Uni);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put("user_id", SharedPref.getString(getActivity(),"user_id"));
                Log.d("TAG", "Error = " + params.toString());
                return params;
            }
    };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

}
}
