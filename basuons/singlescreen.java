package com.xlix.basuons;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.xlix.basuons.Adapter.AnkAdapter;
import com.xlix.basuons.Model.AnkModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import URLhelper.BaseHelper;
import URLhelper.SharedPref;


public class singlescreen extends Fragment {

    Fragment fragment = null;
    ProgressDialog progressDialog;
    RecyclerView recycler_view;
    AnkAdapter ankAdapter;
    ArrayList<AnkModel> ankModels1 = new ArrayList<>();
    Button send;
    JSONObject object;


    public singlescreen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_singlescreen, container, false);
        final String getArgument = getArguments().getString("bid");
        final String ank = getArguments().getString("ank");
        final String cat_id = getArguments().getString("cat_id");
        recycler_view = view.findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recycler_view.setLayoutManager(gridLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setNestedScrollingEnabled(true);

        send = view.findViewById(R.id.send);

        setData(getArgument, ank);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                object = new JSONObject();
                JSONArray array = new JSONArray();
                for (int j = 0; j < ankModels1.size(); j++) {
                    JSONObject obj = new JSONObject();
                    try {
                        if (ankModels1.get(j).getEt_ank_qty().isEmpty()) {
                            obj.put("no_of_units", "0");
                        } else {
                            obj.put("no_of_units", ankModels1.get(j).getEt_ank_qty());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    array.put(obj);

                }


                try {
                    object.put("module_id", ank);
                    object.put("category_id", cat_id);
                    object.put("user_id", SharedPref.getString(getActivity(), "user_id"));
                    object.put("booking_type", getArgument);
                    object.put("data", array);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.i("objectobject", "onClick: " + object);


                RequestQueue queue = Volley.newRequestQueue(getActivity());
                JsonObjectRequest jobReq = new JsonObjectRequest(Request.Method.POST, BaseHelper.book, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                progressDialog.dismiss();
                                fragment = new DrawBooking();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.fragment, fragment);
                                transaction.commit();

                                Log.i("FTFYTFYTFYTF", "onResponse: " + jsonObject);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        });

                queue.add(jobReq);

                //Toast.makeText(getContext(),"Draw Booking Succesfull", Toast.LENGTH_SHORT).show();

            }



        });


        return view;
    }

    private void setData(String getArgument, String ank) {
        Log.i("TAG", "setData: " + getArgument + ", " + ank);

        if (ank.equals("1")) {
            for (int i = 0; i < 10; i++) {
                AnkModel ankModel = new AnkModel("", i);
                ankModels1.add(ankModel);
            }
            ankAdapter = new AnkAdapter(getContext(), ankModels1);
            recycler_view.setAdapter(ankAdapter);
        } else if (ank.equals("2")) {
            for (int i = 10; i < 100; i++) {
                AnkModel ankModel = new AnkModel("", i);
                ankModels1.add(ankModel);
            }
            ankAdapter = new AnkAdapter(getContext(), ankModels1);
            recycler_view.setAdapter(ankAdapter);
        }

    }

}
