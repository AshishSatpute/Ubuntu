package com.example.ashish.firebase.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.ashish.firebase.Adapter.ArtistListAdapter;
import com.example.ashish.firebase.DataModel.Artists;
import com.example.ashish.firebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String ARTIST_NAME = "artistName";
    public static final String ARTIST_ID = "artistId";

    private static final String TAG = MainActivity.class.getSimpleName();
    DatabaseReference databaseReferenceArtists;
    ListView listViewArtists;
    List<Artists> artistsList;
    Context context;
    EditText editTextName;
    Spinner spinnerGenres;
    Button buttonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buttonAdd();
    }

    private void init() {
        databaseReferenceArtists = FirebaseDatabase.getInstance().getReference("artists");
        editTextName = findViewById(R.id.etName);
        spinnerGenres = findViewById(R.id.spinnerGenres);
        buttonAdd = findViewById(R.id.buttonArtist);
        listViewArtists = findViewById(R.id.listViewArtists);
        artistsList = new ArrayList<>();
    }

    private void buttonAdd() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtists();
            }
        });

        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artists artists = artistsList.get(position);
                Intent intent = new Intent(getApplicationContext(), AddTrackActivity.class);
                intent.putExtra(ARTIST_ID, artists.getArtistId());
                intent.putExtra(ARTIST_NAME, artists.getArtistName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReferenceArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistsList.clear();
                for (DataSnapshot ArtistsSnapshot : dataSnapshot.getChildren()) {
                    Artists artists = ArtistsSnapshot.getValue(Artists.class);
                    artistsList.add(artists);
                }

                ArtistListAdapter artistListAdapter = new ArtistListAdapter(MainActivity.this, artistsList);
                listViewArtists.setAdapter(artistListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void addArtists() {
        Log.e(TAG, "addArtists()");
        String name = editTextName.getText().toString().trim();
        String genres = spinnerGenres.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)) {
            String id = databaseReferenceArtists.push().getKey();

            Artists artists = new Artists(id, name, genres);
            databaseReferenceArtists.child(id).setValue(artists);
            Log.e(TAG, "data added");
        } else {
            Log.e(TAG, "else fails");
        }
    }
}