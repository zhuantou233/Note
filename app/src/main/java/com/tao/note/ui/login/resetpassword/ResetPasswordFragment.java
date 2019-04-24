package com.tao.note.ui.login.resetpassword;

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
import com.tao.note.databinding.FragmentResetPasswordBinding;
import com.tao.note.ui.base.BaseFragment;
import com.tao.note.ui.custom.MyCountDownTimer;
import com.tao.note.ui.custom.MyPasswordWatcher;
import com.tao.note.utils.ToastUtil;
import com.tao.note.utils.Util;

import javax.inject.Inject;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.resetpassword
 */
public class ResetPasswordFragment extends BaseFragment<FragmentResetPasswordBinding, ResetPasswordViewModel> implements ResetPasswordNavigator {

    public static final String TAG = ResetPasswordFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ResetPasswordViewModel mResetPasswordViewModel;
    private FragmentResetPasswordBinding mFragmentResetPasswordBinding;
    private TextInputEditText phoneView;
    private TextInputEditText passwordView;
    private TextInputEditText passwordConfirmView;
    private TextInputEditText codeView;
    private CircularProgressButton confirmBtn;

    public static ResetPasswordFragment newInstance() {
        Bundle args = new Bundle();
        ResetPasswordFragment fragment = new ResetPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_reset_password;
    }

    @Override
    public ResetPasswordViewModel getViewModel() {
        mResetPasswordViewModel = ViewModelProviders.of(this, factory).get(ResetPasswordViewModel.class);
        return mResetPasswordViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResetPasswordViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentResetPasswordBinding = getViewDataBinding();
        phoneView = mFragmentResetPasswordBinding.userPhoneNumber;
        passwordView = mFragmentResetPasswordBinding.userPassword;
        passwordConfirmView = mFragmentResetPasswordBinding.userPasswordConfirm;
        codeView = mFragmentResetPasswordBinding.userVerificationCode;
        TextInputLayout passwordFirst = mFragmentResetPasswordBinding.inputLayoutFirst;
        TextInputLayout passwordSecond = mFragmentResetPasswordBinding.inputLayoutSecond;
        passwordView.addTextChangedListener(new MyPasswordWatcher(passwordFirst));
        passwordConfirmView.addTextChangedListener(new MyPasswordWatcher(passwordSecond));
        confirmBtn = mFragmentResetPasswordBinding.confirmResetButton;
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
                mFragmentResetPasswordBinding.getVerificationCodeBtn).start();
        if (!mResetPasswordViewModel.requestVerCode(phone)) {
            ToastUtil.getInstance(getContext()).shortToast(getString(R.string.get_code_failed));
        }
    }

    @Override
    public void confirmReset() {
        String phone = phoneView.getText().toString();
        String password = passwordView.getText().toString();
        String passwordRepeat = passwordConfirmView.getText().toString();
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
        if (!Util.isPasswordFormatValid(passwordRepeat)) {
            passwordConfirmView.setError(getString(R.string.psw_can_not_be_null));
            passwordConfirmView.requestFocus();
            return;
        }
        if (!password.equals(passwordRepeat)) {
            ToastUtil.getInstance(getContext()).longToast(getString(R.string.psw_is_different));
            passwordConfirmView.requestFocus();
            return;
        }
        if (!Util.isCodeFormatValid(code)) {
            codeView.setError(getString(R.string.code_can_not_be_null));
            codeView.requestFocus();
            return;
        }
        setInputStatus(false);
        confirmBtn.startAnimation(() -> null);
        if (!mResetPasswordViewModel.confirmReset(phone, password, code)) {
            ToastUtil.getInstance(getContext()).shortToast(getString(R.string.reset_password_fail));
            confirmBtn.revertAnimation(() -> {
                confirmBtn.setText(getString(R.string.reset_password));
                return null;
            });
        } else {
            confirmBtn.doneLoadingAnimation(
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
        passwordView.setFocusable(flag);
        passwordView.setFocusableInTouchMode(flag);
        passwordConfirmView.setFocusable(flag);
        passwordConfirmView.setFocusableInTouchMode(flag);
        codeView.setFocusable(flag);
        codeView.setFocusableInTouchMode(flag);
    }
}
