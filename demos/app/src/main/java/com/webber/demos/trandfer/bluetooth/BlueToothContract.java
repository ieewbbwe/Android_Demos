package com.webber.demos.trandfer.bluetooth;

import com.webber.demos.trandfer.ICallback;

import java.io.File;

/**
 * Created by mxh on 2017/8/14.
 * Describe：
 */

public interface BlueToothContract {

    interface View {

    }

    interface Presenter {
        /**
         * 检测蓝牙是否打开
         */
        boolean isBluetoothOpen();

        /**
         * 打开蓝牙
         */
        void openBlueTooth();

        /**
         * 扫描蓝牙设备
         */
        void searchBlueDevice();

        /**
         * 建立连接
         */
        void contract();

        /**
         * 传输
         *
         * @param file     传输的文件
         * @param callback 传输回调
         */
        void transferData(File file, ICallback callback);
    }

}
