package com.tao.note.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

/**
 * Created by Tao Zhou on 2019/4/15
 * Package name: com.tao.note.ui.base
 */
public abstract class BaseFragmnet extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bindDependencies();
    }

    private void bindDependencies() {
//        ((BaseActivity1) getActivity()).bind(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
    }

    private void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    protected abstract int getFragmentLayout();
}
