package com.example.noteme.Adapter;

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

import com.example.noteme.DataModel.AddDataModel;
import com.example.noteme.R;

import java.util.List;

/**
 * Created by ashish on 9/11/17.
 */

public class ArrayListAdapter extends ArrayAdapter<AddDataModel> {

    private static final String TAG = ArrayListAdapter.class.getSimpleName();
    private Activity context;
    private List<AddDataModel> artistsList;

    public ArrayListAdapter(Context context, List<AddDataModel> artistsList) {
        super(context, R.layout.list_layout_1, artistsList);
        Log.e(TAG, "ArtistListAdapter constructor call");
        this.context = (Activity) context;
        this.artistsList = artistsList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.e(TAG, "getView() call");
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View listViewItem = layoutInflater.inflate(R.layout.list_layout_1, null, true);

        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc);

        AddDataModel addDataModel = artistsList.get(position);

        textViewTitle.setText(addDataModel.getTitle());
        textViewDesc.setText(addDataModel.getDesc());

        return listViewItem;
    }
}
