package com.github.apska.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by APS2
 * on 10.02.2016
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class MultyTextSection extends Section {
    static final long serialVersionUID = 1L;

    private List<String> values;

    public MultyTextSection(String... values){
        this(new LinkedList<>(Arrays.asList(values)));
    }

    public MultyTextSection(List<String> values){
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MultyTextSection other = (MultyTextSection) obj;

        if (values != null ? !values.equals(other.values) : other.values != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }
}
