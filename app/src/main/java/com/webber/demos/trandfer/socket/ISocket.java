package com.webber.demos.trandfer.socket;

import java.io.OutputStream;

/**
 * Created by mxh on 2017/8/23.
 * Describe：
 */

public interface ISocket {

    void openSocketClientChannel();

    void openSocketServiceChannel();

    void transferData(OutputStream outputStream);
}
