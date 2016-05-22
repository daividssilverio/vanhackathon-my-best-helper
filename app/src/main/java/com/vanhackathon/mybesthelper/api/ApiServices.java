package com.vanhackathon.mybesthelper.api;

import com.vanhackathon.mybesthelper.model.ProfileResult;
import com.vanhackathon.mybesthelper.model.Quiz;
import com.vanhackathon.mybesthelper.model.QuizAnswers;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by daividsilverio on 5/20/16.
 */
public interface ApiServices {
    @GET("vanhackathon/mybesthelper/quiz/{quiz_id}")
    Call<Quiz> getQuiz(@Path("quiz_id") int quizId);

    @POST("vanhackathon/mybesthelper/quiz/{quiz_id}/calculateProfile")
    Call<ProfileResult> calculateProfile(@Path("quiz_id") long quizId, @Body QuizAnswers answers);

    @POST("vanhackathon/mybesthelper/quiz/{quiz_id}/sendEmail")
    Call<Void> sendEmail(@Path("quiz_id") long quizId, @Body HashMap<String, Object> email);
}
