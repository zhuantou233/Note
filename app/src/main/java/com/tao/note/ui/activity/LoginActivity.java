package com.tao.note.ui.activity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tao.note.R;
import com.tao.note.base.BaseActivity;
import com.tao.note.utils.Constants;

import org.w3c.dom.Text;

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

                break;
            case R.id.forget_tv:
                showBottomSheetDialog();
                break;
            case R.id.sign_up_tv:
                if (LOGIN_CODE == Constants.LOGIN_WITH_PASSWORD) {
                    signUp();
                } else if (LOGIN_CODE == Constants.SIGN_UP) {
                    cancelSignUp();
                }
                break;
            case R.id.get_verification_code_btn:
                break;
            case R.id.bottom_sheet_cancel:
                if (bottomSheetDialog != null) {
                    bottomSheetDialog.cancel();
                }
                break;
            case R.id.bottom_sheet_sign_in:
                signInWithVerificationCode();
                break;
            case R.id.bottom_sheet_forget_psw:
                break;
            default:

        }
    }

    private void signInWithVerificationCode() {
        LOGIN_CODE = Constants.LOGIN_WITH_VERIFICATION_CODE;
        passwordFrom.setVisibility(View.GONE);

    }

    private void cancelSignUp() {
        LOGIN_CODE = Constants.LOGIN_WITH_PASSWORD;
        signUpForm.setVisibility(View.GONE);
        signUpTextView.setText(getString(R.string.sign_up));
        forgetTextView.setVisibility(View.VISIBLE);
    }

    private void signUp() {
        LOGIN_CODE = Constants.SIGN_UP;
        signUpForm.setVisibility(View.VISIBLE);
        signUpTextView.setText(getString(R.string.login_with_psw));
        forgetTextView.setVisibility(View.INVISIBLE);
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
