package com.pewa;

public class SingleSearchResult implements Comparable<SingleSearchResult> {
    private String url;
    private String desc;
    private Integer idInt;
    private String poster;
    private PewaType type;

    public PewaType getType() {
        return type;
    }

    public void setType(PewaType type) {
        this.type = type;
    }

    public Integer getIdInt() {
        return idInt;
    }

    public void setIdInt(Integer idInt) {
        this.idInt = idInt;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SingleSearchResult{" +
                "url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                ", idInt=" + idInt +
                ", poster='" + poster + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleSearchResult that = (SingleSearchResult) o;

        return desc != null ? desc.equals(that.desc) : that.desc == null;
    }

    @Override
    public int hashCode() {
        return desc != null ? desc.hashCode() : 0;
    }

    @Override
    public int compareTo(SingleSearchResult o) {
        return this.getDesc().compareTo(o.getDesc());
    }
}
