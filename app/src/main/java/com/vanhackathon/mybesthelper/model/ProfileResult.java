package com.vanhackathon.mybesthelper.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by daividsilverio on 5/22/16.
 */
public class ProfileResult implements Serializable {
    private static final long serialVersionUID = -285647825903985368L;

    @SerializedName("title")
    public String archeTypeTitle;

    @SerializedName("description")
    public String archeTypeDescription;

    @SerializedName("archetype_image_url")
    public String archetypeImageUrl;
}
