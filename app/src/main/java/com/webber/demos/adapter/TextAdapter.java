package com.webber.demos.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webber.demos.R;

import java.util.List;

/**
 * Created by picher on 2018/4/12.
 * Describe：
 */

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {

    private List<String> strings;

    public void setDatas(List<String> datas){
        strings = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItemTv.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings == null?0:strings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView mItemTv;

        ViewHolder(View itemView) {
            super(itemView);
            mItemTv = itemView.findViewById(R.id.m_item_tv);
        }
    }
}
