package com.reactiveview.network;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FileDownloadService {
  @GET("/apps/downloads/index.android.bundle")
  Call<ResponseBody> downloadJsBundle();
}
