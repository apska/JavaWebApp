package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.Resume;

import java.util.Collection;

/**
 * Created by APS2
 * on 11.02.2016
 */
public interface IStorage {
    void clear();
    void save(Resume r);
    void update(Resume r);
    void delete(String uuid);

    Resume load(String uuid);

    Collection<Resume> getAllSorted();
    int size();

    boolean isSectionSupported();
}
