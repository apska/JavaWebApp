package lesson05;

import com.github.apska.webapp.model.Resume;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by APS2
 * on 16.02.2016
 */
public class Calculator {
    public int abs(int value){
        return Math.abs(value);
    }

    public static void main(String[] args) {
        Comparator<Resume> comparator = new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                System.out.println(this.getClass().getName());
                return 0;
            }
        };
        System.out.println(comparator);
    }
}

