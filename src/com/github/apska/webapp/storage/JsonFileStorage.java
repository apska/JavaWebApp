package com.github.apska.webapp.storage;

import com.github.apska.webapp.model.Resume;
import com.github.apska.webapp.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by APS2
 * on 29.02.2016
 */
public class JsonFileStorage extends FileStorage {
    public JsonFileStorage(String path){
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume r) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)){
            JsonParser.write(r, w);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)){
            return JsonParser.read(r, Resume.class);
        }
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
