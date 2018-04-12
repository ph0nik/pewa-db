package com.pewa;

import com.pewa.common.Encounter;

/**
 * Created by phonik on 2017-04-06.
 */
public abstract class MediaModel /*implements Encounter*/ {

    private PewaType type;

    public PewaType getType() {
        return type;
    }

    public void setType(PewaType type) {
        this.type = type;
    }

    public abstract boolean isEmpty();
}
