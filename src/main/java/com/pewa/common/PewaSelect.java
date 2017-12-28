package com.pewa.common;

/**
 * Created by phonik on 2017-11-23.
 */
public class PewaSelect {
    private String whereIn;
    private String select;
    private String from;
    private String innerJoin;
    private String on;
    private String equals;
    private Integer id;

    public String getWhereIn() {
        return whereIn;
    }

    public void setWhereIn(String whereIn) {
        this.whereIn = whereIn;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getInnerJoin() {
        return innerJoin;
    }

    public void setInnerJoin(String innerJoin) {
        this.innerJoin = innerJoin;
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getEquals() {
        return equals;
    }

    public void setEquals(String equals) {
        this.equals = equals;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
