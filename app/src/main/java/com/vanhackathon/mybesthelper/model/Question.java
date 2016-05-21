package com.vanhackathon.mybesthelper.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class Question {
    @SerializedName("question_id")
    long questionId;

    @SerializedName("description")
    String description;

    @SerializedName("options")
    ArrayList<Option> options;

    public boolean isAnswered() {
        if (options == null || options.isEmpty()) return false;
        for (Option option : options) {
            if (option.isSelected) return true;
        }
        return false;
    }

}
