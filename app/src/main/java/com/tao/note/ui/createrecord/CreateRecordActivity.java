package com.tao.note.ui.createrecord;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;
import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.ActivityCreateRecordBinding;
import com.tao.note.ui.base.BaseActivity;
import com.tao.note.utils.AppConstants;
import com.tao.note.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Tao Zhou on 2019/5/6
 * Package name: com.tao.note.ui.createrecord
 */
public class CreateRecordActivity extends BaseActivity<ActivityCreateRecordBinding, CreateRecordViewModel>
        implements CreateRecordNavigator, HasSupportFragmentInjector, SwitchDateTimeDialogFragment.OnButtonClickListener {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private CreateRecordViewModel mCreateRecordViewModel;
    private ActivityCreateRecordBinding mActivityCreateRecordBinding;
    private Toolbar mToolbar;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CreateRecordActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_record;
    }

    @Override
    public CreateRecordViewModel getViewModel() {
        mCreateRecordViewModel = ViewModelProviders.of(this, factory).get(CreateRecordViewModel.class);
        return mCreateRecordViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCreateRecordBinding = getViewDataBinding();
        mCreateRecordViewModel.setNavigator(this);
        setUp();

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void showIncomeOrExpenseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.income_or_expense);
        builder.setItems(AppConstants.INCOME_OR_EXPENSE, (dialog, which) ->
                mCreateRecordViewModel.setIsIncomeOrExpense(which == 0));
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showMoneyTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.money_type);
        builder.setItems(AppConstants.MONEY_TYPE_LIST, (dialog, which) ->
                mCreateRecordViewModel.setMoneyType(AppConstants.MONEY_TYPE_LIST[which]));
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showRecordTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.record_type);
        builder.setItems(AppConstants.RECORD_TYPE_LIST, (dialog, which) ->
                mCreateRecordViewModel.setRecordType(AppConstants.RECORD_TYPE_LIST[which]));
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showRecordDateDialog() {
        SwitchDateTimeDialogFragment dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
                getString(R.string.record_date),
                getString(R.string.ok),
                getString(R.string.cancel)
        );
        dateTimeDialogFragment.startAtCalendarView();
        dateTimeDialogFragment.set24HoursMode(true);
        dateTimeDialogFragment.setDefaultDateTime(new Date());

        try {
            dateTimeDialogFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("dd MMMM", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
            Log.e("Note", e.getMessage());
        }
        dateTimeDialogFragment.setOnButtonClickListener(this);
        dateTimeDialogFragment.show(getSupportFragmentManager(), SwitchDateTimeDialogFragment.class.getSimpleName());
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    private void setUp() {
        mCreateRecordViewModel.onDataLoad();
        mToolbar = mActivityCreateRecordBinding.toolbar;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }

    }


    @Override
    public void onPositiveButtonClick(Date date) {
        mCreateRecordViewModel.setRecordDate(date);
    }

    @Override
    public void onNegativeButtonClick(Date date) {

    }
}
