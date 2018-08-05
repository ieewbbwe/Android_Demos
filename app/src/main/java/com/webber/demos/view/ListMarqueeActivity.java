package com.webber.demos.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.webber.demos.R;
import com.webber.demos.weight.MAutoTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class ListMarqueeActivity extends AppCompatActivity {


    private BreakingNewsRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private CountDownTimer countDownTimer;
    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_marquee);
        List<String> stringList =new ArrayList<>();
        stringList.add("这是一段话！！！1111一段话！！！！！111111一段话！！！！111111一段话！！！！111111一段话！！！！111111一段话！！！！111111 End");
        stringList.add("一段话！！！！2222一段话！！！！2222一段话！！！！2222一段话！！！！2222一段话！！！！2222一段话！！！！2222 End");
        stringList.add("一段话！！！！333333一段话！！！！333333一段话！！！！333333一段话！！！！333333 End");
        stringList.add("44444444 End");
        recyclerView = findViewById(R.id.m_marquee_rv);
        recyclerView.setLayoutManager(new SmoothLinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter = new BreakingNewsRecyclerAdapter(stringList,this);
        recyclerView.setAdapter(adapter);


      /*  countDownTimer = new CountDownTimer(6000, 6000) {

            public void onTick(long l) {
            }

            public void onFinish() {
                try {
                    adapter.setLastPosition(adapter.getCurrentPosition() + 1);
                    recyclerView.smoothScrollToPosition(adapter.getCurrentPosition());
                    start();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        };

        countDownTimer.start();*/
        disposable = Observable.interval(8,8, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d("picher","设置当前到：" + (adapter.getCurrentPosition() + 1));
                        adapter.setLastPosition(adapter.getCurrentPosition() + 1);
                        recyclerView.smoothScrollToPosition(adapter.getCurrentPosition());
                    }
                });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("picher","滚动：" + newState);
                if(newState == SCROLL_STATE_IDLE){
                    //((MarqueeTextView)recyclerView.getLayoutManager().getChildAt(adapter.getRealCurrentPosition() - 1)).startScroll();
                    ((MarqueeTextView)((SmoothLinearLayoutManager) recyclerView.getLayoutManager()).getChildAt(0)).startScroll();
                    //Log.d("picher",""+adapter.getRealCurrentPosition());
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
