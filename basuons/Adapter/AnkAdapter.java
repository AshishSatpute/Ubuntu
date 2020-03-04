package com.xlix.basuons.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xlix.basuons.Model.AnkModel;
import com.xlix.basuons.R;

import java.util.ArrayList;

public class AnkAdapter extends RecyclerView.Adapter<AnkAdapter.Holder> {

    Context context;
    ArrayList<AnkModel> ankModels = new ArrayList<>();
    ArrayList<AnkModel> ankModels2 = new ArrayList<>();
    int pos;

    public AnkAdapter(Context context, ArrayList<AnkModel> ankModels) {
        this.context = context;
        this.ankModels = ankModels;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ank_design, parent, false);
        context = parent.getContext();
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setIsRecyclable(false);
        final AnkModel model = ankModels.get(position);
        Log.i("TAG", "onBindViewHolder: " + position);
        holder.tv_ank_num.setText(String.valueOf(String.valueOf(model.getTv_ank_num())));
        holder.et_ank_qty.setText(String.valueOf(model.getEt_ank_qty()));


        holder.et_ank_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                model.setEt_ank_qty(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return ankModels.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_ank_num;
        EditText et_ank_qty;
        View view;

        public Holder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tv_ank_num = view.findViewById(R.id.tv_ank_num);
            et_ank_qty = view.findViewById(R.id.et_ank_qty);
        }

    }


}
