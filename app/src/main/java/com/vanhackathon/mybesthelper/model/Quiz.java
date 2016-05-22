package com.vanhackathon.mybesthelper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class Quiz implements Serializable {
    private static final long serialVersionUID = 4002774404692042976L;

    @SerializedName("quiz_id")
    public long quizId;

    @SerializedName("version")
    public int version;

    @SerializedName("questions")
    public ArrayList<Question> questions;

    public QuizAnswers getAnswers() {
        ArrayList<Answer> answers = new ArrayList<>();
        for (Question question : questions) {
            answers.add(question.getAnswer());
        }
        return new QuizAnswers(this.quizId, answers);
    }
}
