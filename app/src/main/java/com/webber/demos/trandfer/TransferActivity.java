package com.webber.demos.trandfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.webber.demos.R;
import com.webber.demos.trandfer.bluetooth.BluetoothActivity;
import com.webber.demos.trandfer.socket.SocketActivity;
import com.webber.demos.trandfer.socket.WebSocketActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TransferActivity extends AppCompatActivity {

    @Bind(R.id.m_bluetooth_bt)
    Button mBluetoothBt;
    @Bind(R.id.m_socket_bt)
    Button mSocketBt;
    @Bind(R.id.m_web_socket_bt)
    Button mWebSocketBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);

        mBluetoothBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransferActivity.this, BluetoothActivity.class));
            }
        });
        mSocketBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransferActivity.this, SocketActivity.class));
            }
        });
        mWebSocketBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransferActivity.this, WebSocketActivity.class));
            }
        });
    }

}
