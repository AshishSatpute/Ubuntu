package com.xlix.basuons.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xlix.basuons.Model.ResultModel;
import com.xlix.basuons.R;

import java.util.List;

public class Result_Adapter extends RecyclerView.Adapter<Result_Adapter.MyViewHolder> {
private List<ResultModel> modelList;

private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,booking_status,draw_result;
        public LinearLayout btn;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.textView15);
            booking_status = (TextView)view.findViewById(R.id.textView14);
            draw_result = (TextView)view.findViewById(R.id.textView16);
            btn = (LinearLayout) view.findViewById(R.id.btn);
        }
    }
 public Result_Adapter(List<ResultModel>modelList){this.modelList = modelList;}


    @NonNull
    @Override
    public Result_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result, parent, false);
        context = parent.getContext();

        return new Result_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Result_Adapter.MyViewHolder holder, int position) {
            ResultModel mList = modelList.get(position);
            holder.title.setText(mList.getCatTitle());
            holder.booking_status.setText(mList.getBookingStatus());
            holder.draw_result.setText(mList.getDrawResult());
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //dialog box code here
                }
            });
            String a = mList.getBookingData();
        Log.d("TAG","Open = "+a);
            String[] aa = a.split("/");
            String[] a1 = aa[0].split(",");
            String[] a2 = aa[1].split(",");
        Log.d("TAG","Open = "+a1+ " a2 = "+a2);

            String dr_result = mList.getDrawResult();
            String[] r = dr_result.split(",");
            int open = Integer.parseInt(r[0]);
            int close = Integer.parseInt(r[1]);

        Log.d("TAG","Open = "+a1[open]+" close = "+a2[close]);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


}
