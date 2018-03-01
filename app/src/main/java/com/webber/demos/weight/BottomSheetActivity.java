package com.webber.demos.weight;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webber.demos.R;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView bottomSheetView;
    private BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        View show = findViewById(R.id.show);
        View hide = findViewById(R.id.hide);
        View expend = findViewById(R.id.expended);
        View collapsed = findViewById(R.id.collapsed);

        show.setOnClickListener(this);
        hide.setOnClickListener(this);
        expend.setOnClickListener(this);
        collapsed.setOnClickListener(this);

        CoordinatorLayout mCoordinatorLayout = findViewById(R.id.m_container_cdl);
        bottomSheetView = findViewById(R.id.m_bottom_sheet);
        bottomSheetView.setLayoutManager(new LinearLayoutManager(this));
        bottomSheetView.setAdapter(new SimpleAdapter());

        behavior = BottomSheetBehavior.from(bottomSheetView);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.d("picher","onStateChange:" + newState);
                // STATE_DRAGGING=1  正在拖拽
                // STATE_SETTLING=2 释放
                // STATE_EXPANDED=3 展开
                // STATE_COLLAPSED=4 收起
                // STATE_HIDDEN=5 隐藏
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d("picher","onSlide:" + slideOffset);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show:
                changeState();
                break;
            case R.id.hide:
                changeState();
                break;
            case R.id.expended:
                changeState();
                break;
            case R.id.collapsed:
                changeState();
                break;
        }
    }

    private void changeState() {
        switch (behavior.getState()){
            case BottomSheetBehavior.STATE_EXPANDED:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case BottomSheetBehavior.STATE_HIDDEN:
            case BottomSheetBehavior.STATE_COLLAPSED:
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case BottomSheetBehavior.STATE_DRAGGING:
                break;
        }
    }

    public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder>{

        List<String> strings = new ArrayList<>();

        public SimpleAdapter(){
            for(int i=0;i<50;i++){
                strings.add(i+"");
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(strings.get(position));
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
