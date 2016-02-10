package lesson03;

import com.github.apska.webapp.model.Contact;
import com.github.apska.webapp.model.ContactType;

/**
 * Created by APS2
 * on 10.02.2016
 */
public class Main {
    public static void main(String[] args){
        Contact c = new Contact(ContactType.PHONE, "12321");
        ContactType c2 = ContactType.ICQ;
        //System.out.println(ContactType.ICQ == c2 );
    }
}
