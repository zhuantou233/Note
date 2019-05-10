package com.tao.note.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.tao.note.NoteApp;
import com.tao.note.utils.L;
import com.tao.note.utils.Util;

/**
 * Created by Tao Zhou on 2019/5/9
 * Package name: com.tao.note.ui.custom
 */
public class CreateRecordAppBarBehavior extends CoordinatorLayout.Behavior<LinearLayout> {

    private float mChildX;
    private float mChildY;
    private float mChildScale;
    private float mChildStartX = 0;
    private float mChildStartY = 0;
    private float mChildEndX = 0;
    private float mChildEndY = 0;

    public CreateRecordAppBarBehavior() {
    }

    public CreateRecordAppBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull LinearLayout child, @NonNull View dependency) {
        return dependency instanceof Toolbar;
    }


    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull LinearLayout child, @NonNull View dependency) {
        float scaleRatio = 0.6f;

        // 计算X轴坐标
        if (mChildStartX == 0) {
            mChildStartX = (dependency.getWidth() >> 1) - (child.getWidth() >> 1);
        }
        // 计算Y轴坐标
        if (mChildStartY == 0) {
            mChildStartY = (dependency.getHeight() >> 1) - (child.getHeight() >> 1);
        }

        if (mChildEndX == 0) {
            mChildEndX = dependency.getWidth() * 0.185f;
        }
        // 计算Y轴坐标
        if (mChildEndY == 0) {
            mChildEndY = (Util.dip2px(NoteApp.getContext(), 56) - scaleRatio * child.getHeight()) / 2;
        }

        float dependencyMaxMove = dependency.getHeight() - Util.dip2px(NoteApp.getContext(), 80);

        mChildX = mChildStartX + (mChildEndX - mChildStartX) / dependencyMaxMove * (-dependency.getY());
        mChildY = mChildStartY + (mChildEndY - mChildStartY) / dependencyMaxMove * (-dependency.getY());
        mChildScale = 1 + (scaleRatio - 1) / dependencyMaxMove * (-dependency.getY());

        child.setX(mChildX);
        child.setY(mChildY);
        child.setScaleY(mChildScale);
        child.setScaleX(mChildScale);

        return true;
    }
}
