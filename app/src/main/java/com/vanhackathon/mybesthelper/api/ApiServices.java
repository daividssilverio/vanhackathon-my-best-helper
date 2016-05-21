package com.vanhackathon.mybesthelper.api;

import com.vanhackathon.mybesthelper.model.Quiz;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by daividsilverio on 5/20/16.
 */
public interface ApiServices {
    @GET("/quiz")
    Call<Quiz> getQuiz();
}
