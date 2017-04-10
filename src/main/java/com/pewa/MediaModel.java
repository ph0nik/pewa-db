package com.pewa;

import com.pewa.movie.Movie;

import java.io.Serializable;

/**
 * Created by phonik on 2017-04-06.
 */
public abstract class MediaModel {
    private PewaType type;

    public boolean isEmpty() {
        return this.type == null;
    }

}
