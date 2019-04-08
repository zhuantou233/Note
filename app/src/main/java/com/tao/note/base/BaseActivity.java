package com.tao.note.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tao.note.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = true;
    private boolean isSetStatusBarColor = true;

    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (isSetStatusBar) {
            steepStatusBar();
        }
        if (isSetStatusBarColor) {
            statusBarColor();
        }
        initUI();
        initData();
        initListener();
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout layoutContainer = view.findViewById(R.id.layout_container);
        getLayoutInflater().inflate(layoutResID, layoutContainer, true);
        super.setContentView(view);
    }

//    @Override
//    public void setContentView(View view) {
//        super.setContentView(view);
//        initUI();
//        initData();
//        initListener();
//    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initUI();

    protected abstract int getLayoutId();

    protected void longToast(String mToastMsg) {
        Toast.makeText(this, mToastMsg, Toast.LENGTH_LONG).show();
    }

    protected void shortToast(String mToastMsg) {
        Toast.makeText(this, mToastMsg, Toast.LENGTH_SHORT).show();
    }

    protected void hideKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("MultiBackStack", "Failed to add fragment to back stack", e);
        }
    }


    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 是否设置沉浸状态栏
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    private void statusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void setStatusBarColor(boolean isSetStatusBarColor) {
        this.isSetStatusBarColor = isSetStatusBarColor;
    }


    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
