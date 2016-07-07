package com.waracle.androidtest;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Riad on 20/05/2015.
 */
public class StreamUtils {

    private static final String TAG = StreamUtils.class.getSimpleName();

    // Can you see what's wrong with this???
    public static byte[] readUnknownFully(InputStream stream) throws IOException {
        // Read in stream of bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int readByte;
        while ((readByte = stream.read()) != -1) {
            outputStream.write(readByte);
        }
        return outputStream.toByteArray();
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}
