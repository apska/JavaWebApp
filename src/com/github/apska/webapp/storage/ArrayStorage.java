package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;
//import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by APS2
 * on 11.02.2016
 */
public class ArrayStorage implements IStorage {
    private static final int LIMIT = 100;
    //protected Logger LOGGER = Logger.getLogger(getClass().getName());
    private static Logger LOGGER = Logger.getLogger(ArrayStorage.class.getName());

    private Resume[] array = new Resume[LIMIT];
    private int idx = 0;
    private int size = 0;

    @Override
    public void clear() {
        /*for (int i=0; i < LIMIT; i++){
            array[i] = null;
        }*/

        LOGGER.info("Clear ArrayStorage of resumes.");

        //тоже самое что и код закомментированный код сверху
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public void save(Resume r) throws WebAppException {
        LOGGER.info("Save resume in ArrayStorage with uuid = " + r.getUuid());

        int idx = getIndex(r.getUuid());

        if (idx != -1) {
            /*try {
                throw new WebAppException("Resume with uuid = " + r.getUuid() + "already exist in ArrayStorage.", r);
            } catch (WebAppException e) {
                //e.printStackTrace();
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new IllegalStateException(e);
            }*/

            throw new WebAppException("Resume with uuid = " + r.getUuid() + "already exist in ArrayStorage.", r);
        }

        array[size++] = r;

        /*for (int i = 0; i < LIMIT; i++) {
            Resume resume = array[i];
            if (array[i] != null) {
                if (r.equals(resume)) {
                    throw new IllegalStateException("Сохраняемое резюме уже существует");
                }
            } else if (idx == -1) {
                idx = i;
            }
        }

        array[idx] = r;*/

        /*for (int i=0; i < LIMIT; i++){
            if (array[i] == null){
                array[i] = r;
            }
        }*/
    }

    @Override
    public void update(Resume r) throws WebAppException {
        LOGGER.info("Update resume in ArrayStorage with uuid = " + r.getUuid());
        int idx = getIndex(r.getUuid());
        if (idx != -1) throw new WebAppException("Resume with uuid = " + r.getUuid() + "not exist in ArrayStorage.", r);
        array[idx] = r;
    }

    @Override
    public void delete(String uuid) throws WebAppException {
        LOGGER.info("Delete resume in ArrayStorage with uuid = " + uuid);
        int idx = getIndex(uuid);
        if (idx != -1) throw new WebAppException("Resume with uuid = " + uuid + "not exist in ArrayStorage.");

        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(array, idx+1, array, idx,
                    numMoved);
        array[--size] = null; // clear to let GC do its work
    }

    @Override
    public Resume load(String uuid) throws WebAppException {
        LOGGER.info("Load resume in ArrayStorage with uuid = " + uuid);
        int idx = getIndex(uuid);
        if (idx != -1) throw new WebAppException("Resume with uuid = " + uuid + "not exist in ArrayStorage.");
        return array[idx];
    }

    @Override
    public Collection<Resume> getAllSorted() {
        Arrays.sort(array, 0, size);
        return Arrays.asList(Arrays.copyOf(array, size));
    }

    @Override
    public int size() {
        return 0;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null){
                if (array[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }

        return -1;
    }
}
