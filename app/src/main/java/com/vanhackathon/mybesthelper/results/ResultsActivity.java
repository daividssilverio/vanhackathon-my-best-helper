package com.vanhackathon.mybesthelper.results;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vanhackathon.mybesthelper.R;
import com.vanhackathon.mybesthelper.base.BaseActivity;
import com.vanhackathon.mybesthelper.model.Quiz;

/**
 * Created by daividsilverio on 5/21/16.
 */
public class ResultsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    public static void start(Context context, Quiz quiz) {
        context.startActivity(new Intent(context, ResultsActivity.class));
    }
}
