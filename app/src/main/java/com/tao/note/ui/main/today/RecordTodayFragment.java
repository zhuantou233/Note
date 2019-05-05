package com.tao.note.ui.main.today;

import com.tao.note.databinding.FragmentRecordTodayBinding;
import com.tao.note.ui.base.BaseFragment;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.main
 */
public class RecordTodayFragment extends BaseFragment<FragmentRecordTodayBinding, RecordTodayViewModel> implements RecordTodayNavigator {
    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public RecordTodayViewModel getViewModel() {
        return null;
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}
