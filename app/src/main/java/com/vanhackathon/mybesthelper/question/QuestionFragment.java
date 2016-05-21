package com.vanhackathon.mybesthelper.question;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanhackathon.mybesthelper.MainActivity;
import com.vanhackathon.mybesthelper.R;
import com.vanhackathon.mybesthelper.model.Option;
import com.vanhackathon.mybesthelper.model.Question;
import com.vanhackathon.mybesthelper.util.ItemOffesetDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    private static final String QUESTION_ARG = "QUESTION_ARG";

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
        optionsRecyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), rootView.getResources().getInteger(R.integer.option_columns)));
        optionsRecyclerView.addItemDecoration(new ItemOffesetDecoration(rootView.getContext(), R.dimen.option_offeset));
        optionsRecyclerView.setAdapter(new OptionsAdapter());
        return rootView;
    }

    public static QuestionFragment newInstance(int questionIndex) {
        Bundle args = new Bundle();
        args.putInt(QUESTION_ARG, questionIndex);
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface QuestionHolderListener {
        Question getQuestion(int index);
    }

    private class OptionsAdapter extends RecyclerView.Adapter<OptionViewHolder> {

        public Option getItem(int position) {
            if (question == null || question.options == null) return null;
            return question.options.get(position);
        }

        @Override
        public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new OptionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_option, parent, false));
        }

        @Override
        public void onBindViewHolder(OptionViewHolder holder, int position) {
            Option option = getItem(position);
            if (option == null) return;
            holder.format(option);
        }

        @Override
        public int getItemCount() {
            return question == null || question.options == null ? 0 : question.options.size();
        }
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView text;

        public OptionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, this.itemView);
        }

        public void format(Option option) {
            text.setText(option.description);
        }
    }
}
