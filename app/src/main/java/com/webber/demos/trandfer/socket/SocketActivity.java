package com.webber.demos.trandfer.socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.webber.demos.R;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executors;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SocketActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.m_start_bt)
    Button mStartBt;
    @Bind(R.id.m_close_bt)
    Button mCloseBt;
    @Bind(R.id.m_message_et)
    EditText mMessageEt;
    @Bind(R.id.m_send_bt)
    Button mSendBt;
    @Bind(R.id.m_test_tv)
    TextView mTestTv;
    private String HOST = "10.27.0.197";
    private int PORT = 1993;
    private Socket mSocket;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("picher_log", "接收到数据：" + (String) msg.obj);
                    mTestTv.setText(mTestTv.getText() + "\n" + msg.obj);
                    break;
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mStartBt.setOnClickListener(this);
        mCloseBt.setOnClickListener(this);
        mSendBt.setOnClickListener(this);
    }

    private void openSocketClientChannel() {
        try {
            if (mSocket == null) {
                //1 创建Socket实例打开通道
                mSocket = new Socket(HOST, PORT);
                Log.d("picher_log", "Socket开启！");
                //2 创建读数据线程，从Socket中读出数据
                new Thread(new ReadHandlerThread()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                mSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.m_start_bt:
                Executors.newSingleThreadExecutor().submit(new Runnable() {
                    @Override
                    public void run() {
                        openSocketClientChannel();
                    }
                });
                break;
            case R.id.m_stop_bt:
                closeSocketClientChannel();
                break;
            case R.id.m_send_bt:
                sendSocketData();
                break;
        }
    }

    public void sendSocketData() {
        new Thread(new WriteHandlerThread()).start();
    }

    private class ReadHandlerThread implements Runnable {

        @Override
        public void run() {
            DataInputStream inputStream;
            try {
                //3 向Socket中发送数据
                inputStream = new DataInputStream(mSocket.getInputStream());
                String receiver = inputStream.readUTF();
                handler.obtainMessage(1, receiver).sendToTarget();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class WriteHandlerThread implements Runnable {

        @Override
        public void run() {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            String str = mMessageEt.getText().toString().trim();
            Log.d("picher_log", "发送的数据是:：" + str);
            try {
                outputStream = mSocket.getOutputStream();
                inputStream = new ByteArrayInputStream(str.getBytes());
                byte[] buffer = new byte[1024 * 4];
                int temp = 0;
                while ((temp = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, temp);
                }
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void closeSocketClientChannel() {
        if (mSocket != null) {
            try {
                mSocket.close();
                Log.d("picher_log", "Socket已关闭！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
