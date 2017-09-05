package com.webber.demos.trandfer.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.webber.demos.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BluetoothActivity extends AppCompatActivity implements BlueToothContract.View {

    private static final int REQUEST_ENABLE = 0x01;
    @Bind(R.id.m_open_bt)
    Button mOpenBt;
    @Bind(R.id.m_search_bt)
    Button mSearchBt;
    @Bind(R.id.m_file_chose_vt)
    Button mFileChoseVt;
    @Bind(R.id.m_file_path_tv)
    TextView mFilePathTv;
    @Bind(R.id.m_devices_rlv)
    RecyclerView mDevicesRlv;

    private BlueToothPresenter presenter;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_actiity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        presenter = new BlueToothPresenter(this);
        initListener();
    }

    private void initListener() {
        mOpenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openBlueTooth();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("picher_log", "reqCode:" + requestCode + "resCode:" + resultCode);
    }

    @Override
    public void requireBlue() {
        //弹出对话框提示用户是后打开
        Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enabler, REQUEST_ENABLE);
    }
}
