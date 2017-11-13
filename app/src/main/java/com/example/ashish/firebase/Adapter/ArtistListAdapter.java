package com.example.ashish.firebase.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ashish.firebase.DataModel.Artists;
import com.example.ashish.firebase.R;

import java.util.List;

/**
 * Created by ashish on 6/11/17.
 */

public class ArtistListAdapter extends ArrayAdapter<Artists> {

    private static final String TAG = ArtistListAdapter.class.getSimpleName();
    private Activity context;
    private List<Artists> artistsList;

    public ArtistListAdapter(Context context, List<Artists> artistsList) {
        super(context, R.layout.list_layout, artistsList);
        Log.e(TAG, "ArtistListAdapter constructor call");
        this.context = (Activity) context;
        this.artistsList = artistsList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.e(TAG, "getView() call");
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View listViewItem = layoutInflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenres = (TextView) listViewItem.findViewById(R.id.textViewGenres);

        Artists artists = artistsList.get(position);

        textViewName.setText(artists.getArtistName());
        textViewGenres.setText(artists.getArtistGenres());

        return listViewItem;
    }
}
