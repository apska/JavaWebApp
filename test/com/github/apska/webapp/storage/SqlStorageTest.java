package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppConfig;

/**
 * Created by APS2
 * on 27.08.2016
 */
public class SqlStorageTest extends AbstractStorageTest {
    {
        storage =  WebAppConfig.get().getStorage();
    }
}
