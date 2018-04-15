package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.util.CustomLocalDateDeserializer;
import com.pewa.util.CustomLocalDateSerializer;

import java.time.LocalDate;
import java.util.Comparator;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExternalMediaResult extends MediaModel implements Comparable<ExternalMediaResult> {
    // original title
    private String title;
    // element description
    private String description;
    // english title
    private String engTitle;
    // external id
    private Integer idInt;
    // internal object type
    private PewaType type;
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate date;

    public ExternalMediaResult() {
        super();
        setType(PewaType.STATUS);
    }

    public String getEngTitle() {
        return engTitle;
    }

    public void setEngTitle(String engTitle) {
        this.engTitle = engTitle;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExternalMediaResult{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", engTitle='" + engTitle + '\'' +
                ", idInt=" + idInt +
                ", type=" + type +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExternalMediaResult that = (ExternalMediaResult) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (engTitle != null ? !engTitle.equals(that.engTitle) : that.engTitle != null) return false;
        if (idInt != null ? !idInt.equals(that.idInt) : that.idInt != null) return false;
        if (type != that.type) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (engTitle != null ? engTitle.hashCode() : 0);
        result = 31 * result + (idInt != null ? idInt.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    private static Comparator<String> nullSafeStringComparator = Comparator.nullsFirst(String::compareTo);
    private static Comparator<Integer> nullSafeIntegerComparator = Comparator.nullsFirst(Integer::compareTo);
    private static Comparator<ExternalMediaResult> singleSearchResultComparator = Comparator.comparing(ExternalMediaResult::getIdInt, nullSafeIntegerComparator)
            .thenComparing(ExternalMediaResult::getTitle, nullSafeStringComparator)
            .thenComparing(ExternalMediaResult::getDescription, nullSafeStringComparator);

    @Override
    public int compareTo(ExternalMediaResult o) {
        return singleSearchResultComparator.compare(this, o);
    }

    @Override
    public boolean isEmpty() {
        return title.isEmpty();
    }
}
