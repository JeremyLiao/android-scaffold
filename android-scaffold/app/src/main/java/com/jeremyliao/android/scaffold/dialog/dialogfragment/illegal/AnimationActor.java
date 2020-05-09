package com.jeremyliao.android.scaffold.dialog.dialogfragment.illegal;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by liaohailiang on 2020-04-24.
 */
public class AnimationActor implements Runnable {

    private int count = 0;
    private final int limit;
    private final long delay;
    private Handler handler = new Handler(Looper.getMainLooper());

    public AnimationActor(int limit, long delay) {
        this.limit = limit;
        this.delay = delay;
    }

    @Override
    public void run() {
        onAnimation(count);
        count++;
        if (count < limit && !shouldStop()) {
            handler.postDelayed(this, delay);
        } else {
            onFinished();
        }
    }

    public void start() {
        onStart();
        handler.postDelayed(this, delay);
    }

    protected boolean shouldStop() {
        return false;
    }

    protected void onAnimation(int currentTimes) {

    }

    protected void onStart() {

    }

    protected void onFinished() {

    }
}
