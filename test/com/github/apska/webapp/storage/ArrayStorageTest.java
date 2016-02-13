package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.Contact;
import com.github.apska.webapp.model.ContactType;
import com.github.apska.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by APS2
 * on 11.02.2016
 */
public class ArrayStorageTest {
    private Resume R1, R2, R3;

    private ArrayStorage storage = new ArrayStorage();

    @BeforeClass
    public static void beforeClass() {
        //тоже самое что и static {}
    }

    @Before
    public void before() throws WebAppException {
        storage.clear();

        R1 = new Resume("Полное Имя 1", "location1");
        R1.addContact(new Contact(ContactType.MAIL, "apska@mail.ru"));
        R1.addContact(new Contact(ContactType.PHONE, "11111"));

        R2 = new Resume("Полное Имя 2", null);
        R2.addContact(new Contact(ContactType.SKYPE, "skype2"));
        R2.addContact(new Contact(ContactType.PHONE, "22222"));

        R3 = new Resume("Полное Имя 3", null);

        storage.save(R3);
        storage.save(R1);
        storage.save(R2);
    }

    @org.junit.Test
    public void testSave() throws Exception {

    }

    @org.junit.Test
    public void testClear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @org.junit.Test
    public void testUpdate() throws Exception {
        R2.setFullName("Updated N2");
        storage.update(R2);
        Assert.assertEquals(R2, storage.load(R2.getUuid()));
    }

    @org.junit.Test(expected = WebAppException.class)
    public void testDeleteNotFound() throws Exception {
        storage.load("dummy");
    }

    @org.junit.Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());
    }

    @org.junit.Test
    public void testLoad() throws Exception {
        Assert.assertEquals(R1, storage.load(R1.getUuid()));
        Assert.assertEquals(R2, storage.load(R2.getUuid()));
        Assert.assertEquals(R3, storage.load(R3.getUuid()));
    }

    @org.junit.Test
    public void testGetAllSorted() throws Exception {
        Resume[] src = new Resume[]{R1, R2, R3};
        Arrays.sort(src);

        Assert.assertArrayEquals(src, storage.getAllSorted().toArray());
    }

    @org.junit.Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }
}