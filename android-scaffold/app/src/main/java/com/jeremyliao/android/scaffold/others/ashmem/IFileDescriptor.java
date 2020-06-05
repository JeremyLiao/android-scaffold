package com.jeremyliao.android.scaffold.others.ashmem;

import android.os.ParcelFileDescriptor;

/**
 * Created by liaohailiang on 2020-06-04.
 */
public interface IFileDescriptor {

    void put(ParcelFileDescriptor descriptor);

    ParcelFileDescriptor get();
}
