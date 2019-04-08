package com.tao.note.ui.activity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.tao.note.R;
import com.tao.note.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar_base) Toolbar mToolbar;
    @BindView(R.id.app_name) TextView appName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initUI() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().hide();
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/webfont.ttf");
        appName.setTypeface(typeface);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


}
