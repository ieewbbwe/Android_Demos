package com.webber.demos.tv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.webber.demos.R;
import com.webber.demos.tv.focus.TvFocusActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TvDemosActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.m_focus_bt)
    Button mFocusBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_demos);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mFocusBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.m_focus_bt:
                startActivity(new Intent(TvDemosActivity.this, TvFocusActivity.class));
                break;
        }
    }
}
