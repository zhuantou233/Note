package com.tao.note.ui.custom;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

/**
 * Created by Tao Zhou on 2019/4/24
 * Package name: com.tao.note.ui.custom
 */
public class MyPasswordWatcher implements TextWatcher {

    private TextInputLayout layout;

    public MyPasswordWatcher(TextInputLayout layout) {
        this.layout = layout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        layout.setPasswordVisibilityToggleEnabled(!TextUtils.isEmpty(s));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
