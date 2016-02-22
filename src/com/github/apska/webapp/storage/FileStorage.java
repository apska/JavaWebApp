package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.Resume;

import java.io.*;
import java.util.List;

/**
 * Created by APS2
 * on 21.02.2016
 */
//TODO вынести дублирующий код из подклассов
abstract public class FileStorage extends AbstractStorage<File> {
    private File dir;

    public FileStorage(String path) {
        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite())
            throw new IllegalArgumentException("'" + path + "' is not directory or is not writable");
    }

    @Override
    protected void doClear() {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    protected File getContext(String fileName) {
        return new File(dir, fileName);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(File file, Resume r) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new WebAppException("Couldn't create file " + file.getAbsolutePath(), r, e);
        }

        write(file, r);
    }

    abstract protected void write(File file, Resume r);

    abstract protected Resume read(File file);

    @Override
    protected void doUpdate(File file, Resume r) {
        write(file, r);
    }

    @Override
    protected Resume doLoad(File file) {
        return read(file);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new WebAppException("File " + file.getAbsolutePath() + " can not be deleted.");
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        //TODO read all
        return null;
    }

    @Override
    public int size() {
        String[] list = dir.list();
        if(list == null)
            throw new WebAppException("Couldn't read directory " + dir.getAbsolutePath());
        return list.length;
    }
}
