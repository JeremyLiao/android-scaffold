package com.jeremyliao.android.scaffold.databinding.observable;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by liaohailiang on 2019-04-20.
 */
public class ObservableViewModel extends ViewModel {

    public final MutableLiveData<String> name = new MutableLiveData<>();
}
