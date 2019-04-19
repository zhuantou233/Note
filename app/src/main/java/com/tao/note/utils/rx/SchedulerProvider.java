package com.tao.note.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Tao Zhou on 2019/4/18
 * Package name: com.tao.note.utils.rx
 */
public interface SchedulerProvider {
    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
