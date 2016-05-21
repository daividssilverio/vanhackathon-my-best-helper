package com.vanhackathon.mybesthelper;

import android.content.DialogInterface;
import android.os.Bundle;

import com.vanhackathon.mybesthelper.base.BaseActivity;
import com.vanhackathon.mybesthelper.main.MainPresenter;
import com.vanhackathon.mybesthelper.main.QuizContract;

public class MainActivity extends BaseActivity implements QuizContract.View {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this, this);
    }

    @Override
    public void showLoadingPlaceholder(boolean show) {

    }

    @Override
    public void showTryAgain(DialogInterface.OnClickListener listener) {

    }
}
