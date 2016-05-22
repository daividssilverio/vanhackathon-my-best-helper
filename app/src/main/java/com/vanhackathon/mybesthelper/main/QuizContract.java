package com.vanhackathon.mybesthelper.main;

import android.content.DialogInterface;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class QuizContract {
    public interface View {
        void showLoadingPlaceholder(boolean show);

        void showTryAgain(boolean show, android.view.View.OnClickListener listener);

        void setupQuizAdapter();

        void moveToQuestion(int position);

        void nextQuestion();

        void alert(String string);

        void showConfirmationDialog(String confirmationTitle, String confirmationMessage, DialogInterface.OnClickListener listener);

        void exit();

        void showProgressDialog(String string);

        void dismissProgressDialog();

        void showTryAgainDialog(String tryAgainTitle, String tryAgainMessage, DialogInterface.OnClickListener listener);
    }

    public interface UserActionsListener {
        void onBackPressed();
    }
}
