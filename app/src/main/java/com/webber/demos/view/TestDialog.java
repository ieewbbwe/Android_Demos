package com.webber.demos.view;

import android.util.Pair;
import android.view.Gravity;
import android.view.View;

import com.bana.libcore.core.AbstractDialogFragment;
import com.webber.demos.R;

import org.jetbrains.annotations.NotNull;

/**
 * Created by picher on 2018/6/10.
 * Describe：
 */

public class TestDialog extends AbstractDialogFragment{
    @Override
    protected int getResId() {
        return R.layout.dialog_test_layout;
    }

    @Override
    public void initVariables(@NotNull View container) {

    }

    @Override
    public int gravity() {
        return Gravity.BOTTOM;
    }

    @NotNull
    @Override
    public Pair<Integer, Integer> widthAndHeight() {
        return null;
    }
}
