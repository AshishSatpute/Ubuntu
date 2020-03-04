package com.xlix.basuons;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xlix.basuons.Adapter.Result_Adapter;
import com.xlix.basuons.Model.ResultModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import URLhelper.BaseHelper;


public class DrawBooking extends Fragment {
    private static String TAG = MainActivity.class.getSimpleName();

    private RecyclerView rv_draw;
    LinearLayout single, jodi,singlepanel, doublepanel,fullpanel,halfpanel,triplepanel;
    View view;
    Fragment fragment = null;
    private Result_Adapter result;
    private List<ResultModel> list;
    public DrawBooking() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_draw_booking, container, false);
        findbyid();
        onclick();

        rv_draw = view.findViewById(R.id.rv_draw1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        rv_draw.setLayoutManager(gridLayoutManager);
        rv_draw.setItemAnimator(new DefaultItemAnimator());
        rv_draw.setNestedScrollingEnabled(true);
        getliverequest();


        return view;
    }

    private void getliverequest() {
        StringRequest request = new StringRequest(Request.Method.GET, BaseHelper.getresult, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(response);
                    boolean status = obj.getBoolean("status");
                    Log.i(TAG, "status"+status);
                    if (status == true){
                        list  = new ArrayList<>();
                        obj = new JSONObject(response);
                        JSONArray array = obj.getJSONArray("data");
                        Log.i(TAG, "data"+array);
                        for (int i = 0; i<array.length(); i++){
                            JSONObject ob =array.getJSONObject(i);
                            ResultModel ld = new ResultModel(
                                    ob.getString("cat_title"),
                                    ob.getString("booking_status"),
                                    ob.getString("draw_result"),
                                    ob.getString("booking_data"));
                            list.add(ld);

                        }
                        result = new Result_Adapter(list);
                        rv_draw.setAdapter(result);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    private void onclick() {
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment  = new singledraw();
                Bundle args = new Bundle();
                args.putString("module_id","1");
                fragment.setArguments(args);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                // transaction.add(R.id.fragment,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        jodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new singledraw();
                Bundle args = new Bundle();
                args.putString("module_id","2");
                fragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                // transaction.add(R.id.fragment,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        singlepanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new singledraw();
                Bundle args = new Bundle();
                args.putString("module_id","3");
                fragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                // transaction.add(R.id.fragment,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        doublepanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new singledraw();
                Bundle args = new Bundle();
                args.putString("module_id","4");
                fragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                // transaction.add(R.id.fragment,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        fullpanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new singledraw();
                Bundle args = new Bundle();
                args.putString("module_id","5");
                fragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                // transaction.add(R.id.fragment,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        halfpanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new singledraw();
                Bundle args = new Bundle();
                args.putString("module_id","6");
                fragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                //  transaction.add(R.id.fragment,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        triplepanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new singledraw();
                Bundle args = new Bundle();
                args.putString("module_id","7");
                fragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                // transaction.add(R.id.fragment,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void findbyid() {
        rv_draw = view.findViewById(R.id.rv_draw1);
        single = view.findViewById(R.id.single);
        jodi = view.findViewById(R.id.jodi);
        singlepanel = view.findViewById(R.id.single_panel);
        doublepanel = view.findViewById(R.id.double_panel);
        fullpanel = view.findViewById(R.id.full_panel);
        halfpanel = view.findViewById(R.id.half_panel);
        triplepanel = view.findViewById(R.id.triple_panel);

    }


}
