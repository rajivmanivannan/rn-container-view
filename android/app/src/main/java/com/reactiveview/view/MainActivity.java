package com.reactiveview.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.reactiveview.R;
import com.reactiveview.network.FileDownloadService;
import com.reactiveview.network.ServiceGenerator;
import com.reactiveview.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.download_js_bundle_btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        showToast(getString(R.string.downloading_js_bundle_from_server));
        downloadJSBundle();
      }
    });

    findViewById(R.id.download_go_to_detail_btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, DetailActivity.class));
      }
    });
  }

  // Download JS Bundle
  private void downloadJSBundle() {
    FileDownloadService downloadService = ServiceGenerator.createService(FileDownloadService.class);
    Call<ResponseBody> call = downloadService.downloadJsBundle();
    call.enqueue(new Callback<ResponseBody>() {
      @Override public void onResponse(@NonNull Call<ResponseBody> call,
          @NonNull Response<ResponseBody> response) {
        if (response.isSuccessful()) {
          storeJSBundleLocally(response);
        }
      }

      @Override
      public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        showToast(throwable.getMessage());
      }
    });
  }

  // Store JS Bundle in local file directory
  private void storeJSBundleLocally(Response<ResponseBody> response) {
    Log.d(TAG, "Server connected and has file");
    boolean writtenToDisk;
    if (response.body() != null) {
      try {
        File file = new File(getFilesDir(), "/index.android.bundle");
        if (file.exists()) {
          file.delete();
        } else {
          file.createNewFile();
        }
        Log.d(TAG, "Path " + file.getPath());
        writtenToDisk = FileUtils.writeResponseBodyToDisk(response.body(), file.getPath());
        Log.d(TAG, "File download was a success? " + writtenToDisk);
        if (writtenToDisk) {
          showToast(getString(R.string.js_bundle_download_completed));
        }
      } catch (IOException e) {
        Log.e(TAG, e.getMessage());
      }
    }
  }

  private void showToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }
}