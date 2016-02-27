package com.github.apska.webapp.storage;

import static org.junit.Assert.*;

/**
 * Created by APS2
 * on 23.02.2016
 */
public class XmlFileStorageTest extends AbstractStorageTest {
    {
        storage = new XmlStorage("./file_storage");
    }
}