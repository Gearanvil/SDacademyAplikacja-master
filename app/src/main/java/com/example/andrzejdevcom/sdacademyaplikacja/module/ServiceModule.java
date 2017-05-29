package com.example.andrzejdevcom.sdacademyaplikacja.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Katarzyna on 2017-05-27.
 */

@Module
//moduł service czyli co chcemy zeby nasz modul mial w sobie
public class ServiceModule {
    //url potrzebny do retrofita
    private String mBaseURL;

    public ServiceModule(String mBaseURL) {
        this.mBaseURL = mBaseURL;
    }

    //provides oznacza co dany modul daje
    @Provides
    @Singleton
    //provide Gson - przekazujemt gssona
    GsonConverterFactory provideGson() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
        //RxJava dzialaa na zasadzie aasyncTaska
        //przekazuje RxJave
    RxJava2CallAdapterFactory provideAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
        //tutaj budujemy naszego retrofita
        //jak widzicie posiada on metody wyzej zadeklarowane czyli GsonConverterFactory, RxJava2CallAdapterFactory
    Retrofit provideRetrofit(GsonConverterFactory gson, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(gson)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl(mBaseURL)
                .build();
    }
}