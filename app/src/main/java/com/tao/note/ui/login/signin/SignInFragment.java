package com.tao.note.ui.login.signin;

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
import com.tao.note.databinding.FragmentSignInBinding;
import com.tao.note.ui.base.BaseFragment;
import com.tao.note.ui.custom.MyPasswordWatcher;
import com.tao.note.ui.login.bottomdialog.ResetDialog;
import com.tao.note.ui.login.signup.SignUpFragment;
import com.tao.note.utils.ToastUtil;
import com.tao.note.utils.Util;

import javax.inject.Inject;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

/**
 * Created by Tao Zhou on 2019/4/23
 * Package name: com.tao.note.ui.login.signin
 */
public class SignInFragment extends BaseFragment<FragmentSignInBinding, SignInViewModel> implements SignInNavigator {

    public static final String TAG = SignInFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private SignInViewModel mSignInViewModel;
    private FragmentSignInBinding mFragmentSignInBinding;
    private TextInputEditText phoneView;
    private TextInputEditText passwordView;
    private CircularProgressButton signInBtn;

    public static SignInFragment newInstance() {
        Bundle args = new Bundle();
        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sign_in;
    }

    @Override
    public SignInViewModel getViewModel() {
        mSignInViewModel = ViewModelProviders.of(this, factory).get(SignInViewModel.class);
        return mSignInViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignInViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentSignInBinding = getViewDataBinding();
        phoneView = mFragmentSignInBinding.userPhoneNumber;
        passwordView = mFragmentSignInBinding.userPassword;
        TextInputLayout passwordTI = mFragmentSignInBinding.tiInput;
        passwordView.addTextChangedListener(new MyPasswordWatcher(passwordTI));
        signInBtn = mFragmentSignInBinding.phoneSignInButton;
    }

    @Override
    public void signIn() {
        String phone = phoneView.getText().toString();
        String password = passwordView.getText().toString();
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
        setInputStatus(false);
        signInBtn.startAnimation(() -> null);
        if (!mSignInViewModel.signIn(phone, password)) {
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
    public void showSignUpFragment() {
        if (getFragmentManager() != null &&
                getFragmentManager().findFragmentByTag(SignUpFragment.TAG) == null) {
            getFragmentManager()
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .add(R.id.root_view, SignUpFragment.newInstance(), SignUpFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void showBottomSheetDialog() {
        ResetDialog.newInstance().show(getFragmentManager());
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openMainActivity() {
        ToastUtil.getInstance(getContext()).shortToast("登录成功");
    }

    private void setInputStatus(boolean flag) {
        phoneView.setFocusable(flag);
        phoneView.setFocusableInTouchMode(flag);
        passwordView.setFocusable(flag);
        passwordView.setFocusableInTouchMode(flag);
    }
}
