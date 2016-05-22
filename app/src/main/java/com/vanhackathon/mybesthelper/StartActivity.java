package com.vanhackathon.mybesthelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vanhackathon.mybesthelper.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    public static final String QUIZ_ID = "QUIZ_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_start)
    public void startQuiz() {
        startActivity(new Intent(this, MainActivity.class).putExtra(QUIZ_ID, 1));
    }

    @OnClick(R.id.button_start_showcase)
    public void startQuizShowCase() {
        startActivity(new Intent(this, MainActivity.class).putExtra(QUIZ_ID, 2));
    }
}
