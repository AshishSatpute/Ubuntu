package com.example.noteme;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteme.DataModel.AddDataModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNote extends AppCompatActivity {

    EditText editTextTitle;
    EditText editTextDesc;
    Button buttonSave;
    DatabaseReference databaseReference;

    private static final String TAG = AddNote.class.getCanonicalName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDesc = findViewById(R.id.editTextDesc);
        buttonSave = findViewById(R.id.buttonSave);

        databaseReference = FirebaseDatabase.getInstance().getReference("addDataModel");


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewNote();
            }
        });
    }


    private void AddNewNote() {

        Log.e(TAG, "addNewNote()");
        String title = editTextTitle.getText().toString().trim();
        String desc = editTextDesc.getText().toString();

        if (!TextUtils.isEmpty(title)) {

         String id = databaseReference.push().getKey();

            AddDataModel addDataModel = new AddDataModel(id,title,desc);
            
            databaseReference.child(id).setValue(addDataModel);

            Log.e(TAG, "data added" +id);

            Toast.makeText(this, "Added success", Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "else fails");

            Toast.makeText(this, "Added Fails", Toast.LENGTH_SHORT).show();
        }
    }
}
