package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.ContactType;
import com.github.apska.webapp.model.Resume;

import java.io.*;
import java.util.Map;

/**
 * Created by APS2
 * on 21.02.2016
 */
public class SerializeFileStorage extends FileStorage {
    private File dir;

    public SerializeFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume r) {
        try (FileOutputStream fos = new FileOutputStream(file); DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeUTF(r.getFullName());
            dos.writeUTF(r.getLocation());
            dos.writeUTF(r.getHomePage());

            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                dos.writeUTF(entry.getValue());
            }

            dos.writeUTF("SECTIONS");

            //TODO section OUT
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), r, e);
        }

    }

    protected Resume read(File file) {
        Resume r = new Resume();
        try (InputStream is = new FileInputStream(file); DataInputStream dis = new DataInputStream(is)){
            r.setFullName(dis.readUTF());
            r.setLocation(dis.readUTF());
            r.setHomePage(dis.readUTF());

            int contactsSize = dis.readInt();
            for(int i=0; i<contactsSize; i++){
                r.addContact(ContactType.VALUES[dis.readInt()], dis.readUTF());
            }

            //TODO section read
        }catch (IOException e){
            throw new WebAppException("Couldn't read file "+ file.getAbsolutePath(), e);
        }
        return null;
    }
}
