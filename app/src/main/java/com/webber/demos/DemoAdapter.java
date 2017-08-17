package com.webber.demos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by mxh on 2017/8/14.
 * Describe：
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    private AdapterView.OnItemClickListener listener;
    private DemoInfo[] demoInfos;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(DemoInfo[] demoInfos) {
        this.demoInfos = demoInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_demo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mDemoBt.setText(demoInfos[position].getTitle() + "\n" + demoInfos[position].getDesc());

        if (listener != null) {
            holder.mDemoBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(null, v, position, 0);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return demoInfos.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Button mDemoBt;

        public ViewHolder(View itemView) {
            super(itemView);
            mDemoBt = (Button) itemView.findViewById(R.id.m_item_bt);
        }
    }
}
