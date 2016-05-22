package com.vanhackathon.mybesthelper.quiz;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vanhackathon.mybesthelper.R;
import com.vanhackathon.mybesthelper.events.ItemSelectedEvent;
import com.vanhackathon.mybesthelper.model.Option;
import com.vanhackathon.mybesthelper.model.Question;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vanhackathon.mybesthelper.model.Question.SINGLE_COLUMN;
import static com.vanhackathon.mybesthelper.model.Question.SMALL_TEXT_WITH_ICON;
import static com.vanhackathon.mybesthelper.model.Question.TEXT_ONLY;

/**
 * Created by daividsilverio on 5/21/16.
 */
public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionViewHolder> {

    private final Question question;
    private final int layoutId;
    private final Handler handler;
    private boolean isAnimating = false;

    public OptionsAdapter(Question question) {
        super();
        this.handler = new Handler();
        this.question = question;
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

    public class OptionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        public TextView title;

        @BindView(R.id.check_indicator)
        public View checkIndicator;

        @Nullable
        @BindView(R.id.description)
        public TextView description;

        @Nullable
        @BindView(R.id.question_thumbnail_image_view)
        public ImageView questionThumbnailImageView;

        public OptionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, this.itemView);
        }

        public void format(final Option option) {
            updateVisibility(option.isSelected);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateSelectedItem(option);
                    notifyDataSetChanged();
                }
            });
            title.setText(option.title);
            if (description != null) {
                description.setText(option.description);
            }
            if (questionThumbnailImageView != null) {
                Picasso.with(itemView.getContext()).load(option.questionThumbnailUrl).into(questionThumbnailImageView);
            }
        }

        private void updateVisibility(boolean isSelected) {
            checkIndicator.setVisibility(isSelected ? View.VISIBLE : View.GONE);
        }
    }

    private void updateSelectedItem(Option selectedOption) {
        if (isAnimating) return;
        if (selectedOption.isSelected) {
            selectedOption.isSelected = false;
        } else {
            isAnimating = true;
            selectedOption.isSelected = true;
            for (Option option : question.options) {
                if (option.isSelected && (option.optionId != selectedOption.optionId)) {
                    option.isSelected = false;
                    break;
                }
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isAnimating = false;
                    EventBus.getDefault().post(new ItemSelectedEvent());
                }
            }, 300);
        }
    }
}
