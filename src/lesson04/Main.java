package lesson04;

import com.github.apska.webapp.model.Organization;
import com.github.apska.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by APS2
 * on 15.02.2016
 */
public class Main {
    public static void main(String[] args){
        // статический внутренний класс
        new Organization.Period();

        // не статический внутренний класс
        //new Organization().new Period();

        Map<String, Resume> map = new HashMap<String, Resume>();
        map.put("uuid", new Resume("uuid","",""));

        //неэффективный обход коллекции
        //for(String key: map.keySet()){

        //эфферктивный обход коллекции
        for(Map.Entry<String, Resume> entry : map.entrySet()){
            System.out.println(entry.getKey()+ "->"+ entry.getValue());
        }
    }
}
