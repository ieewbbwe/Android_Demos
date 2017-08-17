package com.webber.demos.view.camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.webber.demos.R;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity {

    /*请求相机权限*/
    private static final int REQUEST_CAMERA_PERMISSIONS_CODE = 0x01;
    private SurfaceView mCameraSv;
    private Camera mCamera;
    private SurfaceHolder mHolder;
    private CameraView mCameraCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCameraSv = (SurfaceView) findViewById(R.id.m_camera_sv);
        mCameraCv = (CameraView) findViewById(R.id.m_camera_cv);

        mHolder = mCameraSv.getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d("picher_log", "surfaceCreated");
                startDisplay();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d("picher_log", "surfaceChanged");

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("picher_log", "surfaceDestroyed");
                releaseCamera();
            }
        });
    }

    private void startDisplay() {
        if (mCamera != null) {
            mCamera.startPreview();
            try {
                int orientation = getCameraOrientation();

                mCamera.setDisplayOrientation(orientation);
                mCamera.getParameters().setRotation(orientation);
                mCamera.setPreviewDisplay(mHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int getCameraOrientation() {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, info);

        int rotation = getWindowManager().getDefaultDisplay().getRotation();

        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int orientation;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            orientation = (info.orientation + degrees) % 360;
            orientation = (360 - orientation) % 360;
        } else {
            orientation = (info.orientation - degrees + 360) % 360;
        }

        return orientation;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("picher_log", "onResume");
        requestCameraPermission();
    }

    private void requestCameraPermission() {
        //6.0 以上的权限适配，并且检查是否有相机权限，没有则申请
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && this.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSIONS_CODE);
        } else {
            initCamera();
        }
    }

    public void initCamera() {
        int numCams = Camera.getNumberOfCameras();
        // 检查是否存在相机设备
        if (numCams > 0) {
            // 开启相机
            mCamera = Camera.open();
            mCameraCv.setCamera(mCamera);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSIONS_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("picher_log", "权限通过");
                //startDisplay();
            }
        }
    }

    public void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
}
