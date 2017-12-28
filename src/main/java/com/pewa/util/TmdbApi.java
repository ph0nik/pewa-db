package com.pewa.util;

import com.pewa.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TmdbApi {

    private String baseUrl;
    private String secureBaseUrl;
    private List<String> posterSizes;
    private LocalDateTime configDate;


    public LocalDateTime getConfigDate() {
        return configDate;
    }

    public void setConfigDate(LocalDateTime configDate) {
        this.configDate = configDate;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }

    public List<String> getPosterSizes() {
        return posterSizes;
    }

    public void setPosterSizes(List<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    public void setPosterSizes(String element) {
        if (this.posterSizes == null) this.posterSizes = new ArrayList<>();
        this.posterSizes.add(element);
    }

    @Override
    public String toString() {
        return "TmdbConfiguration{" +
                "baseUrl='" + baseUrl + '\'' +
                ", secureBaseUrl='" + secureBaseUrl + '\'' +
                ", posterSizes=" + posterSizes +
                ", configDate=" + configDate +
                '}';
    }
}
