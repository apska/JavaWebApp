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
public class OrganizationSection extends Section {
    private List<Organization> values;

    public OrganizationSection(Organization... values){
        this.values = new LinkedList<>(Arrays.asList(values));
    }
}
