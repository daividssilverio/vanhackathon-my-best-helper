package com.vanhackathon.mybesthelper.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class Option {
    @SerializedName("optionId")
    long optionId;

    @SerializedName("thumbnail_url")
    String questionThumbnailUrl;

    @SerializedName("description")
    String description;

    boolean isSelected = false;
}
