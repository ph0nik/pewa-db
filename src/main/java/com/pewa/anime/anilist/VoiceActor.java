package com.pewa.anime.anilist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoiceActor {

    @SerializedName("name")
    @Expose
    private Name_ name;

    public Name_ getName() {
        return name;
    }

    public void setName(Name_ name) {
        this.name = name;
    }

}
