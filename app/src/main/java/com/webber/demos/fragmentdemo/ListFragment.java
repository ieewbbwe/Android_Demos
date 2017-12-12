package com.webber.demos.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webber.demos.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by picher on 2017/12/12.
 * Describe：
 */

public class ListFragment extends BaseFragment {

    private int size = 50;
    private String[] data = new String[size];

    int currentPos = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_layout, container, false);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                currentPos++;
                Log.d("picher",""+currentPos);
            }
        },10000);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mListContentRlv = (RecyclerView) view.findViewById(R.id.m_list_content_rlv);
        mListContentRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ListAdapter listAdapter = new ListAdapter();
        mListContentRlv.setAdapter(listAdapter);
        if(currentPos == 0){
            for (int i = 0; i < size; i++) {
                data[i] = "我是数据小 " + i + "";
            }
        }else{
            for (int i = 0; i < size; i++) {
                data[i] = "我改变啦！！ " + i + "";
            }
        }

        listAdapter.setData(data);

    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        private String[] strs;

        public void setData(String[] data) {
            Log.d("picher","设置数据");
            this.strs = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(strs[position]);
        }

        @Override
        public int getItemCount() {
            return strs.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public ViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
