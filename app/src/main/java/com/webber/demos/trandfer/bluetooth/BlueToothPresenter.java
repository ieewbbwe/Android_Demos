package com.webber.demos.trandfer.bluetooth;

import android.bluetooth.BluetoothAdapter;

import com.webber.demos.trandfer.ICallback;

import java.io.File;

/**
 * Created by mxh on 2017/8/14.
 * Describe：
 */

public class BlueToothPresenter implements BlueToothContract.Presenter {

    private BluetoothAdapter bluetoothAdapter;
    private BlueToothContract.View blueView;

    public BlueToothPresenter() {
    }

    public BlueToothPresenter(BlueToothContract.View view) {
        this.blueView = view;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * 检测蓝牙是否打开
     */
    @Override
    public boolean isBluetoothOpen() {

        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    /**
     * 打开蓝牙
     */
    @Override
    public void openBlueTooth() {
        if (!bluetoothAdapter.isEnabled()) {
           /* //弹出对话框提示用户是后打开
            Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enabler, REQUEST_ENABLE);*/
        }
    }

    /**
     * 扫描蓝牙设备
     */
    @Override
    public void searchBlueDevice() {

    }

    /**
     * 建立连接
     */
    @Override
    public void contract() {

    }

    /**
     * 传输
     *
     * @param file     传输的文件
     * @param callback 传输回调
     */
    @Override
    public void transferData(File file, ICallback callback) {

    }
}
