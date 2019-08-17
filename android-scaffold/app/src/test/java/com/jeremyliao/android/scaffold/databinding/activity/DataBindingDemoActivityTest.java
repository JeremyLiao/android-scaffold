package com.jeremyliao.android.scaffold.databinding.activity;

import android.widget.TextView;

import com.jeremyliao.android.scaffold.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by liaohailiang on 2019-07-30.
 */
@RunWith(RobolectricTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "javax.xml.parsers.*",
        "com.sun.org.apache.*", "javax.net.ssl.*", "*.databinding.*"})
public class DataBindingDemoActivityTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    ActivityController<DataBindingDemoActivity> controller;

    @Before
    public void setUp() {
        controller = Robolectric.buildActivity(DataBindingDemoActivity.class).create().start().resume().visible();
    }

    @Test
    public void testInitText() {
        DataBindingDemoActivity activity = controller.get();
        TextView view = activity.findViewById(R.id.tv_title);
        assertEquals(view.getText().toString(), "hello world");
    }

    @Test
    public void testChangeValue() {
        DataBindingDemoActivity activity = controller.get();
        activity.findViewById(R.id.btn_change_value).performClick();
        TextView view = activity.findViewById(R.id.tv_title);
        assertTrue(view.getText().toString().startsWith("Value"));
    }

    @Test
    public void testChangeName() {
        DataBindingDemoActivity activity = controller.get();
        activity.findViewById(R.id.btn_change_value).performClick();
        assertTrue(activity.name.startsWith("Value"));
    }
}