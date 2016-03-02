package com.github.apska.webapp.storage;

import com.github.apska.webapp.model.*;
import com.github.apska.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by APS2
 * on 23.02.2016
 */
public class XmlFileStorage extends FileStorage {
    private XmlParser xmlParser;

    public XmlFileStorage(String path) {
        super(path);
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class,
                OrganizationSection.class, TextSection.class, Organization.Period.class, MultyTextSection.class);
    }

    @Override
    protected void write(OutputStream os, Resume r) throws IOException {
        try(Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)){
            xmlParser.marshall(r, w);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try(Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)){
            return xmlParser.unmarshall(r);
        }
    }
}
