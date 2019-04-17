package com.tao.note.ui.login;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.tao.note.ui.base.BaseActivity;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.ui.main.MainActivity;
import com.tao.note.utils.Constants;
import com.tao.note.utils.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import butterknife.BindView;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

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
    @BindView(R.id.phone_login_form)
    LinearLayout phoneFrom;
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
    @BindView(R.id.placeholder)
    View placeHolder;

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/webfont.ttf");
        appName.setTypeface(typeface);
        setSteepStatusBar(true);
        setStatusBarColor(true);
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
                getVerCode();
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

    private void getVerCode() {
        if (!validatePhone()) {
            return;
        }
        if (!Util.isNetworkConnected(this)) {
            longToast(getString(R.string.no_network));
            return;
        }
        new MyCountDownTimer(60000, 1000).start();
        BmobSMS.requestSMSCode(userPhoneNumber.getText().toString(), "", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {
                    shortToast("发送验证码成功，短信ID：" + smsId + "\n");
                } else {
                    shortToast("发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n");
                }
            }
        });
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
        if (!Util.isNetworkConnected(this)) {
            longToast(getString(R.string.no_network));
            return;
        }
        switch (login_code) {
            case Constants.LOGIN_WITH_PASSWORD:
                signIn(userPhoneNumber.getText().toString(),
                        userPassword.getText().toString());
                break;
            case Constants.LOGIN_WITH_VERIFICATION_CODE:
                signInWithCode(userPhoneNumber.getText().toString(),
                        userVerificationCode.getText().toString());
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

    private void signInWithCode(String phone, String code) {
        if (!validatePhone() || !validateCode()) {
            return;
        }
        setInputStatus(false);
        signInBtn.startAnimation(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                return null;
            }
        });
        BmobUser.signOrLoginByMobilePhone(phone, code, new LogInListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    loginSuccess();
                } else {
                    signInBtn.revertAnimation(new Function0<Unit>() {
                        @Override
                        public Unit invoke() {
                            return null;
                        }
                    });
                    longToast(getString(R.string.account_code_error));
                }
            }
        });
    }

    private void signIn(String phone, String password) {
        if (!validatePhone() || !validatePassword()) {
            return;
        }
        setInputStatus(false);
        signInBtn.startAnimation(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                return null;
            }
        });
        BmobUser.loginByAccount(phone, password, new LogInListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    loginSuccess();
                } else {
                    signInBtn.revertAnimation(new Function0<Unit>() {
                        @Override
                        public Unit invoke() {
                            return null;
                        }
                    });
                    longToast(getString(R.string.account_psw_error));
                }
            }
        });
    }

    private void signUp(String phone, String password, String code) {
        if (!validatePhone() || !validatePassword() || !validateCode()) {
            return;
        }
        setInputStatus(false);
        signInBtn.startAnimation(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                return null;
            }
        });
        MyUser user = new MyUser();
        user.setMobilePhoneNumber(phone);
        user.setPassword(password);
        user.signOrLogin(code, new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                setInputStatus(true);
                if (e == null) {
                    loginSuccess();
                } else {
                    signInBtn.revertAnimation(new Function0<Unit>() {
                        @Override
                        public Unit invoke() {
                            signInBtn.setText(getString(R.string.sign_up_now));
                            return null;
                        }
                    });
                    longToast(getString(R.string.sign_up_fail));
                }
            }
        });
    }

    private void loginSuccess() {
        shortToast(getString(R.string.login_success));
        signInBtn.doneLoadingAnimation(
                R.color.colorTheme,
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void setInputStatus(boolean flag) {
        userPhoneNumber.setFocusable(flag);
        userPhoneNumber.setFocusableInTouchMode(flag);
        userPassword.setFocusable(flag);
        userPassword.setFocusableInTouchMode(flag);
        userVerificationCode.setFocusable(flag);
        userVerificationCode.setFocusableInTouchMode(flag);
    }

    private boolean validatePassword() {
        String password = userPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            userPassword.setError(getString(R.string.psw_can_not_be_null));
            userPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = userPhoneNumber.getText().toString();
//        String regExp = "^[1](([3|5|8][\\\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\\\d]{8}$";
        // 不准确匹配
        String regExp = getString(R.string.phone_reg);
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(phone);

        if (TextUtils.isEmpty(phone)) {
            userPhoneNumber.setError(getString(R.string.phone_can_not_be_null));
            userPhoneNumber.requestFocus();
            return false;
        } else if (!matcher.matches()) {
            userPhoneNumber.setError(getString(R.string.phone_invalidate));
            userPhoneNumber.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateCode() {
        String code = userVerificationCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            userVerificationCode.setError(getString(R.string.code_can_not_be_null));
            userVerificationCode.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void cancelSignInWithCode() {
        LOGIN_CODE = Constants.LOGIN_WITH_PASSWORD;
        signUpForm.setVisibility(View.INVISIBLE);
        signUpTextView.setText(getString(R.string.sign_up_now));
        forgetTextView.setVisibility(View.VISIBLE);
        passwordFrom.setVisibility(View.VISIBLE);
        userPhoneNumber.setError(null);
        userPassword.setError(null);
        userVerificationCode.setError(null);
        placeHolder.setVisibility(View.GONE);
    }

    private void setSignInWithVerificationCode() {
        LOGIN_CODE = Constants.LOGIN_WITH_VERIFICATION_CODE;
        passwordFrom.setVisibility(View.GONE);
        signUpForm.setVisibility(View.VISIBLE);
        signUpTextView.setText(getString(R.string.login_with_psw));
        forgetTextView.setVisibility(View.INVISIBLE);
        placeHolder.setVisibility(View.INVISIBLE);
        if (bottomSheetDialog != null) {
            bottomSheetDialog.cancel();
        }
        userPhoneNumber.setError(null);
        userPassword.setError(null);
        userVerificationCode.setError(null);
    }

    private void cancelSignUp() {
        LOGIN_CODE = Constants.LOGIN_WITH_PASSWORD;
        signUpForm.setVisibility(View.INVISIBLE);
        signUpTextView.setText(getString(R.string.sign_up_now));
        forgetTextView.setVisibility(View.VISIBLE);
        signInBtn.setText(getString(R.string.sign_in));
        userPhoneNumber.setError(null);
        userPassword.setError(null);
        userVerificationCode.setError(null);
    }

    private void setSignUp() {
        LOGIN_CODE = Constants.SIGN_UP;
        signUpForm.setVisibility(View.VISIBLE);
        signUpTextView.setText(getString(R.string.login_with_psw));
        forgetTextView.setVisibility(View.INVISIBLE);
        signInBtn.setText(getString(R.string.sign_up_now));
        userPhoneNumber.setError(null);
        userPassword.setError(null);
        userVerificationCode.setError(null);
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

    private class MyCountDownTimer extends CountDownTimer {

        private MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {

            getVerCodeBtn.setClickable(false);
            String text = String.valueOf(l / 1000) + "s";
            getVerCodeBtn.setText(text);

        }

        @Override
        public void onFinish() {

            getVerCodeBtn.setText(getString(R.string.get_verification_code));
            getVerCodeBtn.setClickable(true);
        }
    }
}
