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
    void save(Resume r) throws WebAppException;
    void update(Resume r) throws WebAppException;
    void delete(String uuid) throws WebAppException;

    Resume load(String uuid) throws WebAppException;

    Collection<Resume> getAllSorted();
    int size();
}
