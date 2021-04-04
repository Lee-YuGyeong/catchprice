package com.market.catchprice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.market.catchprice.Contract.AutionAdapterContract;
import com.market.catchprice.Model.AutionResponse;
import com.market.catchprice.R;

import java.util.ArrayList;
import java.util.List;

public class AutionAdapter extends RecyclerView.Adapter<AutionAdapter.MyViewHolder> implements AutionAdapterContract.View, AutionAdapterContract.Model {
    private Context context;
    private List<AutionResponse> list = new ArrayList<>();

    OnItemClickListener listener;

    public AutionAdapter(Context context) {
        this.context = context;
    }

    public static interface OnItemClickListener {
        public void OnItemClick(MyViewHolder holder, View view, int position);
    }

    @Override
    public AutionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.aution_item, parent, false);

        return new AutionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AutionAdapter.MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getTime());
        holder.text_upper.setText(list.get(position).getText_upper());
        holder.text_lower.setText(list.get(position).getText_lower());

        holder.setOnItemClickListener(listener);
    }

    public void addItem(AutionResponse item) {
        list.add(item);
    }

    public AutionResponse getItem(int position) {
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

        OnItemClickListener listener;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            time = (TextView) view.findViewById(R.id.time);
            text_upper = (TextView) view.findViewById(R.id.text_upper);
            text_lower = (TextView) view.findViewById(R.id.text_lower);

            // RecyclerView Item 클릭리스너 등록
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // OnItemClick 인터페이스 메서드 사용 (Presenter에게 이벤트 처리 위임)
                    if (listener != null) {
                        listener.OnItemClick(MyViewHolder.this, view, getAdapterPosition());
                    }
                }
            });
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }//클릭 이벤트


    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setData(List<AutionResponse> list) {
        this.list = list;
    }

    @Override
    public AutionResponse AutionResponse(int position) {
        return list.get(position);
    }


}
