package com.github.apska.webapp.storage;

import com.github.apska.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by APS2
 * on 16.02.2016
 */
public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getContext(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String uuid) {
        return map.containsKey(uuid);
    }

    @Override
    protected void doClear() {
        map.clear();
    }

    @Override
    protected void doSave(String uuid, Resume r) {
        map.put(uuid, r);
    }

    @Override
    protected void doUpdate(String uuid, Resume r) {
        map.put(uuid, r);
    }

    @Override
    protected Resume doLoad(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
