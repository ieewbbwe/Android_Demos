package com.webber.demos.weight;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private static final int TYPE_BIGIMG = 2;
    private OnItemClickListener onItemClickListener;

    public void setData(List<String> data) {
        this.strings = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* if(viewType == TYPE_LASTER){
            return new LastViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty,parent,false));
        }*/
       if(viewType == TYPE_BIGIMG){
            return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_big_img,parent,false));
       }
        return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal_img,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });

        if(holder instanceof NormalViewHolder){
            ((NormalViewHolder) holder).textView.setText(strings.get(position));
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int postion);
    }

    @Override
    public int getItemViewType(int position) {
        String str = strings.get(position);
        if(position == strings.size() -1){
            return TYPE_LASTER;
        }else if(str.contains("强势插入")){
            return TYPE_BIGIMG;
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
