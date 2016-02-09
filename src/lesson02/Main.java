package lesson02;

import com.github.apska.webapp.model.Link;

/**
 * Created by APS2 on 02.02.2016.
 */
public class Main {
    public static void main(String[] args) {
        Link l1 = new Link("YANDEX", "yandex.ru");

        Link l2 = l1;

        Link l3 = new Link(l1);

        System.out.println(l1.getClass());

        System.out.println(l1.equals(l2));
        System.out.println(l1.equals(l3));
        System.out.println(l1);

    }
}
