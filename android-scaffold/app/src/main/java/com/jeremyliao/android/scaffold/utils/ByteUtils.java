package com.jeremyliao.android.scaffold.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by liaohailiang on 2020-05-22.
 */
public class ByteUtils {

    public static byte[] object2ByteArray(Object obj) {
        ByteArrayOutputStream bout = null;
        ObjectOutputStream out = null;
        try {
            bout = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bout);
            out.writeObject(obj);
            out.flush();
            return bout.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(bout);
            CloseUtils.closeIO(out);
        }

    }

    public static Object byteArray2Object(byte[] bytes) {
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            bi = new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bi);
            return oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(bi);
            CloseUtils.closeIO(oi);
        }

    }

    public static byte[] byteBuffer2ByteArray(ByteBuffer byteBuffer) {
        byte[] b = new byte[byteBuffer.remaining()];
        byteBuffer.get(b);
        return b;
    }

}
