package com.reactiveview.network;

    import okhttp3.OkHttpClient;
    import retrofit2.Retrofit;

public class ServiceGenerator {
  private static final String BASE_URL = "https://tarkalabs.com/";
  private static Retrofit.Builder builder =
      new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .client(new OkHttpClient());
  private static Retrofit retrofit = builder.build();

  public static <S> S createService(Class<S> serviceClass) {
    return retrofit.create(serviceClass);
  }
}
