package com.vanhackathon.mybesthelper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by daividsilverio on 5/20/16.
 */
public class Option implements Serializable {
    private static final long serialVersionUID = -6597084758011407776L;

    @SerializedName("optionId")
    public long optionId;

    @SerializedName("thumbnail_url")
    public String questionThumbnailUrl;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    boolean isSelected = false;
}
