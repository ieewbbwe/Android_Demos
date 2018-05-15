package com.webber.demos.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webber.demos.R;

/**
 * Created by picher on 2018/5/8.
 * Describe：
 */

public class NoteView extends LinearLayout {

    private LayoutParams mLineParam;

    public NoteView(Context context) {
        this(context, null);
    }

    public NoteView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLineParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(createLine(), mLineParam);
    }

    private View createLine() {
        View lineView = LayoutInflater.from(getContext()).inflate(R.layout.control_item, null, false);
        CheckBox itemCb = lineView.findViewById(R.id.m_item_cb);
        EditText itemEt = lineView.findViewById(R.id.m_item_et);
        setListener(itemCb,itemEt);
        itemEt.requestFocus();
        return lineView;
    }

    private void setListener(final CheckBox mItemCb, EditText mItemEt) {
        mItemEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    addView(createLine(),mLineParam);
                } else if (event != null && KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                    Log.d("picher", "delete logic");
                    //holder.mItemCb.setVisibility(TextUtils.isEmpty(holder.mItemEt.getText().toString()) ? View.GONE:View.VISIBLE);
                }
                return true;
            }
        });

        mItemEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mItemCb.setVisibility(TextUtils.isEmpty(s.toString().trim()) ? View.GONE : View.VISIBLE);
            }
        });
    }

}
