package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.ContactType;
import com.github.apska.webapp.model.Resume;
import com.github.apska.webapp.model.Section;
import com.github.apska.webapp.model.SectionType;

import java.io.*;
import java.util.Map;

/**
 * Created by APS2
 * on 21.02.2016
 */
public class DataStreamFileStorage extends FileStorage {
    private static final String NULL = "null";

    public DataStreamFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume resume) {
        try (FileOutputStream fos = new FileOutputStream(file); DataOutputStream dos = new DataOutputStream(fos)) {
            writeString(dos, resume.getFullName());

            writeString(dos, resume.getLocation());

            writeString(dos, resume.getHomePage());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, entry.getValue());
            }

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, type.name());
            }






        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), resume, e);
        }

    }

    protected Resume read(File file) {
        Resume r = new Resume(file.getName());
        try (InputStream is = new FileInputStream(file); DataInputStream dis = new DataInputStream(is)) {
            r.setFullName(readString(dis));
            r.setLocation(readString(dis));
            r.setHomePage(readString(dis));

            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                r.addContact(ContactType.VALUES[dis.readInt()], readString(dis));
            }

            //TODO section read
        } catch (IOException e) {
            throw new WebAppException("Couldn't read file " + file.getAbsolutePath(), e);
        }
        return r;
    }

    private void writeString(DataOutputStream dos, String str) throws IOException {
        dos.writeUTF(str == null ? NULL : str);
    }

    private String readString(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;
    }
}
