package com.tao.note.ui.login.signup;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.FragmentSignUpBinding;
import com.tao.note.ui.base.BaseFragment;
import com.tao.note.ui.custom.MyCountDownTimer;
import com.tao.note.ui.custom.MyPasswordWatcher;
import com.tao.note.ui.main.MainActivity;
import com.tao.note.utils.ToastUtil;
import com.tao.note.utils.Util;

import javax.inject.Inject;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signup
 */
public class SignUpFragment extends BaseFragment<FragmentSignUpBinding, SignUpViewModel> implements SignUpNavigator {

    public static final String TAG = SignUpFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private SignUpViewModel mSignUpViewModel;
    private FragmentSignUpBinding mFragmentSignUpBinding;
    private TextInputEditText phoneView;
    private TextInputEditText passwordView;
    private TextInputEditText codeView;
    private CircularProgressButton signUpBtn;

    public static SignUpFragment newInstance() {
        Bundle args = new Bundle();
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public SignUpViewModel getViewModel() {
        mSignUpViewModel = ViewModelProviders.of(this, factory).get(SignUpViewModel.class);
        return mSignUpViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignUpViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentSignUpBinding = getViewDataBinding();
        phoneView = mFragmentSignUpBinding.userPhoneNumber;
        passwordView = mFragmentSignUpBinding.userPassword;
        codeView = mFragmentSignUpBinding.userVerificationCode;
        TextInputLayout passwordTI = mFragmentSignUpBinding.inputLayout;
        passwordView.addTextChangedListener(new MyPasswordWatcher(passwordTI));
        signUpBtn = mFragmentSignUpBinding.phoneSignUpButton;
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void requestVerCode() {
        String phone = phoneView.getText().toString();
        if (!isNetworkConnected()) {
            ToastUtil.getInstance(getContext()).longToast(getString(R.string.no_network));
            return;
        }
        if (!Util.isPhoneFormatValid(phone)) {
            phoneView.setError(getString(R.string.phone_invalidate));
            phoneView.requestFocus();
            return;
        }
        new MyCountDownTimer(
                60000,
                1000,
                mFragmentSignUpBinding.getVerificationCodeBtn).start();
        mSignUpViewModel.requestVerCode(phone);
    }

    @Override
    public void signUp() {
        String phone = phoneView.getText().toString();
        String password = passwordView.getText().toString();
        String code = codeView.getText().toString();
        if (!isNetworkConnected()) {
            ToastUtil.getInstance(getContext()).longToast(getString(R.string.no_network));
            return;
        }
        if (!Util.isPhoneFormatValid(phone)) {
            phoneView.setError(getString(R.string.phone_invalidate));
            phoneView.requestFocus();
            return;
        }
        if (!Util.isPasswordFormatValid(password)) {
            passwordView.setError(getString(R.string.psw_can_not_be_null));
            passwordView.requestFocus();
            return;
        }
        if (!Util.isCodeFormatValid(code)) {
            codeView.setError(getString(R.string.code_can_not_be_null));
            codeView.requestFocus();
            return;
        }
        setInputStatus(false);
        signUpBtn.startAnimation(() -> null);
        mSignUpViewModel.signUp(phone, password, code);
    }

    @Override
    public void handleError(Throwable throwable) {
        setInputStatus(true);
        ToastUtil.getInstance(getContext()).shortToast(getString(R.string.sign_up_fail));
        signUpBtn.revertAnimation(() -> {
            signUpBtn.setText(getString(R.string.sign_up_now));
            return null;
        });
    }

    @Override
    public void openMainActivity() {
        signUpBtn.doneLoadingAnimation(
                R.color.colorTheme,
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
        Intent intent = MainActivity.newIntent(getContext());
        startActivity(intent);
        getActivity().finish();
    }

    private void setInputStatus(boolean flag) {
        phoneView.setFocusable(flag);
        phoneView.setFocusableInTouchMode(flag);
        passwordView.setFocusable(flag);
        passwordView.setFocusableInTouchMode(flag);
        codeView.setFocusable(flag);
        codeView.setFocusableInTouchMode(flag);
    }
}
