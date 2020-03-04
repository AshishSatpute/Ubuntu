package com.xlix.basuons;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xlix.basuons.Adapter.Draw_Adapter;
import com.xlix.basuons.Model.CatbyMod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import URLhelper.BaseHelper;


public class singledraw extends Fragment {
    private RecyclerView rv_item;
    View view;
    private static String TAG = MainActivity.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Draw_Adapter adapter;
    private List<CatbyMod> list;


    public singledraw() {
        // Required empty public constructor
    }

    public static singlescreen newInstance(String param1, String param2) {
        singlescreen fragment = new singlescreen();
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
            mParam2 = getArguments().getString("module_id");
            Log.i(TAG, "module_id" + mParam2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_singledraw, container, false);

        rv_item = view.findViewById(R.id.rv_home);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        rv_item.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_item.setItemAnimator(new DefaultItemAnimator());
        rv_item.setNestedScrollingEnabled(true);




        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(5 * 1000);

                    // After 5 seconds redirect to another intent
                    getmodulerequest();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        background.start();


        getmodulerequest();
        return view;
    }



    private void getmodulerequest() {
        StringRequest request = new StringRequest(Request.Method.POST, BaseHelper.getmodule, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "datad" + response);
                JSONObject obj = null;
                try {
                    obj = new JSONObject(response);
                    boolean status = obj.getBoolean("status");
                    if (status == true) {
                        list = new ArrayList<>();
                        obj = new JSONObject(response);
                        JSONArray array = obj.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            Log.i(TAG, "obj" + array);
                            JSONObject ob = array.getJSONObject(i);
                            CatbyMod ld = new CatbyMod(
                                    ob.getString("cat_title"),
                                    ob.getString("open_time"),
                                    ob.getString("close_time"),
                                    ob.getString("cat_id"),
                                    ob.getString("openactive"),
                                    ob.getString("closeactive"));
                            list.add(ld);
                            Log.i(TAG, "list" + ld);
                        }
                        adapter = new Draw_Adapter(list, mParam2);
                        rv_item.setAdapter(adapter);
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
                params.put("module_id", mParam2);
                Log.d("TAG", "Error = " + params.toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);

    }

}

