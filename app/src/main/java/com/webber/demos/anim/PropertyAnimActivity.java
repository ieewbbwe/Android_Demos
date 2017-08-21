package com.webber.demos.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.webber.demos.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PropertyAnimActivity extends AppCompatActivity {

    @Bind(R.id.m_start_bt)
    Button mStartBt;
    @Bind(R.id.m_stop_bt)
    Button mStopBt;
    @Bind(R.id.m_choose_sp)
    Spinner mChooseSp;
    @Bind(R.id.m_content_iv)
    ImageView mContentIv;
    private ObjectAnimator objectAnimatorR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this, R.layout.simple_spinner_item, getResources().getStringArray(R.array.arr_property_anim));
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        mChooseSp.setAdapter(adapter);
        mStartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchAnimStart((String) mChooseSp.getSelectedItem());
            }
        });

        mStopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchAnimStop((String) mChooseSp.getSelectedItem());
            }
        });

        mContentIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PropertyAnimActivity.this, "点击了！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void switchAnimStop(String selectedItem) {
        if (objectAnimatorR != null && objectAnimatorR.isStarted()) {
            objectAnimatorR.pause();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void switchAnimStart(String selectedItem) {
        switch (selectedItem) {
            case "ObjectAnim":
                //startObjAnim();
                //startObjAnim1();
                startObjAnim2();
                break;
            case "ValueAnim":
                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startObjAnim2() {
        if (objectAnimatorR == null) {
            objectAnimatorR = ObjectAnimator.ofFloat(mContentIv, "rotation", 0f, 360f);
            objectAnimatorR.setDuration(3000);
            objectAnimatorR.setRepeatCount(3);
            objectAnimatorR.start();
        }else{
            objectAnimatorR.resume();
        }
    }

    /*X,Y方向都移动300个像素*/
    private void startObjAnim1() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(mContentIv, "Y", mContentIv.getY(), 300);
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(mContentIv, "X", mContentIv.getX(), 100);
        ObjectAnimator objectAnimatorRx = ObjectAnimator.ofFloat(mContentIv, "rotationX", 0f, 180f);
        ObjectAnimator objectAnimatorSx = ObjectAnimator.ofFloat(mContentIv, "scaleX", 1f, 0.5f);
        ObjectAnimator objectAnimatorA = ObjectAnimator.ofFloat(mContentIv, "alpha", 1f, 0.5f);
        animatorSet.play(objectAnimatorX).with(objectAnimatorY).with(objectAnimatorA).with(objectAnimatorSx).with(objectAnimatorRx);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }

    /*Y轴方向移动到300*/
    private void startObjAnim() {
        // 第一个参数是执行动画的View，第二个参数是该View需要改变的属性，第三个参数是执行开始Y坐标，以屏幕坐标系为参照，第四个参数是移动到的位置
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(mContentIv, "Y", mContentIv.getY(), 300);
        objectAnimatorY.setDuration(2000);
        objectAnimatorY.setRepeatCount(2);
        objectAnimatorY.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimatorY.setInterpolator(new BounceInterpolator());
        objectAnimatorY.start();
    }
}
