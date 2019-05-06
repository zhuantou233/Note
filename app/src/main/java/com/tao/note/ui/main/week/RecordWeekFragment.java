package com.tao.note.ui.main.week;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.FragmentRecordWeekBinding;
import com.tao.note.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.main
 */
public class RecordWeekFragment extends BaseFragment<FragmentRecordWeekBinding, RecordWeekViewModel> implements RecordWeekNavigator {

    public static final String TAG = RecordWeekFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private RecordWeekViewModel mRecordWeekViewModel;
    private FragmentRecordWeekBinding mFragmentRecordWeekBinding;

    public static RecordWeekFragment newInstance() {
        Bundle args = new Bundle();
        RecordWeekFragment fragment = new RecordWeekFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_record_week;
    }

    @Override
    public RecordWeekViewModel getViewModel() {
        mRecordWeekViewModel = ViewModelProviders.of(this, factory).get(RecordWeekViewModel.class);
        return mRecordWeekViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecordWeekViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentRecordWeekBinding = getViewDataBinding();

    }

    @Override
    public void handleError(Throwable throwable) {

    }
}
