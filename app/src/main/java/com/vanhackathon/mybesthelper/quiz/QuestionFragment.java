package com.vanhackathon.mybesthelper.quiz;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanhackathon.mybesthelper.main.MainActivity;
import com.vanhackathon.mybesthelper.R;
import com.vanhackathon.mybesthelper.model.Question;
import com.vanhackathon.mybesthelper.util.ItemOffesetDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vanhackathon.mybesthelper.model.Question.SINGLE_COLUMN;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    private static final String QUESTION_ARG = "QUESTION_ARG";

    @BindView(R.id.question_title)
    TextView titleTextView;

    @BindView(R.id.options_recycler_view)
    RecyclerView optionsRecyclerView;

    private Question question;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int questionIndex = getArguments().getInt(QUESTION_ARG);
        this.question = ((MainActivity) getActivity()).getPresenter().getQuestion(questionIndex);
        View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, rootView);
        int numberOfColumns = question.questionType == SINGLE_COLUMN ? 1 : rootView.getResources().getInteger(R.integer.option_columns);
        titleTextView.setText(question.description);
        optionsRecyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), numberOfColumns));
        optionsRecyclerView.addItemDecoration(new ItemOffesetDecoration(rootView.getContext(), R.dimen.option_offeset));
        optionsRecyclerView.setAdapter(new OptionsAdapter(question));
        return rootView;
    }

    public static QuestionFragment newInstance(int questionIndex) {
        Bundle args = new Bundle();
        args.putInt(QUESTION_ARG, questionIndex);
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
