package com.vanhackathon.mybesthelper.main;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.vanhackathon.mybesthelper.R;
import com.vanhackathon.mybesthelper.api.ApiClient;
import com.vanhackathon.mybesthelper.base.BasePresenter;
import com.vanhackathon.mybesthelper.events.CalculateResultRequestEvent;
import com.vanhackathon.mybesthelper.events.ItemSelectedEvent;
import com.vanhackathon.mybesthelper.model.Question;
import com.vanhackathon.mybesthelper.model.Quiz;
import com.vanhackathon.mybesthelper.results.ResultsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class MainPresenter extends BasePresenter implements QuizContract.UserActionsListener {

    private QuizContract.View view;
    public Quiz quiz;
    private final Lock lock = new ReentrantLock(true);

    public MainPresenter(Context context, QuizContract.View view) {
        super(context);
        this.view = view;
        EventBus.getDefault().register(this);
    }

    private void loadQuiz() {
        view.showLoadingPlaceholder(true);
        ApiClient.getQuiz(new Callback<Quiz>() {

            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                view.showLoadingPlaceholder(false);
                if (response.isSuccessful()) {
                    quiz = response.body();
                    view.setupQuizAdapter();
                } else {
                    view.showTryAgain(true, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadQuiz();
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

    public int quizSize() {
        return quiz == null || quiz.questions == null ? 0 : quiz.questions.size();
    }

    public Question getQuestion(int index) {
        return quiz.questions.get(index);
    }

    public void init(Context context, QuizContract.View view) {
        this.context = context;
        this.view = view;
        if (quiz == null) {
            loadQuiz();
        } else {
            view.setupQuizAdapter();
        }
    }

    public void release() {
        this.view = null;
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(ItemSelectedEvent event) {
        if (lock.tryLock()) {
            view.nextQuestion();
            lock.unlock();
        }
    }

    @Subscribe
    public void onEvent(CalculateResultRequestEvent event) {
        if (validateAnswers()) {
            showResults();
        }
    }

    private void showResults() {
        ResultsActivity.start(context, quiz);
        this.view.exit();
    }

    private boolean validateAnswers() {
        if (quiz == null || quiz.questions == null) return false;
        for (int i = 0; i < quiz.questions.size(); i++) {
            Question question = quiz.questions.get(i);
            if (!question.isAnswered()) {
                this.view.moveToQuestion(i);
                this.view.alert(context.getString(R.string.activity_main_select_one));
                return false;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        String confirmationTitle = context.getString(R.string.activity_main_exit_title);
        String confirmationMessage = context.getString(R.string.activity_main_exit_message);
        view.showConfirmationDialog(confirmationTitle, confirmationMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                view.exit();
            }
        });
    }
}
