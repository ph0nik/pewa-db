package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Form implements Comparable<Form>, Serializable {
    private Integer id;
    private String form;

    static final long serialVersionUID = 1L;

    public Form() {}

    public Form(String form) {
        this.form = ("".equals(form)) ? Empty.NOT_AVAILABLE : form;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", form='" + form + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Form form1 = (Form) o;

        return form != null ? form.equals(form1.form) : form1.form == null;
    }

    @Override
    public int hashCode() {
        return form != null ? form.hashCode() : 0;
    }

    @Override
    public int compareTo(Form o) {
        return this.getForm().compareTo(o.getForm());
    }
}
