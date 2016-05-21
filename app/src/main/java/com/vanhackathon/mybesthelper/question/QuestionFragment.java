package com.vanhackathon.mybesthelper.question;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vanhackathon.mybesthelper.MainActivity;
import com.vanhackathon.mybesthelper.R;
import com.vanhackathon.mybesthelper.model.Option;
import com.vanhackathon.mybesthelper.model.Question;
import com.vanhackathon.mybesthelper.util.ItemOffesetDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vanhackathon.mybesthelper.model.Question.SINGLE_COLUMN;
import static com.vanhackathon.mybesthelper.model.Question.SMALL_TEXT_WITH_ICON;
import static com.vanhackathon.mybesthelper.model.Question.TEXT_ONLY;

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

        private final int layoutId;

        public OptionsAdapter() {
            super();
            switch (question.questionType) {
                case SMALL_TEXT_WITH_ICON:
                    this.layoutId = R.layout.view_option_small_text_with_icon;
                    break;
                case TEXT_ONLY:
                    this.layoutId = R.layout.view_option_text_only;
                    break;
                case SINGLE_COLUMN:
                    this.layoutId = R.layout.view_option_single_column;
                    break;
                default:
                    this.layoutId = R.layout.view_option_text_only;
            }
        }

        public Option getItem(int position) {
            if (question == null || question.options == null) return null;
            return question.options.get(position);
        }

        @Override
        public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new OptionViewHolder(LayoutInflater.from(parent.getContext()).inflate(this.layoutId, parent, false));
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

        @BindView(R.id.title)
        TextView title;

        @Nullable
        @BindView(R.id.description)
        TextView description;

        @Nullable
        @BindView(R.id.question_thumbnail_image_view)
        ImageView questionThumbnailImageView;

        public OptionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, this.itemView);
        }

        public void format(Option option) {
            title.setText(option.title);
            if (description != null) {
                description.setText(option.description);
            }
            if (questionThumbnailImageView != null) {
                Picasso.with(itemView.getContext()).load(option.questionThumbnailUrl).into(questionThumbnailImageView);
            }
        }
    }
}
