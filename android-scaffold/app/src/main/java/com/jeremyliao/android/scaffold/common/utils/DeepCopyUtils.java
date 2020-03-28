package com.jeremyliao.android.scaffold.common.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 */
public final class DeepCopyUtils {

    private static final Gson DEFAULT_GSON = new Gson();

    public static <T extends Parcelable> T copyParcelable(T input) {
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

    public static <T extends Serializable> T copySerializable(T obj) {
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
            close(bos);
            close(obs);
            close(bis);
            close(ois);
        }
    }

    public static <T> T copyBean(T input) {
        if (input == null) {
            return null;
        }
        try {
            return (T) DEFAULT_GSON.fromJson(DEFAULT_GSON.toJson(input), input.getClass());
        } catch (Exception e) {
            return null;
        }
    }

    private static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
        }
    }
}
