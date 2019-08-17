package com.jeremyliao.android.scaffold.robolectric.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.base.helper.RxSchedulersHelper;
import com.jeremyliao.android.scaffold.base.wrapper.Wrapper;
import com.jeremyliao.android.scaffold.retrofit.api.WanAndroidApi;
import com.jeremyliao.android.scaffold.retrofit.bean.ChaptersResp;
import com.jeremyliao.android.scaffold.retrofit.common.Hosts;
import com.jeremyliao.android.scaffold.retrofit.factory.RetrofitFactory;
import com.jeremyliao.android.scaffold.robolectric.helper.InfoHelper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowToast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by liaohailiang on 2019-07-31.
 */
@RunWith(RobolectricTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "javax.xml.parsers.*",
        "com.sun.org.apache.*", "javax.net.ssl.*"})
@PrepareForTest({InfoHelper.class})
public class UTMainActivityTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    ActivityController<UTMainActivity> controller;

    @BeforeClass
    public static void setUpStatic() {
    }

    @Before
    public void setUp() {
        RxSchedulersHelper.initRxSchedulers();
        controller = Robolectric.buildActivity(UTMainActivity.class).create().start().resume().visible();
    }

//    @Test
//    public void testFail() {
//        assertNotNull(null);
//    }

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

    @Test
    public void testShowMockToast() {
        String msg = "mock message";
        PowerMockito.mockStatic(InfoHelper.class);
        PowerMockito.when(InfoHelper.getToastInfo()).thenReturn(msg);
        UTMainActivity activity = controller.get();
        activity.findViewById(R.id.btn_toast).performClick();
        assertEquals(msg, ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testGetChapters() {
        UTMainActivity activity = controller.get();
        activity.findViewById(R.id.btn_chapters).performClick();
        assertEquals("success", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testChaptersRequest() {
        final Wrapper<ChaptersResp> result = new Wrapper<>(null);
        WanAndroidApi api = RetrofitFactory.get(Hosts.HOST_WANANDROID).create(WanAndroidApi.class);
        api.chapters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChaptersResp>() {
                    @Override
                    public void accept(ChaptersResp chaptersResp) throws Exception {
                        result.setTarget(chaptersResp);
                    }
                });
        assertNotNull(result.getTarget());
    }

    @Test
    public void testMockStatic() {
        String msg = "mock message";
        PowerMockito.mockStatic(InfoHelper.class);
        PowerMockito.when(InfoHelper.getToastInfo()).thenReturn(msg);
        assertEquals(InfoHelper.getToastInfo(), msg);
    }

    @Test
    public void testMockContext() {
        Context context = Mockito.mock(Context.class);
        Mockito.when(context.getApplicationContext()).thenReturn(RuntimeEnvironment.application);
        assertEquals(context.getApplicationContext(), RuntimeEnvironment.application);
    }

    @Test
    public void testMockContextByPowerMock() {
        Context context = PowerMockito.mock(Context.class);
        PowerMockito.when(context.getApplicationContext()).thenReturn(RuntimeEnvironment.application);
        assertEquals(context.getApplicationContext(), RuntimeEnvironment.application);
    }

    @Test
    public void testMockContextFinal() {
        Context context = PowerMockito.mock(Context.class);
        PowerMockito.when(context.getString(Mockito.anyInt())).thenReturn("test");
        assertEquals(context.getString(1), "test");
    }
}