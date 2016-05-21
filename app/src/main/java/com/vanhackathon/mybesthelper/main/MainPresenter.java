package com.vanhackathon.mybesthelper.main;

import android.content.Context;
import android.content.DialogInterface;

import com.vanhackathon.mybesthelper.api.ApiClient;
import com.vanhackathon.mybesthelper.base.BasePresenter;
import com.vanhackathon.mybesthelper.model.Quiz;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class MainPresenter extends BasePresenter implements QuizContract.UserActionsListener {

    private final QuizContract.View view;

    public MainPresenter(Context context, QuizContract.View view) {
        super(context);
        this.view = view;
        loadQuestions();
    }

    private void loadQuestions() {
        view.showLoadingPlaceholder(true);
        ApiClient.getQuiz(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                view.showLoadingPlaceholder(false);
                if (response.isSuccessful()) {
                    view.loadQuiz(response.body());
                } else {
                    view.showTryAgain(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loadQuestions();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                view.showLoadingPlaceholder(false);
                //todo: some other failure
            }
        });
    }
}
