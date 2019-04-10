package com.tao.note.ui.activity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tao.note.R;
import com.tao.note.base.BaseActivity;
import com.tao.note.utils.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import butterknife.BindView;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar_base)
    Toolbar mToolbar;
    @BindView(R.id.app_name)
    TextView appName;
    @BindView(R.id.user_phone_number)
    AutoCompleteTextView userPhoneNumber;
    @BindView(R.id.password_form)
    LinearLayout passwordFrom;
    @BindView(R.id.user_password)
    EditText userPassword;
    @BindView(R.id.sign_up_form)
    LinearLayout signUpForm;
    @BindView(R.id.get_verification_code_btn)
    Button getVerCodeBtn;
    @BindView(R.id.user_verification_code)
    AutoCompleteTextView userVerificationCode;
    @BindView(R.id.phone_sign_in_button)
    CircularProgressButton signInBtn;
    @BindView(R.id.forget_tv)
    TextView forgetTextView;
    @BindView(R.id.sign_up_tv)
    TextView signUpTextView;

    private int LOGIN_CODE = Constants.LOGIN_WITH_PASSWORD;
    private BottomSheetDialog bottomSheetDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initListener() {
        signInBtn.setOnClickListener(this);
        forgetTextView.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
        getVerCodeBtn.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone_sign_in_button:
                signInOrSignUp(LOGIN_CODE);
                break;
            case R.id.forget_tv:
                showBottomSheetDialog();
                break;
            case R.id.sign_up_tv:
                signUpOrCancel(LOGIN_CODE);
                break;
            case R.id.get_verification_code_btn:
                break;
            case R.id.bottom_sheet_cancel:
                if (bottomSheetDialog != null) {
                    bottomSheetDialog.cancel();
                }
                break;
            case R.id.bottom_sheet_sign_in:
                setSignInWithVerificationCode();
                break;
            case R.id.bottom_sheet_forget_psw:
                setFindPassword();
                break;
            default:

        }
    }

    private void signUpOrCancel(int login_code) {
        switch (login_code) {
            case Constants.LOGIN_WITH_PASSWORD:
                setSignUp();
                break;
            case Constants.LOGIN_WITH_VERIFICATION_CODE:
                cancelSignInWithCode();
                break;
            case Constants.SIGN_UP:
                cancelSignUp();
                break;
            default:
                break;
        }
    }

    private void setFindPassword() {
        if (bottomSheetDialog != null) {
            bottomSheetDialog.cancel();
        }
        shortToast("Not available now!");
    }

    private void signInOrSignUp(int login_code) {
        switch (login_code) {
            case Constants.LOGIN_WITH_PASSWORD:
                break;
            case Constants.LOGIN_WITH_VERIFICATION_CODE:
                break;
            case Constants.SIGN_UP:
                signUp(userPhoneNumber.getText().toString(),
                        userPassword.getText().toString(),
                        userVerificationCode.getText().toString());
                break;
            default:
                break;
        }
    }

    private void signUp(String phone, String password, String code) {
        if (!validatePhone() || !validatePassword() || !validateCode()) {
            return;
        }
    }

    private boolean validatePassword() {
        String password = userPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            userPassword.setError(getString(R.string.psw_can_not_be_null));
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = userPhoneNumber.getText().toString();
//        String regExp = "^[1](([3|5|8][\\\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\\\d]{8}$";
        // 不准确匹配
        String regExp = "^((1[345][0-9])|(16[56])|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(phone);

        if (TextUtils.isEmpty(phone)) {
            userPhoneNumber.setError(getString(R.string.phone_can_not_be_null));
            return false;
        } else if (!matcher.matches()) {
            userPhoneNumber.setError(getString(R.string.phone_invalidate));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateCode() {
        String code = userVerificationCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            userVerificationCode.setError(getString(R.string.code_can_not_be_null));
            return false;
        } else {
            return true;
        }
    }

    private void cancelSignInWithCode() {
        LOGIN_CODE = Constants.LOGIN_WITH_PASSWORD;
        signUpForm.setVisibility(View.GONE);
        signUpTextView.setText(getString(R.string.sign_up_now));
        forgetTextView.setVisibility(View.VISIBLE);
        passwordFrom.setVisibility(View.VISIBLE);
    }

    private void setSignInWithVerificationCode() {
        LOGIN_CODE = Constants.LOGIN_WITH_VERIFICATION_CODE;
        passwordFrom.setVisibility(View.GONE);
        signUpForm.setVisibility(View.VISIBLE);
        signUpTextView.setText(getString(R.string.login_with_psw));
        forgetTextView.setVisibility(View.INVISIBLE);
        if (bottomSheetDialog != null) {
            bottomSheetDialog.cancel();
        }
    }

    private void cancelSignUp() {
        LOGIN_CODE = Constants.LOGIN_WITH_PASSWORD;
        signUpForm.setVisibility(View.GONE);
        signUpTextView.setText(getString(R.string.sign_up_now));
        forgetTextView.setVisibility(View.VISIBLE);
        signInBtn.setText(getString(R.string.sign_in));
    }

    private void setSignUp() {
        LOGIN_CODE = Constants.SIGN_UP;
        signUpForm.setVisibility(View.VISIBLE);
        signUpTextView.setText(getString(R.string.login_with_psw));
        forgetTextView.setVisibility(View.INVISIBLE);
        signInBtn.setText(getString(R.string.sign_up_now));
    }

    private void showBottomSheetDialog() {
        if (bottomSheetDialog == null) {
            bottomSheetDialog = new BottomSheetDialog(this);
        }
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(this).inflate(R.layout.forget_psw_bottom_sheet, null);
        TextView bottomSheetForgetPsw = view.findViewById(R.id.bottom_sheet_forget_psw);
        TextView bottomSheetCancel = view.findViewById(R.id.bottom_sheet_cancel);
        TextView bottomSheetSignIn = view.findViewById(R.id.bottom_sheet_sign_in);
        bottomSheetCancel.setOnClickListener(this);
        bottomSheetForgetPsw.setOnClickListener(this);
        bottomSheetSignIn.setOnClickListener(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}
