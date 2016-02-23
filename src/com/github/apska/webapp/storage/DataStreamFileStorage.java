package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.*;

import java.io.*;
import java.util.*;

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
        try (FileOutputStream fos = new FileOutputStream(file); final DataOutputStream dos = new DataOutputStream(fos)) {
            writeString(dos, resume.getFullName());

            writeString(dos, resume.getLocation());

            writeString(dos, resume.getHomePage());

            Map<ContactType, String> contacts = resume.getContacts();

            /*writeCollection(dos, contacts.entrySet(), new ElementWriter<Map.Entry<ContactType, String>>() {
                @Override
                public void write(Map.Entry<ContactType, String> entry) throws IOException {
                    dos.writeInt(entry.getKey().ordinal());
                    writeString(dos, entry.getValue());
                }
            });*/

            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, entry.getValue());
            });

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                writeString(dos, type.name());

                switch (type) {
                    case OBJECTIVE:
                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        /*writeCollection(dos, ((MultyTextSection) section).getValues(), new ElementWriter<String>() {
                            @Override
                            public void write(String value) throws IOException {
                                writeString(dos, value);
                            }
                        });*/

                        writeCollection(dos, ((MultyTextSection) section).getValues(), value -> writeString(dos, value));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:

                        break;

                }
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

            final int sectionSize = dis.readInt();
            for(int i=0; i < sectionSize; i++){
                SectionType sectionType = SectionType.valueOf(readString(dis));
                switch (sectionType){
                    case OBJECTIVE:
                        r.addObjective(readString(dis));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        r.addSection(sectionType, new MultyTextSection(readList(dis, () -> readString(dis))));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        break;
                }
            }
            return r;
        } catch (IOException e) {
            throw new WebAppException("Couldn't read file " + file.getAbsolutePath(), e);
        }
    }

    private void writeString(DataOutputStream dos, String str) throws IOException {
        dos.writeUTF(str == null ? NULL : str);
    }

    private String readString(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;
    }

    private interface ElementWriter<T>{
        void write(T t) throws IOException;
    }

    private interface ElementReader<T>{
        T read() throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item: collection) {
            writer.write(item);
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);

        for (int i=0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }
}
