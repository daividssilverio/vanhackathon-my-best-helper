package com.vanhackathon.mybesthelper.main;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class QuizContract {
    public interface View {
        void showLoadingPlaceholder(boolean show);

        void showTryAgain(boolean show, android.view.View.OnClickListener listener);

        void setupQuizAdapter();

        void moteToQuestion(int position);

        void nextQuestion();
    }

    public interface UserActionsListener {

    }
}
