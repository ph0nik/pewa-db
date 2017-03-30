package com.pewa.anime;

import java.io.Serializable;

public class AnimeAccessToken implements Serializable {
    private String accessToken;
    private String tokenType;
    private Long expireTime;

    static final long serialVersionUID = 1L;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpireTime() {
        return expireTime;
    }

    @Override
    public String toString() {
        return "[" + accessToken + ", "
                + tokenType + ", "
                + expireTime + "]";
    }

}