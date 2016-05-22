package com.vanhackathon.mybesthelper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by daividsilverio on 5/22/16.
 */
public class QuizAnswers implements Serializable {
    private static final long serialVersionUID = -6936277851350019510L;

    @SerializedName("quiz_id")
    long quizId;

    @SerializedName("answers")
    ArrayList<Answer> answers;

    public QuizAnswers(long quizId, ArrayList<Answer> answers) {
        this.quizId = quizId;
        this.answers = answers;
    }
}
