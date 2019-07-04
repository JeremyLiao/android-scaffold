package com.jeremyliao.android.scaffold.databinding.vmshare;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by liaohailiang on 2019-04-20.
 */
public class DataShareViewModel extends ViewModel {

    public final MutableLiveData<String> name = new MutableLiveData<>();
    public String data;
}
