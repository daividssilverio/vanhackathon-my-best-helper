package com.vanhackathon.mybesthelper.main;

import android.content.DialogInterface;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class QuizContract {
    public interface View {
        void showLoadingPlaceholder(boolean show);

        void showTryAgain(DialogInterface.OnClickListener listener);

        void setupQuizAdapter();
    }

    public interface UserActionsListener {

    }
}
