package com.jeremyliao.android.scaffold.manager;

import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;

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

    public ParcelFileDescriptor put(Object object) {
        if (object == null) {
            return null;
        }
        byte[] bytes = ByteUtils.object2ByteArray(object);
        if (bytes == null) {
            return null;
        }
        try {
            MemoryFile memoryFile = new MemoryFile(DEFAULT_SHARED_MEMORY_NAME, bytes.length);
            memoryFile.getOutputStream().write(bytes);
            Method methodGfd = MemoryFile.class.getDeclaredMethod("getFileDescriptor");
            methodGfd.setAccessible(true);
            FileDescriptor fileDescriptor = (FileDescriptor) methodGfd.invoke(memoryFile);
            return ParcelFileDescriptor.dup(fileDescriptor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object read(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            FileInputStream inputStream = new FileInputStream(fileDescriptor);
            byte[] bytes = ConvertUtils.inputStream2Bytes(inputStream);
            Object object = ByteUtils.byteArray2Object(bytes);
            CloseUtils.closeIO(inputStream);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
