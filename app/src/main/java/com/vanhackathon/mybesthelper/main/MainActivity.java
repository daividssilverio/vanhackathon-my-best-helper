package com.vanhackathon.mybesthelper.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import com.vanhackathon.mybesthelper.R;
import com.vanhackathon.mybesthelper.base.BaseActivity;
import com.vanhackathon.mybesthelper.quiz.QuestionFragment;
import com.vanhackathon.mybesthelper.quiz.SubmitQuizFragment;
import com.vanhackathon.mybesthelper.util.DialogUtils;
import com.viewpagerindicator.UnderlinePageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements QuizContract.View {

    @BindView(R.id.viewPager)
    public ViewPager questionsViewPager;

    @BindView(R.id.loadingView)
    ViewStub loadingViewStub;

    @BindView(R.id.tryagainView)
    ViewStub tryAgainViewStub;

    @BindView(R.id.pager_indicator)
    UnderlinePageIndicator pageIndicator;

    private static MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new MainPresenter(this, this);
        }
        presenter.init(this, this);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        clearPresenter();
        super.onDestroy();
    }

    private void clearPresenter() {
        if (presenter != null) {
            presenter.release();
            presenter = null;
        }
    }

    @Override
    public void showLoadingPlaceholder(boolean show) {
        loadingViewStub.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showTryAgain(boolean show, View.OnClickListener listener) {
        tryAgainViewStub.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setupQuizAdapter() {
        if (questionsViewPager.getAdapter() == null) {
            questionsViewPager.setAdapter(new QuestionsPagerAdapter(getSupportFragmentManager()));
        }
        pageIndicator.setViewPager(questionsViewPager);
        pageIndicator.setFades(false);
    }

    @Override
    public void moveToQuestion(final int position) {
        if (questionsViewPager != null) {
            setViewPagerItem(position);
        }
    }

    @Override
    public void nextQuestion() {
        if (questionsViewPager != null) {
            PagerAdapter adapter = questionsViewPager.getAdapter();
            if (adapter == null) return;
            int currentItem = questionsViewPager.getCurrentItem();
            if (currentItem >= 0 && (currentItem + 1) < adapter.getCount()) {
                setViewPagerItem(currentItem + 1);
            }
        }
    }

    private void setViewPagerItem(final int position) {
        questionsViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                questionsViewPager.setCurrentItem(position, true);
            }
        }, 100);
    }

    @Override
    public void alert(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showConfirmationDialog(String confirmationTitle, String confirmationMessage, DialogInterface.OnClickListener listener) {
        DialogUtils.showConfirmation(this, confirmationTitle, confirmationMessage, listener);
    }

    @Override
    public void exit() {
        clearPresenter();
        finish();
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    public class QuestionsPagerAdapter extends FragmentStatePagerAdapter {

        public QuestionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == getCount() - 1) {
                return new SubmitQuizFragment();
            }
            return QuestionFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return presenter.quizSize() + 1;
        }
    }
}
