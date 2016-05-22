package com.vanhackathon.mybesthelper.api;

import com.vanhackathon.mybesthelper.BuildConfig;
import com.vanhackathon.mybesthelper.model.ProfileResult;
import com.vanhackathon.mybesthelper.model.Quiz;
import com.vanhackathon.mybesthelper.model.QuizAnswers;

import java.util.HashMap;

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

    public static Call<Quiz> getQuiz(int quizId, Callback<Quiz> callback) {
        Call<Quiz> call = getApiServices().getQuiz(quizId);
        call.enqueue(callback);
        return call;
    }

    public static Call<ProfileResult> calculateProfile(QuizAnswers answers, Callback<ProfileResult> callback) {
        Call<ProfileResult> call = getApiServices().calculateProfile(answers.quizId, answers);
        call.enqueue(callback);
        return call;
    }

    public static Call<Void> sendEmail(String email, ProfileResult profileResult, Callback<Void> callback) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("profile_result", profileResult);
        Call<Void> call = getApiServices().sendEmail(profileResult.quizId, body);
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
