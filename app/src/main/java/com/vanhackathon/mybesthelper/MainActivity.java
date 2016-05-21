package com.vanhackathon.mybesthelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.vanhackathon.mybesthelper.base.BaseActivity;
import com.vanhackathon.mybesthelper.main.MainPresenter;
import com.vanhackathon.mybesthelper.main.QuizContract;
import com.vanhackathon.mybesthelper.question.QuestionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements QuizContract.View {

    @BindView(R.id.viewPager)
    public ViewPager questionsViewPager;

    private static MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (presenter == null) {
            presenter = new MainPresenter(this, this);
        }
        presenter.init(this, this);
    }

    @Override
    public void showLoadingPlaceholder(boolean show) {

    }

    @Override
    public void showTryAgain(DialogInterface.OnClickListener listener) {

    }

    @Override
    public void setupQuizAdapter() {
        questionsViewPager.setAdapter(new QuestionsPagerAdapter(getSupportFragmentManager()));
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    public class QuestionsPagerAdapter extends FragmentStatePagerAdapter {

        public QuestionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public QuestionFragment getItem(int position) {
            return QuestionFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return presenter.quizSize();
        }
    }
}
