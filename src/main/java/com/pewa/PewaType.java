package com.pewa;

public enum PewaType {
    MOVIE(0), TVSERIES(1), BOOK(2), MUSIC(3), ANIME(4), MANGA(5), STATUS(6);

    private final Integer pewaTypeValue;
    private PewaType pewaTypeName;

    private PewaType(Integer pewaTypeValue) {
        this.pewaTypeValue = pewaTypeValue;
    }

    public Integer getPewaTypeValue() {
        return pewaTypeValue;
    }

    public PewaType getPewaTypeName() {
        for (PewaType pt : this.values()) {
            if (pt.equals(this))
                return pt;
        }
        return null;
    }
}
