package com.vanhackathon.mybesthelper.results;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vanhackathon.mybesthelper.R;
import com.vanhackathon.mybesthelper.base.BaseActivity;
import com.vanhackathon.mybesthelper.model.ProfileResult;
import com.vanhackathon.mybesthelper.util.DialogUtils;
import com.vanhackathon.mybesthelper.util.SimpleTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import commons.validator.routines.EmailValidator;

/**
 * Created by daividsilverio on 5/21/16.
 */
public class ResultsActivity extends BaseActivity implements ResultsContract.View {

    private static final String RESULT_EXTRA = "RESULT_EXTRA";

    @BindView(R.id.archetype_image)
    ImageView archetypeImageView;

    @BindView(R.id.archetype_name_text)
    TextView archetypeNameTextView;

    @BindView(R.id.archetype_description_text)
    TextView archetypeDescriptionTextView;

    @BindView(R.id.emailEditText)
    EditText emailEditText;

    private ResultsPresenter resultsPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        ProfileResult profileResult = (ProfileResult) getIntent().getSerializableExtra(RESULT_EXTRA);
        initPresenter(profileResult);
    }

    private void initPresenter(ProfileResult profileResult) {
        if (resultsPresenter == null) {
            resultsPresenter = new ResultsPresenter(this, this);
        }
        resultsPresenter.init(profileResult);
    }

    @OnClick(R.id.button_send_email)
    public void onSendEmailButtonClicked() {
        validateEmail();
    }

    private void validateEmail() {
        if (EmailValidator.getInstance().isValid(emailEditText.getText().toString())) {
            resultsPresenter.sendEmail(emailEditText.getText().toString());
        } else {
            emailEditText.setError(getString(R.string.validation_invalid_email));
            emailEditText.addTextChangedListener(new SimpleTextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    super.beforeTextChanged(s, start, count, after);
                    emailEditText.setError(null);
                    emailEditText.removeTextChangedListener(this);
                }
            });
        }
    }

    @Override
    public void showResultInfo(String resultTitle, String resultDescription, String resultImageUrl) {
        archetypeNameTextView.setText(resultTitle);
        archetypeDescriptionTextView.setText(resultDescription);
        Picasso.with(this).load(resultImageUrl).placeholder(R.drawable.ic_photo_black_24dp).into(archetypeImageView);
    }

    @Override
    public void showProgressDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showTryAgain(String title, String message, DialogInterface.OnClickListener listener) {
        DialogUtils.showTryAgain(this, title, message, listener);
    }

    @Override
    public void showSuccess(String title, String message) {
        DialogUtils.showMessage(this, title, message);
    }

    public static void start(Context context, ProfileResult profileResult) {
        context.startActivity(new Intent(context, ResultsActivity.class).putExtra(RESULT_EXTRA, profileResult));
    }
}
