package com.pewa.anime.anilist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Edge {

    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("node")
    @Expose
    private Node node;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

}
