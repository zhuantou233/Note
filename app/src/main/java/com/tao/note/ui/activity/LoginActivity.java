package com.tao.note.ui.activity;


import com.tao.note.R;
import com.tao.note.base.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar_base) Toolbar mToolbar;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initUI() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("login");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
