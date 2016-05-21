package com.vanhackathon.mybesthelper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class Quiz implements Serializable {
    @SerializedName("quiz_id")
    long quizId;

    @SerializedName("version")
    int version;

    @SerializedName("questions")
    ArrayList<Question> questions;
}
