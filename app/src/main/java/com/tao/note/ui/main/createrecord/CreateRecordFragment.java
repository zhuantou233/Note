package com.tao.note.ui.main.createrecord;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.tao.note.BR;
import com.tao.note.R;
import com.tao.note.ViewModelProviderFactory;
import com.tao.note.databinding.FragmentCreateRecordBinding;
import com.tao.note.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Tao Zhou on 2019/5/6
 * Package name: com.tao.note.ui.main.createrecord
 */
public class CreateRecordFragment extends BaseFragment<FragmentCreateRecordBinding, CreateRecordViewModel> implements CreateRecordNavigator {

    public static final String TAG = CreateRecordFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private CreateRecordViewModel mCreateRecordViewModel;
    private FragmentCreateRecordBinding mFragmentCreateRecordBinding;
    private Toolbar mToolbar;

    public static CreateRecordFragment newInstance() {
        Bundle args = new Bundle();
        CreateRecordFragment fragment = new CreateRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_create_record;
    }

    @Override
    public CreateRecordViewModel getViewModel() {
        mCreateRecordViewModel = ViewModelProviders.of(this, factory).get(CreateRecordViewModel.class);
        return mCreateRecordViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCreateRecordViewModel.setNavigator(this);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentCreateRecordBinding = getViewDataBinding();
        setUp();

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    private void setUp() {
        mToolbar = mFragmentCreateRecordBinding.toolbar1;
        getBaseActivity().setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);
    }


}
