package com.vanhackathon.mybesthelper.api;

import com.vanhackathon.mybesthelper.BuildConfig;
import com.vanhackathon.mybesthelper.model.Quiz;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daividsilverio on 5/20/16.
 * Still a Mock!
 */
public class ApiClient {
    private static ApiServices instance;

    public static Call<Quiz> getQuiz(Callback<Quiz> callback) {
        Call<Quiz> call = getApiServices().getQuiz();
        call.enqueue(callback);
        return call;
    }

    private static ApiServices getApiServices() {
        if (instance == null) {
            instance = buildApiServices();
        }
        return instance;
    }

    private static ApiServices buildApiServices() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiServices.class);
    }
}
