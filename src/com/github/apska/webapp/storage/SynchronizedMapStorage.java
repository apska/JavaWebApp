package com.github.apska.webapp.storage;

import com.github.apska.webapp.model.Resume;

import java.util.*;

/**
 * APS
 * 02.03.2016.
 */

public class SynchronizedMapStorage extends AbstractStorage<String> {

    private Map<String, Resume> MAP = Collections.synchronizedMap(new HashMap<>());

    @Override
    protected String getContext(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String uuid) {
        return MAP.containsKey(uuid);
    }

    @Override
    protected void doClear() {
        MAP.clear();
    }

    @Override
    protected void doSave(String uuid, Resume r) {
        MAP.put(uuid, r);
    }

    @Override
    protected void doUpdate(String uuid, Resume r) {
        MAP.put(uuid, r);
    }

    @Override
    protected Resume doLoad(String uuid) {
        return MAP.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        MAP.remove(uuid);
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(MAP.values());
    }

    @Override
    public int size() {
        return MAP.size();
    }
}