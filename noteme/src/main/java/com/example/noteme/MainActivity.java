package com.example.noteme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.noteme.Adapter.ArrayListAdapter;
import com.example.noteme.DataModel.AddDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DatabaseReference databaseReference;
    private static final String TAG = MainActivity.class.getSimpleName();
    Context context;
    List<AddDataModel> addDataModelslist;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inti();
        listcall();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(context, AddNote.class);
                startActivity(intent);
            }
        });
    }

    private void listcall() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                addDataModelslist.clear();
                for (DataSnapshot addDataSnapshot : dataSnapshot.getChildren()) {

                    AddDataModel addDataModel = addDataSnapshot.getValue(AddDataModel.class);
                    addDataModelslist.add(addDataModel);
                }

                ArrayListAdapter arrayListAdapter = new ArrayListAdapter(context,addDataModelslist);

                listView.setAdapter(arrayListAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void inti() {

        listView = findViewById(R.id.listView);

        addDataModelslist = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("addDataModel");
    }

}