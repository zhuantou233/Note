package com.tao.note.ui.accountdetail.changepassword;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.DialogChangeUserPasswordBinding;
import com.tao.note.ui.base.BaseDialog;
import com.tao.note.ui.custom.MyPasswordWatcher;
import com.tao.note.utils.ToastUtil;
import com.tao.note.utils.Util;

import java.net.UnknownHostException;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.accountdetail.changepassword
 */
public class ChangeUserPasswordDialog extends BaseDialog implements ChangeUserPasswordCallback {

    private static final String TAG = ChangeUserPasswordDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private ChangeUserPasswordViewModel mChangeUserPasswordViewModel;
    private DialogChangeUserPasswordBinding mDialogChangeUserPasswordBinding;
    private TextInputEditText oldPsw;
    private TextInputEditText newPsw;

    public static ChangeUserPasswordDialog newInstance() {
        ChangeUserPasswordDialog fragment = new ChangeUserPasswordDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        return dialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDialogChangeUserPasswordBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_change_user_password, container, false);
        View view = mDialogChangeUserPasswordBinding.getRoot();

        AndroidSupportInjection.inject(this);
        mChangeUserPasswordViewModel = ViewModelProviders.of(getBaseActivity(), factory).get(ChangeUserPasswordViewModel.class);
        mDialogChangeUserPasswordBinding.setViewModel(mChangeUserPasswordViewModel);

        mChangeUserPasswordViewModel.setNavigator(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputLayout oldLayout = mDialogChangeUserPasswordBinding.inputLayoutOld;
        oldPsw = mDialogChangeUserPasswordBinding.oldPassword;
        oldPsw.addTextChangedListener(new MyPasswordWatcher(oldLayout));

        TextInputLayout newLayout = mDialogChangeUserPasswordBinding.inputLayoutNew;
        newPsw = mDialogChangeUserPasswordBinding.newPassword;
        newPsw.addTextChangedListener(new MyPasswordWatcher(newLayout));
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Override
    public void handleError(Throwable throwable) {
        if (throwable.getCause() instanceof UnknownHostException) {
            ToastUtil.getInstance(getContext()).shortToast(getString(R.string.no_network));
        } else {
            ToastUtil.getInstance(getContext()).shortToast(throwable.getMessage());
        }
    }

    @Override
    public void confirmReset() {
        String oldPassword = Objects.requireNonNull(oldPsw.getText()).toString();
        String newPassword = Objects.requireNonNull(newPsw.getText()).toString();

        if (!isNetworkConnected()) {
            ToastUtil.getInstance(getContext()).longToast(getString(R.string.no_network));
            return;
        }
        if (!Util.isPasswordFormatValid(oldPassword)) {
            oldPsw.setError(getString(R.string.psw_can_not_be_null));
            oldPsw.requestFocus();
            return;
        }
        if (!Util.isPasswordFormatValid(newPassword)) {
            newPsw.setError(getString(R.string.psw_can_not_be_null));
            newPsw.requestFocus();
            return;
        }
        mChangeUserPasswordViewModel.confirmReset(oldPassword, newPassword);
    }

}
