package com.github.apska.webapp.model;

import java.util.*;

/**
 * Created by APS2
 * on 06.02.2016
 */
public final class Resume {//implements Comparable<Resume> {
    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    private List<Section> sections = new LinkedList<>();

    public  Resume(){

    }

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Resume other = (Resume) obj;
        return uuid.equals(other.uuid);
    }

    public void addSection(Section section){
        sections.add(section);
    }

    public void addContact(ContactType type, String value){
        contacts.put(type, value);
    }
    public String getContact(ContactType type){
        return contacts.get(type);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getHomePage() {
        return homePage;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

//    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName);
    }

    /*private String getEmail(List<Contact> list) {
        for(Contact c : list){
            if(c.getType() == ContactType.MAIL){
                return c.getValue();
            }
            return null;
        }
    }*/
}