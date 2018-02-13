package com.pewa.anime.anilist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Edge_ {

    @SerializedName("node")
    @Expose
    private Node_ node;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("voiceActors")
    @Expose
    private List<VoiceActor> voiceActors = null;

    public Node_ getNode() {
        return node;
    }

    public void setNode(Node_ node) {
        this.node = node;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<VoiceActor> getVoiceActors() {
        return voiceActors;
    }

    public void setVoiceActors(List<VoiceActor> voiceActors) {
        this.voiceActors = voiceActors;
    }

}
