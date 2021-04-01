package com.market.catchprice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.market.catchprice.Model.TodayAutionResponse;
import com.market.catchprice.R;

import java.util.ArrayList;
import java.util.List;

public class TodayAutionAdapter extends RecyclerView.Adapter<TodayAutionAdapter.MyViewHolder> {
    private Context context;
    private List<TodayAutionResponse> list = new ArrayList<>();

    public TodayAutionAdapter(Context context, List<TodayAutionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TodayAutionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_aution_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(TodayAutionAdapter.MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getTime());
        holder.text_upper.setText(list.get(position).getText_upper());
        holder.text_lower.setText(list.get(position).getText_lower());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView time;
        TextView text_upper;
        TextView text_lower;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            time = (TextView) itemView.findViewById(R.id.time);
            text_upper = (TextView) itemView.findViewById(R.id.text_upper);
            text_lower = (TextView) itemView.findViewById(R.id.text_lower);
        }
    }

}
