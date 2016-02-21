package com.github.apska.webapp.model;

/**
 * Created by APS2
 * on 10.02.2016
 */
public enum ContactType {
    PHONE("Тел."),
    MOBILE("Мобильный"),
    HOME_PHONE("Домашний тел."),
    SKYPE("Skype"),
    MAIL("Почта"),
    ICQ("ICQ");

    public static ContactType[] VALUES = ContactType.values();

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}