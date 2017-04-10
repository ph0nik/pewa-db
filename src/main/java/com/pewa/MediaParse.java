package com.pewa;

public interface MediaParse<T,U> {

    /**
     * Returns item of type T based on identifier of type U
     *
     * */

    T getItem(U id);
}
