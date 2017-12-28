package com.pewa.common;

import javax.ejb.Local;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by phonik on 2017-11-27.
 */
public class DateRange implements Serializable {
    private LocalDate date1;
    private LocalDate date2;


    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

    public LocalDate getDate1() {
        return date1;
    }

    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateRange dateRange = (DateRange) o;

        if (date1 != null ? !date1.equals(dateRange.date1) : dateRange.date1 != null) return false;
        return date2 != null ? date2.equals(dateRange.date2) : dateRange.date2 == null;
    }

    @Override
    public int hashCode() {
        int result = date1 != null ? date1.hashCode() : 0;
        result = 31 * result + (date2 != null ? date2.hashCode() : 0);
        return result;
    }


}
