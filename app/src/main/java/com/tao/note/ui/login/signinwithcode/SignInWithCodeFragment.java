package com.tao.note.ui.login.signinwithcode;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.FragmentSignInWithCodeBinding;
import com.tao.note.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signinwithcode
 */

public class SignInWithCodeFragment extends BaseFragment<FragmentSignInWithCodeBinding, SignInWithCodeViewModel> implements SignInWithCodeNavigator {

    public static final String TAG = SignInWithCodeFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private SignInWithCodeViewModel mSignInWithCodeViewModel;

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
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }
}