package com.jeremyliao.android.scaffold;

import android.content.Intent;
import android.widget.TextView;

import com.jeremyliao.android.scaffold.robolectric.activity.LoginActivity;
import com.jeremyliao.android.scaffold.robolectric.activity.UTMainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
public class ExampleUnitTest {

    ActivityController<UTMainActivity> controller;

    @Before
    public void setUp() {
        controller = Robolectric.buildActivity(UTMainActivity.class).create().start().resume().visible();
    }

    @Test
    public void testLaunchMain() {
        UTMainActivity activity = controller.get();
        assertNotNull(activity);
    }

    @Test
    public void testJumpToLogin() {
        UTMainActivity activity = controller.get();
        activity.findViewById(R.id.btn_login).performClick();
        Intent expectedIntent = new Intent(activity, LoginActivity.class);
        Intent actual = Shadows.shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

    @Test
    public void testTitle() {
        UTMainActivity activity = controller.get();
        TextView view = activity.findViewById(R.id.tv_title);
        String text = view.getText().toString();
        assertEquals(text, "unit test");
    }

    @Test
    public void testShowToast() {
        UTMainActivity activity = controller.get();
        activity.findViewById(R.id.btn_toast).performClick();
        assertEquals("hello world", ShadowToast.getTextOfLatestToast());
    }
}