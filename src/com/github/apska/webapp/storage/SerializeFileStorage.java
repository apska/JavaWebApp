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
        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(r);
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), r, e);
        }

    }

    protected Resume read(File file) {
        Resume r = new Resume();
        try (InputStream is = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(is)){
            return (Resume) ois.readObject();
        }catch (IOException e){
            throw new WebAppException("Couldn't read file "+ file.getAbsolutePath(), e);
        } catch (ClassNotFoundException e) {
            throw new WebAppException("Error read resume from file.", e);
        }
    }
}
