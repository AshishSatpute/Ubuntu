package com.xlix.basuons;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xlix.basuons.Adapter.HistoryAdapter;
import com.xlix.basuons.Model.WinModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import URLhelper.BaseHelper;
import URLhelper.SharedPref;


public class WinHistory extends Fragment {

    View view;
    RecyclerView table;
    private HistoryAdapter winhis;
    private List<WinModel> list;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public WinHistory() {
        // Required empty public constructor
    }

    public static WinHistory newInstance(String param1, String param2) {
        WinHistory fragment = new WinHistory();
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
        view =  inflater.inflate(R.layout.fragment_win_history, container, false);
        table =view.findViewById(R.id.recyclerViewMovieList);
        table.setLayoutManager(new LinearLayoutManager(getActivity()));

        gethistory();




        return view;
    }

    private void gethistory() {
        StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.winHistory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try{
                    obj = new JSONObject(response);
                    boolean status = obj.getBoolean("status");
                    Log.i("TAG", "status"+status);
                    if (status == true){
                        list = new ArrayList<>();
                        Log.i("TAG", "gfhgfhgfhfhgf" + response);
                    obj = new JSONObject(response);
                        JSONArray array = obj.getJSONArray("data");
                        Log.i("TAG", "data"+array);
                        for (int i= 0; i<array.length(); i++){
                        JSONObject ob  = array.getJSONObject(i);
                        WinModel ld = new WinModel(
                                ob.getString("module_title"),
                                ob.getString("cat_title"),
                                ob.getString("booking_id"),
                                ob.getString("total_amt"),
                                ob.getString("total_unit"),
                                ob.getString("booking_data"),
                                ob.getString("result_date"),
                                ob.getString("booking_type"),
                                ob.getString("result"));

                        list.add(ld);
                            Log.i("TAG", "ld" + ld);
                            Log.i("TAG", "fhgfhgyfg"+list.size());

                        }
                        Log.i("TAG", "size"+list.size());
                        winhis = new HistoryAdapter(list);
                        table.setAdapter(winhis);
                    }

                } catch (JSONException ex) {
                    ex.printStackTrace();
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
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);


}}
