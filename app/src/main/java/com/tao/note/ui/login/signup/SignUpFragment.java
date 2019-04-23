package com.tao.note.ui.login.signup;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.FragmentSignUpBinding;
import com.tao.note.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signup
 */
public class SignUpFragment extends BaseFragment<FragmentSignUpBinding, SignUpViewModel> implements SignUpNavigator {

    public static final String TAG = SignUpFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private SignUpViewModel mSignUpViewModel;

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
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }


}
