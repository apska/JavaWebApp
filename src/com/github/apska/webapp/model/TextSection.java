package com.github.apska.webapp.model;

import java.util.Objects;

/**
 * Created by APS2
 * on 10.02.2016
 */
public class TextSection extends Section {
    static final long serialVersionUID = 1L;

    private String value;

    public TextSection() {
    }

    public TextSection(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TextSection other = (TextSection) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value;
    }
}
