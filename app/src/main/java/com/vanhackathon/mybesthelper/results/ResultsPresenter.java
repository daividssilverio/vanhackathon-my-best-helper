package com.vanhackathon.mybesthelper.results;

import android.content.Context;
import android.content.DialogInterface;

import com.vanhackathon.mybesthelper.R;
import com.vanhackathon.mybesthelper.api.ApiClient;
import com.vanhackathon.mybesthelper.base.BasePresenter;
import com.vanhackathon.mybesthelper.model.ProfileResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daividsilverio on 5/22/16.
 */
public class ResultsPresenter extends BasePresenter implements ResultsContract.UserActionsListener {
    private final ResultsContract.View view;
    private ProfileResult profileResult;

    public ResultsPresenter(Context context, ResultsContract.View view) {
        super(context);
        this.view = view;
    }

    @Override
    public void sendEmail(final String email) {
        this.view.showProgressDialog(getString(R.string.activity_main_sending_email));
        ApiClient.sendEmail(email, profileResult, new Callback<Void>() {

            public void showTryAgain() {
                String title = getString(R.string.general_error_title);
                String message = getString(R.string.general_error_message);
                view.showTryAgain(title, message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendEmail(email);
                    }
                });
            }

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                view.hideProgressDialog();
                if (response.isSuccessful()) {
                    String title = getString(R.string.general_success_title);
                    String message = getString(R.string.activity_result_email_sent);
                    view.showSuccess(title, message);
                } else {
                    showTryAgain();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.hideProgressDialog();
                showTryAgain();
            }
        });
    }

    public void init(ProfileResult profileResult) {
        this.profileResult = profileResult;
        this.view.showResultInfo(profileResult.archeTypeTitle, profileResult.archeTypeDescription, profileResult.archetypeImageUrl);
    }
}
