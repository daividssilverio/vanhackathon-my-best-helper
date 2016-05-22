package com.vanhackathon.mybesthelper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by daividsilverio on 5/22/16.
 */
public class Answer implements Serializable {
    private static final long serialVersionUID = 117243926631237860L;

    @SerializedName("question_id")
    long questionId;

    @SerializedName("option_id")
    long optionId;

    public Answer() {
    }

    public Answer(long questionId, long optionId) {
        this.questionId = questionId;
        this.optionId = optionId;
    }
}
