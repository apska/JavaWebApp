package lesson05;

import com.github.apska.webapp.model.ContactType;
import com.github.apska.webapp.model.Resume;

import java.util.*;

/**
 * Created by APS2
 * on 19.02.2016
 */
public class Main {
    public static void main(String[] args) {
        Comparator<Resume> comparator = new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                System.out.println(this.getClass().getName());
                return 0;
            }
        };
        System.out.println(comparator);

        Resume R1, R2, R3;

        R1 = new Resume("Полное Имя 1", "location1");
        R1.addContact(ContactType.MAIL, "apska@mail.ru");
        R1.addContact(ContactType.PHONE, "11111");

        R2 = new Resume("Полное Имя 2", null);
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");

        R3 = new Resume("Полное Имя 3", null);

        Resume R4 = new Resume(){
            @Override
            public String getUuid() {
                return "uuid_R4";
            }
        };

        List<Resume> resumes = Arrays.asList(R1, R2, R3);

        print(resumes);
        print(Collections.singletonList(R4));
    }

    public static <T extends Resume> void print(List<T> list){
        list.forEach(r -> System.out.println(r.getUuid()));
    }

}
