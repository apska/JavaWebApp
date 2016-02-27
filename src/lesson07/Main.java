package lesson07;

import java.util.Date;

/**
 * Created by APS2
 * on 23.02.2016
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Date());
        System.out.println(new Date().getTime());

        System.out.println(System.currentTimeMillis());

        System.out.println(new Date(Long.MIN_VALUE));
        System.out.println(new Date(Long.MAX_VALUE));
    }
}
