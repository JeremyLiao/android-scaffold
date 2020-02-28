package com.jeremyliao.android.scaffold.dialog.dialogfragment.utils;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by liaohailiang on 2020-02-18.
 */
public class FragmentDialogUtils {

    private static final String DEFAULT_DIALOG_FRAGMENT_TAG = "default_dialog_fragment_tag";

    public static boolean showDialog(Context activity, DialogFragment dialog) {
        try {
            if (activity instanceof FragmentActivity) {
                FragmentManager manager = ((FragmentActivity) activity).getSupportFragmentManager();
                if (!dialog.isVisible() && !dialog.isAdded()) {
                    dialog.showNow(manager, DEFAULT_DIALOG_FRAGMENT_TAG);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
