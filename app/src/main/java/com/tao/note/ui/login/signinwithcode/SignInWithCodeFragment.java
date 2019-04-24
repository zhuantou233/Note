package com.tao.note.ui.login.signinwithcode;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.FragmentSignInWithCodeBinding;
import com.tao.note.databinding.FragmentSignUpBinding;
import com.tao.note.ui.base.BaseFragment;
import com.tao.note.ui.custom.MyCountDownTimer;
import com.tao.note.ui.login.signup.SignUpViewModel;
import com.tao.note.utils.ToastUtil;
import com.tao.note.utils.Util;

import javax.inject.Inject;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signinwithcode
 */

public class SignInWithCodeFragment extends BaseFragment<FragmentSignInWithCodeBinding, SignInWithCodeViewModel> implements SignInWithCodeNavigator {

    public static final String TAG = SignInWithCodeFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private SignInWithCodeViewModel mSignInWithCodeViewModel;
    private FragmentSignInWithCodeBinding mFragmentSignInWithCodeBinding;
    private TextInputEditText phoneView;
    private TextInputEditText codeView;
    private CircularProgressButton signInBtn;

    public static SignInWithCodeFragment newInstance() {
        Bundle args = new Bundle();
        SignInWithCodeFragment fragment = new SignInWithCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sign_in_with_code;
    }

    @Override
    public SignInWithCodeViewModel getViewModel() {
        mSignInWithCodeViewModel = ViewModelProviders.of(this, factory).get(SignInWithCodeViewModel.class);
        return mSignInWithCodeViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignInWithCodeViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentSignInWithCodeBinding = getViewDataBinding();
        phoneView = mFragmentSignInWithCodeBinding.userPhoneNumber;
        codeView = mFragmentSignInWithCodeBinding.userVerificationCode;
        signInBtn = mFragmentSignInWithCodeBinding.phoneSignInButton;
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
                mFragmentSignInWithCodeBinding.getVerificationCodeBtn).start();
        if (!mSignInWithCodeViewModel.requestVerCode(phone)) {
            ToastUtil.getInstance(getContext()).shortToast(getString(R.string.get_code_failed));
        }
    }

    @Override
    public void signIn() {
        String phone = phoneView.getText().toString();
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
        if (!Util.isCodeFormatValid(code)) {
            codeView.setError(getString(R.string.code_can_not_be_null));
            codeView.requestFocus();
            return;
        }
        setInputStatus(false);
        signInBtn.startAnimation(() -> null);
        if (!mSignInWithCodeViewModel.signIn(phone, code)) {
            ToastUtil.getInstance(getContext()).shortToast(getString(R.string.sign_in_failed));
            signInBtn.revertAnimation(() -> {
                signInBtn.setText(getString(R.string.sign_in));
                return null;
            });
        } else {
            signInBtn.doneLoadingAnimation(
                    R.color.colorTheme,
                    BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
            openMainActivity();
        }
        setInputStatus(true);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openMainActivity() {

    }

    private void setInputStatus(boolean flag) {
        phoneView.setFocusable(flag);
        phoneView.setFocusableInTouchMode(flag);
        codeView.setFocusable(flag);
        codeView.setFocusableInTouchMode(flag);
    }
}