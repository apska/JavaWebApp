package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by APS2
 * on 13.02.2016
 */
abstract public class AbstractStorage implements IStorage {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void clear() {
        logger.info("Clear Resumes Storage.");
        doClear();
    }

    protected abstract void doClear();

    protected abstract boolean exist(String uuid);

    @Override
    public void save(Resume r) {
        logger.info("Save resume in ArrayStorage with uuid = " + r.getUuid());

        if (exist(r.getUuid())) {
            throw new WebAppException("Resume with uuid \"" + r.getUuid() + "\" already exist.", r);
        }

        doSave(r);
    }

    protected abstract void doSave(Resume r);

    @Override
    public void update(Resume r) {
        logger.info("Update resume in ArrayStorage with uuid = " + r.getUuid());

        if (!exist(r.getUuid())) {
            throw new WebAppException("Resume with uuid \"" + r.getUuid() + "\" not exist.", r);
        }

        doUpdate(r);
    }

    protected abstract void doUpdate(Resume r);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume in ArrayStorage with uuid = " + uuid);

        if (!exist(uuid)) {
            throw new WebAppException("Resume with uuid \"" + uuid + "\" not exist.", uuid);
        }

        return doLoad(uuid);
    }

    protected abstract Resume doLoad(String uuid);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume from ArrayStorage with uuid = " + uuid);

        if (!exist(uuid)) {
            throw new WebAppException("Resume with uuid \"" + uuid + "\" not exist.", uuid);
        }

        doDelete(uuid);
    }

    protected abstract void doDelete(String uuid);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("Start getAllSorted");
        List<Resume> list = doGetAll();

        //лямды, пропускают брейкпоинты () -> {}; Resume o1 -> {}; (Resume o1, Resume o2) -> {}
        /*Collections.sort(list, (Resume o1, Resume o2) -> {
            int cmp = o1.getFullName().compareTo(o2.getFullName());
            if(cmp !=0) return cmp;

            return o1.getUuid().compareTo(o2.getUuid());
        });*/

        Collections.sort(list, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                int cmp = o1.getFullName().compareTo(o2.getFullName());
                if(cmp !=0) return cmp;

                return o1.getUuid().compareTo(o2.getUuid());

            }
        });
        //return Collections.singletonList(new Resume());
        return list;
    }

    protected abstract List<Resume> doGetAll();

    @Override
    public abstract int size();
}
