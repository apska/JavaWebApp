package lesson06;

import com.github.apska.webapp.model.Resume;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by APS2
 * on 20.02.2016
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf(10) == Integer.valueOf(10));
        System.out.println(Integer.valueOf(1000).equals(Integer.valueOf(1000)));

        //List<String> es = Collections.emptyList();
        print(Collections.emptyList());
        print(Collections.<List<String>>emptyList());
        List<Resume> er = Collections.emptyList();

        new ArrayList<Resume>(){
            {
                add(new Resume());
                System.out.println(getClass());
            }
        };
    }

    public static <T> void print(List<T> list){
        System.out.println(list.toString());
    }
}
