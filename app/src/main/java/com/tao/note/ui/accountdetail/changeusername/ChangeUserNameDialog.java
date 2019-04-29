package com.tao.note.ui.accountdetail.changeusername;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.DialogChangeUserNameBinding;
import com.tao.note.ui.accountdetail.AccountDetailNavigator;
import com.tao.note.ui.accountdetail.AccountDetailViewModel;
import com.tao.note.ui.base.BaseDialog;
import com.tao.note.utils.ToastUtil;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Tao Zhou on 2019/4/29
 * Package name: com.tao.note.ui.accountdetail.changeusername
 */
public class ChangeUserNameDialog extends BaseDialog implements AccountDetailNavigator {

    private static final String TAG = ChangeUserNameDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private AccountDetailViewModel mAccountDetailViewModel;

    public static ChangeUserNameDialog newInstance() {
        ChangeUserNameDialog fragment = new ChangeUserNameDialog();
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
        DialogChangeUserNameBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_change_user_name, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mAccountDetailViewModel = ViewModelProviders.of(getBaseActivity(), factory).get(AccountDetailViewModel.class);
        binding.setViewModel(mAccountDetailViewModel);

        mAccountDetailViewModel.setNavigator(this);

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    public void handleError(Throwable throwable) {
        ToastUtil.getInstance(getContext()).shortToast(getString(R.string.update_fail));
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }
}
