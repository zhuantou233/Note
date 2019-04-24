package com.tao.note.ui.login.bottomdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.BottomSheetDialogResetBinding;
import com.tao.note.ui.base.BaseDialog;
import com.tao.note.ui.login.signinwithcode.SignInWithCodeFragment;
import com.tao.note.utils.ToastUtil;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Tao Zhou on 2019/4/24
 * Package name: com.tao.note.ui.login.bottomdialog
 */
public class ResetDialog extends BaseDialog implements ResetCallback {

    private static final String TAG = ResetDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private ResetViewModel mResetViewModel;

    public static ResetDialog newInstance() {
        ResetDialog fragment = new ResetDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BottomSheetDialogResetBinding binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_dialog_reset, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mResetViewModel = ViewModelProviders.of(this, factory).get(ResetViewModel.class);
        binding.setViewModel(mResetViewModel);

        mResetViewModel.setNavigator(this);

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Override
    public void showSignInWithCodeFragment() {
        if (getFragmentManager() != null &&
                getFragmentManager().findFragmentByTag(SignInWithCodeFragment.TAG) == null) {
            getFragmentManager()
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .add(R.id.root_view, SignInWithCodeFragment.newInstance(), SignInWithCodeFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void showResetPasswordFragment() {
        ToastUtil.getInstance(getContext()).shortToast("ResetPassword");
    }
}
