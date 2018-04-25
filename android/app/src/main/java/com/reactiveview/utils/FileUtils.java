package com.reactiveview.utils;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okhttp3.ResponseBody;

public class FileUtils {

  private static final String TAG = FileUtils.class.getSimpleName();

  public static boolean writeResponseBodyToDisk(ResponseBody body, String filePath) {
    try {
      InputStream inputStream = null;
      OutputStream outputStream = null;
      try {
        byte[] fileReader = new byte[1024];
        long fileSize = body.contentLength();
        long fileSizeDownloaded = 0;
        inputStream = body.byteStream();
        outputStream = new FileOutputStream(filePath);
        while (true) {
          int read = inputStream.read(fileReader);
          if (read == -1) {
            break;
          }
          outputStream.write(fileReader, 0, read);
          fileSizeDownloaded += read;
          Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
        }
        outputStream.flush();
        return true;
      } catch (IOException e) {
        return false;
      } finally {
        if (inputStream != null) {
          inputStream.close();
        }

        if (outputStream != null) {
          outputStream.close();
        }
      }
    } catch (IOException e) {
      return false;
    }
  }

}
