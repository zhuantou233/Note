package com.tao.note.ui.custom;

import android.os.CountDownTimer;
import android.widget.Button;

import com.tao.note.R;

/**
 * Created by Tao Zhou on 2019/4/24
 * Package name: com.tao.note.ui.custom
 */
public class MyCountDownTimer extends CountDownTimer {
    private Button button;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public MyCountDownTimer(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setClickable(false);
        String text = String.valueOf(millisUntilFinished / 1000).concat("s");
        button.setText(text);
    }

    @Override
    public void onFinish() {
        button.setText("获取验证码");
        button.setClickable(true);
    }
}
