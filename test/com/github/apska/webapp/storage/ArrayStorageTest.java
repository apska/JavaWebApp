package com.github.apska.webapp.storage;

import com.github.apska.webapp.model.Contact;
import com.github.apska.webapp.model.ContactType;
import com.github.apska.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by APS2
 * on 11.02.2016
 */
public class ArrayStorageTest {
    private static Resume R1, R2, R3;

    private ArrayStorage storage = new ArrayStorage();

    static {
        R1 = new Resume("Полное Имя 1", "location1");
        R1.addContact(new Contact(ContactType.MAIL, "apska@mail.ru"));
        R1.addContact(new Contact(ContactType.PHONE, "11111"));

        R2 = new Resume("Полное Имя 2", null);
        R2.addContact(new Contact(ContactType.SKYPE, "skype2"));
        R2.addContact(new Contact(ContactType.PHONE, "22222"));

        R3 = new Resume("Полное Имя 3", null);
    }

    @BeforeClass
    public static void beforeClass(){
        //тоже самое что и static {}
    }

    @Before
    public void before(){
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @org.junit.Test
    public void testSave() throws Exception {

    }

    @org.junit.Test
    public void testClear() throws Exception {

    }

    @org.junit.Test
    public void testUpdate() throws Exception {

    }

    @org.junit.Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());

        Assert.assertEquals(2, storage.size());
        Assert.assertEquals(null, storage.load(R1.getUuid()));
    }

    @org.junit.Test
    public void testLoad() throws Exception {

    }

    @org.junit.Test
    public void testGetAllSorted() throws Exception {

    }

    @org.junit.Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }
}