package com.tao.note.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.tao.note.utils.L;

/**
 * Created by Tao Zhou on 2019/5/9
 * Package name: com.tao.note.ui.custom
 */
public class CreateRecordAppBarBehavior extends CoordinatorLayout.Behavior<LinearLayout> {

    private float mChildX;
    private float mChildY;

    public CreateRecordAppBarBehavior() {
    }

    public CreateRecordAppBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull LinearLayout child, @NonNull View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull LinearLayout child, @NonNull View dependency) {
        mChildX = (dependency.getWidth() - child.getWidth()) >> 1;
        mChildY = (dependency.getHeight() - child.getHeight()) >> 1;
        child.setX(mChildX);
        child.setY(mChildY);

        return true;
    }
}
