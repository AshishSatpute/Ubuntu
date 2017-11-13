package com.example.ashish.firebase.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashish.firebase.DataModel.Track;
import com.example.ashish.firebase.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrackActivity extends AppCompatActivity {

    TextView textViewArtistName;
    EditText editTextTrackName;
    SeekBar seekBarRating;
    ListView listViewTracks;
    Button buttonAddTracks;

    DatabaseReference databaseReferenceForTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        textViewArtistName = findViewById(R.id.textViewArtitsName);
        editTextTrackName = findViewById(R.id.editTextTrackName);
        seekBarRating = findViewById(R.id.SeekBarRating);
        buttonAddTracks = findViewById(R.id.buttonAddTracks);

        listViewTracks = findViewById(R.id.listViewTracks);

        Intent intent = getIntent();

        String id = intent.getStringExtra(MainActivity.ARTIST_ID);
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);

        textViewArtistName.setText(name);

        databaseReferenceForTrack = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        buttonAddTracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrack();
            }
        });
    }

    private void saveTrack() {
        String trackName = editTextTrackName.getText().toString().trim();
        int rating = seekBarRating.getProgress();

        if (!TextUtils.isEmpty(trackName)) {

            String id = databaseReferenceForTrack.push().getKey();

            Track track = new Track(id, trackName, rating);

            databaseReferenceForTrack.child(id).setValue(track);

            Toast.makeText(this, "save tracks success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "fails to save tracks", Toast.LENGTH_SHORT).show();
        }
    }
}