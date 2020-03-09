package com.jeremyliao.android.scaffold.databinding.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by liaohailiang on 2019-04-20.
 */
public class LiveDataViewModel extends ViewModel {

    public final MutableLiveData<Boolean> booleanLiveData = new MutableLiveData<>();
    public final MutableLiveData<String> stringLiveData = new MutableLiveData<>();
}
