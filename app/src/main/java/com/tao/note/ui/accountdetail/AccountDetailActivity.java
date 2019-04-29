package com.tao.note.ui.accountdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.ActivityAccountDetailBinding;
import com.tao.note.ui.accountdetail.changeusername.ChangeUserNameDialog;
import com.tao.note.ui.accountdetail.changeuserphonenumber.ChangeUserPhoneNumberDialog;
import com.tao.note.ui.base.BaseActivity;
import com.tao.note.utils.ToastUtil;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


/**
 * Created by Tao Zhou on 2019/4/28
 * Package name: com.tao.note.ui.accountdetail
 */
public class AccountDetailActivity extends BaseActivity<ActivityAccountDetailBinding, AccountDetailViewModel> implements AccountDetailNavigator, HasSupportFragmentInjector, View.OnClickListener {


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private AccountDetailViewModel mAccountDetailViewModel;
    private ActivityAccountDetailBinding mActivityAccountDetailBinding;
    private Toolbar mToolbar;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AccountDetailActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_detail;
    }

    @Override
    public AccountDetailViewModel getViewModel() {
        mAccountDetailViewModel = ViewModelProviders.of(this, factory).get(AccountDetailViewModel.class);
        return mAccountDetailViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAccountDetailBinding = getViewDataBinding();
        mAccountDetailViewModel.setNavigator(this);
        setUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void handleError(Throwable throwable) {
        ToastUtil.getInstance(this).shortToast(getString(R.string.update_fail));
    }


    @Override
    public void dismissDialog() {
        ChangeUserNameDialog.newInstance().dismissDialog();
        ChangeUserPhoneNumberDialog.newInstance().dismissDialog();
    }

    private void setUp() {
        mAccountDetailViewModel.onDataLoad();
        mToolbar = mActivityAccountDetailBinding.toolbar;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        LinearLayout avatarForm = mActivityAccountDetailBinding.avatarForm;
        LinearLayout nameForm = mActivityAccountDetailBinding.nameForm;
        LinearLayout phoneForm = mActivityAccountDetailBinding.phoneForm;
        LinearLayout passwordForm = mActivityAccountDetailBinding.passwordForm;
        LinearLayout deleteAccountForm = mActivityAccountDetailBinding.deleteAccountForm;
        avatarForm.setOnClickListener(this);
        nameForm.setOnClickListener(this);
        phoneForm.setOnClickListener(this);
        passwordForm.setOnClickListener(this);
        deleteAccountForm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar_form:
                showAvatarDialog();
                break;
            case R.id.name_form:
                ChangeUserNameDialog.newInstance().show(getSupportFragmentManager());
                break;
            case R.id.phone_form:
                ChangeUserPhoneNumberDialog.newInstance().show(getSupportFragmentManager());
                break;
            case R.id.password_form:
                break;
            case R.id.delete_account_form:
                break;
        }
    }

    private void showAvatarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(R.array.avatar, (dialog, which) -> {
            if (which == 0) {
                chooseFromPhoto();
            } else {
                chooseFromGallery();
            }
        });
        builder.create().show();
    }

    private void chooseFromGallery() {

    }

    private void chooseFromPhoto() {

    }
}

