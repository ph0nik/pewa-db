package com.pewa.anime;

public class AnimeMangaQuery {

    private String query;
    private String variables;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "AnimeMangaQuery{" +
                "query='" + query + '\'' +
                ", variables='" + variables + '\'' +
                '}';
    }
}
