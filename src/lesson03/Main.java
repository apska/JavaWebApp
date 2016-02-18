package lesson03;

import com.github.apska.webapp.model.ContactType;
import com.github.apska.webapp.model.Link;

import java.lang.reflect.Field;

/**
 * Created by APS2
 * on 10.02.2016
 */
public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //Contact c = new Contact(ContactType.PHONE, "12321");
        ContactType c2 = ContactType.ICQ;
        //System.out.println(ContactType.ICQ == c2 );

        Field f = Link.class.getDeclaredField("url");
        f.setAccessible(true);

        Link l = new Link("weqeq", "URL");

        System.out.println(l instanceof Link);
        System.out.println(Link.class.isInstance(l));
        System.out.println(f.get(l));
        System.out.println(l.getUrl());

        String a1 = "Ja"+"va";
        String a2 = "Java";
        System.out.println(a1==a2);

        StringBuilder fill = new StringBuilder();
        for(int i=0; i<10; i++){
            fill.append("a");
        }

        System.out.println(fill.toString());
    }
}
