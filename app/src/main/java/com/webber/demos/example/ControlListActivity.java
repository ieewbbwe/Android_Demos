package com.webber.demos.example;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.webber.demos.R;

import java.util.ArrayList;
import java.util.List;

public class ControlListActivity extends AppCompatActivity {

    private RecyclerView mDemoRv;
    private LinearLayoutManager mLayoutManager;
    private List<ControlItemModel> mListDatas = new ArrayList<>();
    private ControlAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_list);
        mDemoRv = findViewById(R.id.m_demo_rv);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ControlAdapter();
        mDemoRv.setLayoutManager(mLayoutManager);
        mDemoRv.setAdapter(mAdapter);
        Log.d("picher","设置数据之前：" + mLayoutManager.findLastVisibleItemPosition());
        for (int i = 0; i < 20; i++) {
            mListDatas.add(new ControlItemModel());
        }
        mAdapter.notifyDataSetChanged();
        Log.d("picher","更新数据之后：" + mLayoutManager.findLastVisibleItemPosition());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("picher","延迟之后：" + mLayoutManager.findLastVisibleItemPosition());
            }
        },1500);
        mDemoRv.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.d("picher","layoutChange：" + mLayoutManager.findLastVisibleItemPosition());
            }
        });

        mDemoRv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("picher","onGlobalLayout：" + mLayoutManager.findLastVisibleItemPosition());
            }
        });
    }


    public class ControlAdapter extends RecyclerView.Adapter<ControlAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.control_item, parent, false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Log.d("picher","bindData:"+position);
            ControlItemModel item = mListDatas.get(position);

            holder.mItemEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                        //处理事件
                        mListDatas.add(position + 1,new ControlItemModel());
                        mAdapter.notifyItemInserted(position+1);
                    } else if (event != null && KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                        Log.d("picher", "删除键");
                        //holder.mItemCb.setVisibility(TextUtils.isEmpty(holder.mItemEt.getText().toString()) ? View.GONE:View.VISIBLE);
                    }
                    return true;
                }
            });

            holder.mItemEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    holder.mItemCb.setVisibility(TextUtils.isEmpty(s.toString().trim()) ? View.GONE : View.VISIBLE);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mListDatas == null ? 0 : mListDatas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            CheckBox mItemCb;
            EditText mItemEt;

            ViewHolder(View itemView) {
                super(itemView);
                mItemCb = itemView.findViewById(R.id.m_item_cb);
                mItemEt = itemView.findViewById(R.id.m_item_et);
            }
        }
    }

    public class ControlItemModel {
        private String test;
        private boolean focusable;
    }
}

