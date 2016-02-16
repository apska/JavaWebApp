package com.github.apska.webapp.storage;

import com.github.apska.webapp.model.Resume;

import java.util.Collection;

/**
 * Created by APS2
 * on 16.02.2016
 */
public class MapStorage extends AbstractStorage {

    @Override
    protected void doSave(Resume r) {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume load(String uuid) {
        return null;
    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
