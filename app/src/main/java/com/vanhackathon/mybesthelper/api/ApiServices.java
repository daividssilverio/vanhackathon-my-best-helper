package com.vanhackathon.mybesthelper.api;

import com.vanhackathon.mybesthelper.model.ProfileResult;
import com.vanhackathon.mybesthelper.model.Quiz;
import com.vanhackathon.mybesthelper.model.QuizAnswers;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by daividsilverio on 5/20/16.
 */
public interface ApiServices {
    @GET("/quiz")
    Call<Quiz> getQuiz();

    @POST("/quiz/calculateProfile")
    Call<ProfileResult> calculateProfile(@Body QuizAnswers answers);

    @POST("/quiz/sendEmail")
    Call<Void> sendEmail(@Body HashMap<String, Object> email);
}
