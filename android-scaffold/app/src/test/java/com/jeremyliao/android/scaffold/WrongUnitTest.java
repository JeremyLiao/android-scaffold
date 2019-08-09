package com.jeremyliao.android.scaffold;

import android.app.Activity;
import android.app.Application;

import com.jeremyliao.android.scaffold.robolectric.activity.UTMainActivity;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by liaohailiang on 2019-07-30.
 */
public class WrongUnitTest {

    @Ignore
    @Test
    public void testWrongCase1() {
        Activity activity = new Activity();
        Application application = activity.getApplication();
        assertNotNull(application);
    }

    @Ignore
    @Test
    public void testWrongCase2() {
        UTMainActivity activity = new UTMainActivity();
        activity.findViewById(R.id.btn_login).performClick();
        assertNotNull(activity);
    }
}
