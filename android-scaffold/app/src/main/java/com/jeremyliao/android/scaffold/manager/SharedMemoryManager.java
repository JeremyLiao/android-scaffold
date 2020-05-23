package com.jeremyliao.android.scaffold.manager;

import android.annotation.SuppressLint;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.os.SharedMemory;

import com.jeremyliao.android.scaffold.utils.ByteUtils;
import com.jeremyliao.android.scaffold.utils.CloseUtils;
import com.jeremyliao.android.scaffold.utils.ConvertUtils;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by liaohailiang on 2020-05-22.
 */
public class SharedMemoryManager {

    private static final String DEFAULT_SHARED_MEMORY_NAME = "default_shared_memory_name";

    public static SharedMemoryManager getManager() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final SharedMemoryManager INSTANCE = new SharedMemoryManager();
    }

    private SharedMemoryManager() {
    }

    public int put(Object object) {
        if (object == null) {
            return -1;
        }
        byte[] bytes = ByteUtils.object2ByteArray(object);
        if (bytes == null) {
            return -1;
        }
        MemoryFile memoryFile = null;
        try {
            memoryFile = new MemoryFile(DEFAULT_SHARED_MEMORY_NAME, bytes.length);
            memoryFile.getOutputStream().write(bytes);
            Method getDeclaredField = MemoryFile.class.getDeclaredMethod("getFileDescriptor");
            getDeclaredField.setAccessible(true);
            FileDescriptor fd = (FileDescriptor) getDeclaredField.invoke(memoryFile);
            Field fDescriptor = FileDescriptor.class.getDeclaredField("descriptor");
            fDescriptor.setAccessible(true);
            return fDescriptor.getInt(fd);
        } catch (Exception e) {
            return -1;
        }
    }

    public Object read(int descriptor) {
        FileDescriptor fd = new FileDescriptor();
        try {
            Field fDescriptor = FileDescriptor.class.getDeclaredField("descriptor");
            fDescriptor.setAccessible(true);
            fDescriptor.set(fd, descriptor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        FileInputStream inputStream = new FileInputStream(fd);
        byte[] bytes = ConvertUtils.inputStream2Bytes(inputStream);
        Object object = ByteUtils.byteArray2Object(bytes);
        CloseUtils.closeIO(inputStream);
        return object;
    }
}
