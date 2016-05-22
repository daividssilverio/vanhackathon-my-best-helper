package com.vanhackathon.mybesthelper.model;

import android.support.annotation.IntDef;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 914156478836737996L;

    @SerializedName("question_id")
    public long questionId;

    @SerializedName("description")
    public String description;

    @SerializedName("question_type")
    @Question.QuestionType
    public int questionType;

    @SerializedName("options")
    public ArrayList<Option> options;

    public long selectedOptionId = -1;

    public boolean isAnswered() {
        if (options == null || options.isEmpty()) return false;
        for (Option option : options) {
            if (option.isSelected) return true;
        }
        return false;
    }

    public Answer getAnswer() {
        return new Answer(this.questionId, selectedOptionId);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SMALL_TEXT_WITH_ICON, TEXT_ONLY, SINGLE_COLUMN})
    public @interface QuestionType {
    }

    public static final int SMALL_TEXT_WITH_ICON = 0;
    public static final int TEXT_ONLY = 1;
    public static final int SINGLE_COLUMN = 2;
}
