package com.vanhackathon.mybesthelper.main;

import android.content.DialogInterface;

import com.vanhackathon.mybesthelper.model.Quiz;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class QuizContract {
    public interface View {
        void showLoadingPlaceholder(boolean show);

        void showTryAgain(DialogInterface.OnClickListener listener);

        void loadQuiz(Quiz body);
    }

    public interface UserActionsListener {

    }
}
