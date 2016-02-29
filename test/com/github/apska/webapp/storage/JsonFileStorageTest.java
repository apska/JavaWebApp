package com.github.apska.webapp.storage;

/**
 * Created by APS2
 * on 23.02.2016
 */
public class JsonFileStorageTest extends AbstractStorageTest {
    {
        storage = new JsonFileStorage("./file_storage");
    }
}