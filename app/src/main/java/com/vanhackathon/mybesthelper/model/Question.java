package com.vanhackathon.mybesthelper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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

    @SerializedName("options")
    public ArrayList<Option> options;

    public boolean isAnswered() {
        if (options == null || options.isEmpty()) return false;
        for (Option option : options) {
            if (option.isSelected) return true;
        }
        return false;
    }

}
