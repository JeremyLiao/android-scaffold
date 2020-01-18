package com.jeremyliao.android.scaffold.news.modules.newsmain;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by liaohailiang on 2019-04-20.
 */
public class NewsMainViewModel extends ViewModel {

    public final MutableLiveData<String> name = new MutableLiveData<>();
}
