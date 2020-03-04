package com.xlix.basuons.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xlix.basuons.Model.CatbyMod;
import com.xlix.basuons.R;
import com.xlix.basuons.singlescreen;

import java.util.List;

public class Draw_Adapter extends RecyclerView.Adapter<Draw_Adapter.MyViewHolder> {
    private List<CatbyMod> modelList;
    private Context context;
    String param;


    public Draw_Adapter(List<CatbyMod> modelList, String param) {
        this.modelList = modelList;
        this.param = param;
    }

    @NonNull
    @Override
    public Draw_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawlist, parent, false);
        context = parent.getContext();
        return new Draw_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Draw_Adapter.MyViewHolder holder, int position) {
        final CatbyMod mList = modelList.get(position);
        holder.title.setText(mList.getCatTitle());

        if (!(mList.getOpenactive().equals("1"))) {
            holder.open.setVisibility(View.GONE);
            holder.opentime.setText(mList.getOpenTime()+"\n Booking is closed");
            holder.closetime.setText(mList.getCloseTime()+"\n Booking is closed");

        } else {
            holder.open.setVisibility(View.VISIBLE);
            holder.opentime.setText(mList.getOpenTime());
            holder.closetime.setText(mList.getCloseTime());
        }
        if (!(mList.getCloseactive().equals("1"))) {
            holder.close.setVisibility(View.GONE);
            holder.opentime.setText(mList.getOpenTime()+"\n Booking is closed");
            holder.closetime.setText(mList.getCloseTime()+"\n Booking is closed");

        } else {
            holder.close.setVisibility(View.VISIBLE);
            holder.opentime.setText(mList.getOpenTime());
            holder.closetime.setText(mList.getCloseTime());
        }

        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "opentime: ");
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                singlescreen fragment = new singlescreen();
                Bundle args = new Bundle();
                args.putString("bid", "open");
                args.putString("ank", param);
                args.putString("cat_id", mList.getCatId());
                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).addToBackStack(null).commit();
            }
        });

        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "closetime: " + mList.getCatId());
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                singlescreen fragment = new singlescreen();
                Bundle args = new Bundle();
                args.putString("bid", "close");
                args.putString("ank", param);
                args.putString("cat_id", mList.getCatId());
                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, opentime, closetime;
        public Button open, close;
        LinearLayout lopen, lclose;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textView13);
            opentime = (TextView) view.findViewById(R.id.textView2);
            closetime = (TextView) view.findViewById(R.id.textView4);
            open = (Button) view.findViewById(R.id.button1);
            close = (Button) view.findViewById(R.id.button2);
            lopen = (LinearLayout) view.findViewById(R.id.lopen);
            lclose = (LinearLayout) view.findViewById(R.id.lclose);
        }
    }


}
