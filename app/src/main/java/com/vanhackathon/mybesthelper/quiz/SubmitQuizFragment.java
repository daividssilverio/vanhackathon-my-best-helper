package com.vanhackathon.mybesthelper.quiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanhackathon.mybesthelper.R;
import com.vanhackathon.mybesthelper.events.CalculateResultRequestEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by daividsilverio on 5/21/16.
 */
public class SubmitQuizFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_submit, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.button_calculate)
    public void onCalculateResultsButtonClick() {
        EventBus.getDefault().post(new CalculateResultRequestEvent());
    }
}
