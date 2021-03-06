package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.ContactType;
import com.github.apska.webapp.model.Organization;
import com.github.apska.webapp.model.Resume;
import com.github.apska.webapp.model.SectionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by APS2
 * on 16.02.2016
 */
abstract public class AbstractStorageTest {
    public static final String FILE_STORAGE = "./file_storage";
    private Resume R1, R2, R3;

    protected IStorage storage;

    @BeforeClass
    public static void beforeClass() {
        //тоже самое что и static {}
    }

    @Before
    public void before() {
        R1 = new Resume("Полное Имя 1", "location1");
        R1.addContact(ContactType.MAIL, "apska@mail.ru");
        R1.addContact(ContactType.PHONE, "11111");

        if (storage.isSectionSupported()) {
            R1.addObjective("Objective1");
            R1.addMultiTextSection(SectionType.ACHIEVEMENT, "Achivment11", "Achivment12");
            R1.addMultiTextSection(SectionType.QUALIFICATIONS, "Java", "Sql");
            R1.addOrganizationSection(SectionType.EXPERIENCE,
                    new Organization("Organization11", null,
                            new Organization.Period(LocalDate.of(2005, Month.JANUARY, 1), Organization.Period.NOW, "position1", "content1"),
                            new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2")),
                    new Organization("Organization12", "http://Organization12.ru"));

            R1.addOrganizationSection(SectionType.EDUCATION,
                    new Organization("Institute", null,
                            new Organization.Period(1996, Month.JANUARY, 2001, Month.DECEMBER, "aspirant", "IT facultet"),
                            new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                    new Organization("Organization12", "http://Organization12.ru"));
        }

        R2 = new Resume("Полное Имя 2", "location2");
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");

        R3 = new Resume("Полное Имя 3", "");



        storage.clear();

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
        /*Resume[] src = new Resume[]{R1, R2, R3};
        Arrays.sort(src);
        Assert.assertArrayEquals(src, storage.getAllSorted().toArray());*/

        List<Resume> list = Arrays.asList(R1, R2, R3);
        Collections.sort(list, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                return 0;
            }
        });
        Assert.assertEquals(list, storage.getAllSorted());
    }

    @org.junit.Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @org.junit.Test(expected = WebAppException.class)
    public void testDeleteMissed() throws Exception {
        storage.delete("dummy");
    }

    @org.junit.Test(expected = WebAppException.class)
    public void testSavePresented() throws Exception {
        storage.save(R1);
    }

    @org.junit.Test(expected = WebAppException.class)
    public void testUpdateMissed() throws Exception {
        Resume resume = new Resume("dummy", "fullName_U1", "location_U1");
        storage.update(resume);
    }
}
