package com.webber.demos.trandfer.bluetooth;

import android.bluetooth.BluetoothAdapter;

import com.webber.demos.trandfer.ICallback;

import java.io.File;

/**
 * Created by mxh on 2017/8/14.
 * Describe：
 */

public class BlueToothPresenter implements BlueToothContract.Presenter {

    private BluetoothAdapter mBluetoothAdapter;
    private BlueToothContract.View mBlueView;

    public BlueToothPresenter() {
    }

    public BlueToothPresenter(BlueToothContract.View view) {
        this.mBlueView = view;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * 检测蓝牙是否打开
     */
    @Override
    public boolean isBluetoothOpen() {
        return mBluetoothAdapter != null && mBluetoothAdapter.isEnabled();
    }

    /**
     * 打开蓝牙
     */
    @Override
    public void openBlueTooth() {
        if (!mBluetoothAdapter.isEnabled()) {
            mBlueView.requireBlue();
        }
    }

    /**
     * 扫描蓝牙设备
     */
    @Override
    public void searchBlueDevice() {
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.startDiscovery();
        }
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
