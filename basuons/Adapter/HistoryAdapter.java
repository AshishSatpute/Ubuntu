package com.xlix.basuons.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xlix.basuons.Model.WinModel;
import com.xlix.basuons.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    private List<WinModel> modelList;
    private Context context;
    String param;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView module_title, cat_title,book_id,tamt,tunit,Book_data,Book_date,Book_type,result;
        public MyViewHolder(@NonNull View view) {
            super(view);
            module_title = view.findViewById(R.id.module_title);
            cat_title = view.findViewById(R.id.cat_title);
            book_id = view.findViewById(R.id.book_id);
            tamt = view.findViewById(R.id.tamt);
            tunit = view.findViewById(R.id.tunit);
            Book_data = view.findViewById(R.id.Book_date);
            Book_date = view.findViewById(R.id.Book_data);
            Book_type = view.findViewById(R.id.Book_type);
            result = view.findViewById(R.id.result);
        }
    }

public HistoryAdapter(List<WinModel> modelList){
        this.modelList = modelList;

}


    @NonNull
    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_list_item,parent,false);
        context = parent.getContext();
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int rowcount = holder.getPosition();
        if ( rowcount == 0 ){
            holder.module_title.setText("Module Title");
            holder.cat_title.setText("Cat Title");
            holder.book_id.setText("Booking ID");
            holder.tamt.setText("Total Amount");
            holder.tunit.setText("Total Unit");
            holder.Book_data.setText("Booking Data");
            holder.Book_date.setText("Booking Date");
            holder.Book_type.setText("Book Type");
            holder.result.setText("Result");

        }else {
            WinModel mList = modelList.get(rowcount - 1);
            holder.module_title.setText(mList.getModuleTitle());
            holder.cat_title.setText(mList.getCatTitle());
            holder.book_id.setText(mList.getBookingId());
            holder.tamt.setText(mList.getTotalAmt());
            holder.tunit.setText(mList.getTotalUnit());
            holder.Book_data.setText(mList.getBookingData());
            holder.Book_date.setText(mList.getResultDate());
            holder.Book_type.setText(mList.getBookingType());
            holder.result.setText(mList.getResult());
        }
    }



    @Override
    public int getItemCount() {
        return modelList.size()+1;
    }
}
