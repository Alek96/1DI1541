package com.example.mobile.di;

import com.example.mobile.network.ParcelApiService;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

  private NetworkModule() {
  }

  @Provides
  @Singleton
  public static ParcelApiService provideParcelApiService() {
    return new Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8081/")
        .addConverterFactory(JacksonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(ParcelApiService.class);
  }
}
