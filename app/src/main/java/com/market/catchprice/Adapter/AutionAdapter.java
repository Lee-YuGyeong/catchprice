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

public class AutionAdapter extends RecyclerView.Adapter<AutionAdapter.MyViewHolder> {
    private Context context;
    private List<TodayAutionResponse> list = new ArrayList<>();

    public AutionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public AutionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_aution_item, parent, false);
//        MyViewHolder myViewHolder = new MyViewHolder(view);
//        return myViewHolder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.today_aution_item, parent, false);

        return new AutionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AutionAdapter.MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getTime());
        holder.text_upper.setText(list.get(position).getText_upper());
        holder.text_lower.setText(list.get(position).getText_lower());
    }

    public void addItem(TodayAutionResponse item) {
        list.add(item);
    }

    public TodayAutionResponse getItem(int position) {
        return list.get(position);
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
