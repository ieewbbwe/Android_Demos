package com.webber.demos.weight;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.webber.demos.R;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> strings = new ArrayList<>();
    private RecycleViewTestAdapter testAdapter;
    private RecyclerView recyclerViewLeft;
    private RecycleViewTestAdapter testAdapterLeft;
    private List<String> stringsLeft = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.m_test_rv);
        recyclerViewLeft = (RecyclerView) findViewById(R.id.m_test_second_rv);
        findViewById(R.id.m_recycle_test_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = stringsLeft.get(2);
                str = "asdads";
                stringsLeft.remove(1);
                Toast.makeText(v.getContext(),stringsLeft.get(0),Toast.LENGTH_SHORT).show();
                testAdapterLeft.notifyDataSetChanged();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        testAdapter = new RecycleViewTestAdapter();
        recyclerView.setAdapter(testAdapter);

        recyclerViewLeft.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        testAdapterLeft = new RecycleViewTestAdapter();
        recyclerViewLeft.setAdapter(testAdapterLeft);

        for (int i = 0; i < 25; i++) {
            strings.add("测试数据 + 男.女-->>男．女" + i);
            stringsLeft.add("测试数据 left + 男.女-->>男．女" + i);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                strings.set(1, "测试数据强势插入！！！1");
                testAdapter.notifyDataSetChanged();
            }
        },2000);

        testAdapter.setData(strings);
        testAdapter.notifyDataSetChanged();
        testAdapterLeft.setData(stringsLeft);

        testAdapter.setOnItemClickListener(new RecycleViewTestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int postion) {

                Log.d("picher","pos:"+postion);

                View focusChild = v;//recyclerView.getLayoutManager().getChildAt(postion);

                Log.d("picher","left:"+focusChild.getLeft()+"->>width:"+focusChild.getWidth());

                Log.d("picher","recycleW:"+recyclerView.getWidth());
                if(focusChild != null){
                    int[] locations = new int[2];
                    focusChild.getLocationInWindow(locations);
                    int distanceX = focusChild.getLeft();// - recyclerView.computeHorizontalScrollOffset();
                    int centerX = (int) ((recyclerView.getX() + recyclerView.getWidth()/2) - focusChild.getWidth() * focusChild.getScaleX()/2);
                    if(locations[0] != centerX){
                       recyclerView.smoothScrollBy(distanceX - centerX, 0);
                    }
                }
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("picher", "canScroll（1）:" + recyclerView.canScrollVertically(1) +
                        "-->>canScroll(-1) :" + recyclerView.canScrollVertically(-1));
               /* //1. lastVisible 判断
                Log.d("picher", "最后一可见位:" + ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition());
                //2. scrollRange 判断
                Log.d("picher", "Offset:" + recyclerView.computeVerticalScrollOffset() + "-->>Extend:" + recyclerView.computeVerticalScrollExtent() + "-->>Range：" + recyclerView.computeVerticalScrollRange());
                //3. canScrollVertical 判断
                Log.d("picher", "canScroll（1）:" + recyclerView.canScrollVertically(1) + "-->>canScroll(-1) :" + recyclerView.canScrollVertically(-1));
                //4.*/
            }
        });
    }
}
