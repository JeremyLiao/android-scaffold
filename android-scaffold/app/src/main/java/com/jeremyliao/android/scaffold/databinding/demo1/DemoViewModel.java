package com.jeremyliao.android.scaffold.databinding.demo1;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by liaohailiang on 2019-04-20.
 */
public class DemoViewModel extends ViewModel {

    public final MutableLiveData<String> name = new MutableLiveData<>();
}
