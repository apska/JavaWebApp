package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by APS2
 * on 13.02.2016
 */
abstract public class AbstractStorage<C> implements IStorage {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void clear() {
        logger.info("Clear Resumes Storage.");
        doClear();
    }

    protected abstract void doClear();

    protected abstract C getContext(String uuid);
    protected abstract boolean isExist(C ctx);

    @Override
    public void save(Resume r) {
        logger.info("Save resume in ArrayStorage with uuid = " + r.getUuid());

        C ctx = getContext(r);
        if (isExist(ctx)) {
            throw new WebAppException("Resume with uuid \"" + r.getUuid() + "\" already exist.", r);
        }

        doSave(ctx, r);
    }

    protected abstract void doSave(C ctx, Resume r);

    @Override
    public void update(Resume r) {
        logger.info("Update resume in ArrayStorage with uuid = " + r.getUuid());

        C ctx = getContext(r);
        if (!isExist(ctx)) {
            throw new WebAppException("Resume with uuid \"" + r.getUuid() + "\" not exist.", r);
        }

        doUpdate(ctx, r);
    }

    protected abstract void doUpdate(C ctx, Resume r);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume in ArrayStorage with uuid = " + uuid);

        C ctx = getContext(uuid);
        if (!isExist(ctx)) {
            throw new WebAppException("Resume with uuid \"" + uuid + "\" not exist.", uuid);
        }

        return doLoad(ctx);
    }

    protected abstract Resume doLoad(C ctx);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume from ArrayStorage with uuid = " + uuid);

        C ctx = getContext(uuid);
        if (!isExist(ctx)) {
            throw new WebAppException("Resume with uuid \"" + uuid + "\" not exist.", uuid);
        }

        doDelete(ctx);
    }

    protected abstract void doDelete(C ctx);

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

    private C getContext(Resume r){
        return getContext(r.getUuid());
    }

    @Override
    public boolean isSectionSupported() {
        return true;
    }
}
