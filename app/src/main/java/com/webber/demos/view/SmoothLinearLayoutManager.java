package com.webber.demos.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

public class SmoothLinearLayoutManager extends LinearLayoutManager {

    private static final float MILLISECONDS_PER_INCH = 300f;
    private Context mContext;

    public SmoothLinearLayoutManager(Context context, int horizontal, boolean b) {
        super(context, horizontal, b);
        mContext = context;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView,
                                       RecyclerView.State state, final int position) {

        if(mContext == null) {
            return;
        }
        LinearSmoothScroller smoothScroller =
                new LinearSmoothScroller(mContext) {

                    @Override
                    public PointF computeScrollVectorForPosition
                            (int targetPosition) {
                        return SmoothLinearLayoutManager.this
                                .computeScrollVectorForPosition(targetPosition);
                    }

                    @Override
                    protected float calculateSpeedPerPixel
                            (DisplayMetrics displayMetrics) {
                        return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
                    }
                };

        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

}