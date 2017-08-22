package com.webber.demos.anim;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.webber.demos.R;

import java.util.ArrayList;
import java.util.List;

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
    @Bind(R.id.m_content_ll)
    LinearLayout mContentLl;
    @Bind(R.id.m_root_ll)
    LinearLayout mRootLl;
    @Bind(R.id.top1)
    View top1;
    @Bind(R.id.top2)
    View top2;
    @Bind(R.id.top3)
    View top3;
    @Bind(R.id.top4)
    View top4;
    @Bind(R.id.down1)
    View down1;
    @Bind(R.id.down2)
    View down2;
    @Bind(R.id.down3)
    View down3;
    @Bind(R.id.down4)
    View down4;
    @Bind(R.id.m_top_ll)
    LinearLayout mTopLl;
    @Bind(R.id.m_down_ll)
    LinearLayout mDownLl;
    @Bind(R.id.m_layout_anim_ll)
    LinearLayout mLayoutAnimLl;
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
                //startObjAnim3();
                //startObjAnim4();
                //startObjAnim5();
                break;
            case "ValueAnim":
                startValueAnim1();
                //startLayoutAnim1();
                //startLayoutAnim2();
                break;
        }
    }

    /*估值器*/
    private void startObjAnim5() {
        ObjectAnimator anim = ObjectAnimator.ofObject(mContentIv, "Y", new FloatEvaluator(),mContentIv.getY(), 1000);
        anim.setDuration(3000);
        anim.start();
    }

    /*插值器*/
    private void startObjAnim4() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mContentIv, "Y", mContentIv.getY(), 1000);
        objectAnimator.setDuration(3000);
        //objectAnimator.setInterpolator(new MyInterpolator());
        objectAnimator.start();
    }

    class MyInterpolator implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            float result;
            if (input <= 0.5) {
                result = (float) (Math.sin(Math.PI * input)) / 2;
                // 使用正弦函数来实现先减速后加速的功能，逻辑如下：
                // 因为正弦函数初始弧度变化值非常大，刚好和余弦函数是相反的
                // 随着弧度的增加，正弦函数的变化值也会逐渐变小，这样也就实现了减速的效果。
                // 当弧度大于π/2之后，整个过程相反了过来，现在正弦函数的弧度变化值非常小，渐渐随着弧度继续增加，变化值越来越大，弧度到π时结束，这样从0过度到π，也就实现了先减速后加速的效果
            } else {
                result = (float) (2 - Math.sin(Math.PI * input)) / 2;
            }
            return result;
        }
    }

    private void startLayoutAnim2() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slid_right);   //得到一个LayoutAnimationController对象；
        LayoutAnimationController controller = new LayoutAnimationController(animation);   //设置控件显示的顺序；
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);   //设置控件显示间隔时间；
        controller.setDelay(0.3f);   //为ListView设置LayoutAnimationController属性；
        mTopLl.setLayoutAnimation(controller);
        mDownLl.setLayoutAnimation(controller);
        mTopLl.startLayoutAnimation();
        mDownLl.startLayoutAnimation();
    }

    private void startObjAnim3() {
        List<View> views = new ArrayList<>();
        views.add(top1);
        views.add(top2);
        views.add(top3);
        views.add(top4);
        views.add(down1);
        views.add(down2);
        views.add(down3);
        views.add(down4);
        scaleView(views);
    }

    public void scaleView(List<View> vs) {
        int l = 0;
        AnimatorSet set = new AnimatorSet();
        for (int i = 0; i < vs.size(); i++) {
            View v = vs.get(i);
            if (i == vs.size() / 2)
                l = 100;
            l += 100;
            AnimatorSet itemSet = new AnimatorSet();
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1f, 0f);
            scaleX.setRepeatMode(ValueAnimator.RESTART);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1f, 0f);
            scaleY.setRepeatMode(ValueAnimator.RESTART);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
            alpha.setRepeatMode(ValueAnimator.RESTART);
            itemSet.play(scaleX).with(scaleY).with(alpha);
            itemSet.setDuration(250);
            itemSet.setStartDelay(l);
            set.play(itemSet);
        }
        set.start();
    }

    private void startLayoutAnim1() {
        /*LayoutTransition layoutTransition = new LayoutTransition();
        mRootLl.setLayoutTransition(layoutTransition);*/
        mContentLl.setVisibility(mContentLl.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    private void startValueAnim1() {
        //1. 创建句柄
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.5f);
        ValueAnimator valueAnimator = ValueAnimator.ofPropertyValuesHolder(scaleX, scaleY);
        //2. 设置属性变化监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //3. 获取变化值并设置给View
                float animatorValueScaleX = (float) animation.getAnimatedValue("scaleX");
                float animatorValueScaleY = (float) animation.getAnimatedValue("scaleY");
                mContentIv.setScaleX(animatorValueScaleX);
                mContentIv.setScaleY(animatorValueScaleY);
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(2);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startObjAnim2() {
        if (objectAnimatorR == null) {
            objectAnimatorR = ObjectAnimator.ofFloat(mContentIv, "rotation", 0f, 360f);
            objectAnimatorR.setDuration(3000);
            objectAnimatorR.setRepeatCount(3);
            objectAnimatorR.start();
        } else {
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
