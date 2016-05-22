package com.vanhackathon.mybesthelper.results;

import android.content.DialogInterface;

/**
 * Created by daividsilverio on 5/22/16.
 */
public class ResultsContract {
    public interface View {
        void showResultInfo(String resultTitle, String resultDescription, String resultImageUrl);

        void showProgressDialog(String message);

        void showTryAgain(String title, String message, DialogInterface.OnClickListener listener);

        void showSuccess(String title, String message);

        void hideProgressDialog();
    }

    public interface UserActionsListener {
        void sendEmail(String email);
    }
}
