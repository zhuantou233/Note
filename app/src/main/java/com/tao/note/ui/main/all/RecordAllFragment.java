package com.tao.note.ui.main.all;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.FragmentRecordAllBinding;
import com.tao.note.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.main
 */
public class RecordAllFragment extends BaseFragment<FragmentRecordAllBinding, RecordAllViewModel> implements RecordAllNavigator {

    public static final String TAG = RecordAllFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private RecordAllViewModel mRecordAllViewModel;
    private FragmentRecordAllBinding mFragmentRecordAllBinding;

    public static RecordAllFragment newInstance() {
        Bundle args = new Bundle();
        RecordAllFragment fragment = new RecordAllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_record_all;
    }

    @Override
    public RecordAllViewModel getViewModel() {
        mRecordAllViewModel = ViewModelProviders.of(this, factory).get(RecordAllViewModel.class);
        return mRecordAllViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecordAllViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentRecordAllBinding = getViewDataBinding();
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}
