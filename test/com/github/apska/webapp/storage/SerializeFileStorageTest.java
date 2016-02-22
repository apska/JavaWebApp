package com.github.apska.webapp.storage;


/**
 * Created by APS2
 * on 16.02.2016
 */
public class SerializeFileStorageTest extends AbstractStorageTest {
    {
        storage = new SerializeFileStorage(".\\file_storage");
    }
}