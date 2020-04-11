package com.jeremyliao.android.scaffold.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;

/**
 *
 */
public final class CloneUtils {

    private static final Gson DEFAULT_GSON = new Gson();

    /**
     * @param input
     * @param <T>
     * @return
     */
    public static <T extends Parcelable> T deepCloneParcelable(T input) {
        if (input == null) {
            return null;
        }
        Parcel parcel = null;
        try {
            parcel = Parcel.obtain();
            parcel.writeParcelable(input, 0);
            parcel.setDataPosition(0);
            return parcel.readParcelable(input.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
            return input;
        } finally {
            if (parcel != null) {
                parcel.recycle();
            }
        }
    }

    /**
     * @param obj
     * @param <T>
     * @return
     */
    public static <T extends Serializable> T deepCloneSerializable(T obj) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream obs = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bos = new ByteArrayOutputStream();
            obs = new ObjectOutputStream(bos);
            obs.writeObject(obj);

            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(bos, obs, bis, ois);
        }
    }

    /**
     * @param input
     * @param <T>
     * @return
     */
    public static <T> T deepCloneBean(T input) {
        if (input == null) {
            return null;
        }
        try {
            return (T) DEFAULT_GSON.fromJson(DEFAULT_GSON.toJson(input), input.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param data
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T deepCloneBean(final T data, final Type type) {
        try {
            return DEFAULT_GSON.fromJson(DEFAULT_GSON.toJson(data), type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
