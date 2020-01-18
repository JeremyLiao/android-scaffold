package com.jeremyliao.android.scaffold.news.modules.newslist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by liaohailiang on 2019-04-20.
 */
public class NewsListViewModel extends ViewModel {

    public final MutableLiveData<String> name = new MutableLiveData<>();
}
