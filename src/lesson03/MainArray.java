package lesson03;

import com.github.apska.webapp.model.Link;
import com.github.apska.webapp.model.Resume;

import java.util.Arrays;

/**
 * Created by APS2
 * on 10.02.2016
 */
public class MainArray {
    public static void main(String[] args){
        int[] array = new int[]{1, 2, 3};
        System.out.println(array.length);
        System.out.println(array.getClass());

        System.out.println(Arrays.toString(array));

        Resume[] resumes = new Resume[10];
        System.out.println(resumes.length);
        System.out.println(resumes.getClass());


        //Link l = new Link();
        //((Object[]) resumes)[0] = l;
    }
}
