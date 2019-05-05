package com.tao.note.ui.main.today;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.FragmentRecordTodayBinding;
import com.tao.note.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.main
 */
public class RecordTodayFragment extends BaseFragment<FragmentRecordTodayBinding, RecordTodayViewModel> implements RecordTodayNavigator {

    public static final String TAG = RecordTodayFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private RecordTodayViewModel mRecordTodayViewModel;
    private FragmentRecordTodayBinding mFragmentRecordTodayBinding;

    public static RecordTodayFragment newInstance() {
        Bundle args = new Bundle();
        RecordTodayFragment fragment = new RecordTodayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_record_today;
    }

    @Override
    public RecordTodayViewModel getViewModel() {
        mRecordTodayViewModel = ViewModelProviders.of(this, factory).get(RecordTodayViewModel.class);
        return mRecordTodayViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecordTodayViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentRecordTodayBinding = getViewDataBinding();

    }

    @Override
    public void handleError(Throwable throwable) {

    }
}
