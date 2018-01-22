package com.webber.demos.weight;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webber.demos.R;

import java.util.List;

/**
 * Created by picher on 2018/1/21.
 * Describe：
 */

public class RecycleViewTestAdapter extends RecyclerView.Adapter {

    private List<String> strings;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_LASTER = 1;

    public void setData(List<String> data) {
        this.strings = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_LASTER){
            return new LastViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty,parent,false));
        }
        return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_big_img,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NormalViewHolder){
            ((NormalViewHolder) holder).textView.setText(strings.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == strings.size() -1){
            return TYPE_LASTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return strings == null ? 0 : strings.size();
    }

    public class LastViewHolder extends RecyclerView.ViewHolder{

        public LastViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
