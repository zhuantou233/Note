package com.tao.note.ui.login.signin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.FragmentSignInBinding;
import com.tao.note.ui.base.BaseFragment;
import com.tao.note.ui.login.signup.SignUpFragment;

import javax.inject.Inject;

/**
 * Created by Tao Zhou on 2019/4/23
 * Package name: com.tao.note.ui.login.signin
 */
public class SignInFragment extends BaseFragment<FragmentSignInBinding, SignInViewModel> implements SignInNavigator {

    public static final String TAG = SignInFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private SignInViewModel mSignInViewModel;

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
    public void login() {

    }

    @Override
    public void showSignUpFragment() {
        if (getFragmentManager() != null) {
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

    }
}
