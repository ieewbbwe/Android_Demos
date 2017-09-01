package com.webber.demos.trandfer.socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.webber.demos.R;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SocketActivity extends AppCompatActivity {

    @Bind(R.id.m_message_et)
    EditText mMessageEt;
    @Bind(R.id.m_send_bt)
    Button mSendBt;
    @Bind(R.id.m_test_tv)
    TextView mTestTv;
    private String HOST = "10.27.0.197";
    private int PORT = 1993;
    private Socket mSocket;
    private ClientThread mClientThread;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
    }

    private void init() {
        try {
            mSocket = new Socket(HOST, PORT);

          /*  mClientThread = new ClientThread(mSocket);
            mClientThread.start();*/

            //1 启动两个线程，一个负责接收服务器的数据，另一个负责发送数据
            //new Thread(new WriteHandlerThread()).start();
            new Thread(new ReadHandlerThread()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        mSendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mSocket.getOutputStream().write((mMessageEt.getText().toString() + "\r\n").getBytes("utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //new Thread(new WriteHandlerThread()).start();
             /*   Message msg = new Message();
                msg.what = 1;
                msg.obj = mMessageEt.getText().toString();
                mClientThread.revHandler.sendMessage(msg);
                mMessageEt.setText("");*/
            }
        });
    }

    private class ClientThread extends Thread {
        private Socket client;
        private BufferedReader bufferedReader;
        private OutputStream outputStream;
        public Handler revHandler;

        public ClientThread(Socket socket) {
            this.client = socket;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "utf-8"));
                outputStream = mSocket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            super.run();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //获取服务端传来的信息
                    String content;
                    try {
                        Log.d("picher_log", "获取服务端信息：" + bufferedReader.readLine());
                        while ((content = bufferedReader.readLine()) != null) {
                            handler.obtainMessage(1, content).sendToTarget();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Looper.prepare();
            revHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1) {
                        try {
                            outputStream.write((msg.obj.toString() + "\r\n").getBytes("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        }
    }

    private class ReadHandlerThread implements Runnable {

        @Override
        public void run() {
            String content;
            try {
                // 接收Socket发来的数据，只要有客户端向服务器发送了数据，服务器就负责把它传递给所有客户端
                BufferedReader mBufferReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
                while ((content = mBufferReader.readLine()) != null) {
                    handler.obtainMessage(1, content).sendToTarget();
                }
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
            Log.d("picher_log", mSocket.getLocalAddress() + "发送的数据是:：" + str);
            try {
                // 给Socket写入数据
                outputStream = mSocket.getOutputStream();
                inputStream = new ByteArrayInputStream(str.getBytes("utf-8"));
                byte[] buffer = new byte[1024 * 4];
                int temp = 0;
                while ((temp = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, temp);
                }
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
