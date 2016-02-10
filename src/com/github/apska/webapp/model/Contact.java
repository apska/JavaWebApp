package com.github.apska.webapp.model;

/**
 * Created by APS2
 * on 09.02.2016
 */
public class Contact {
    private final ContactType type;
    private final String value;

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public ContactType getType() {
        return type;
    }
}
