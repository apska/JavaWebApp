package com.github.apska.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by APS2
 * on 23.02.2016
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ArrayStorageTest.class, MapStorageTest.class, DataStreamFileStorageTest.class, SerializeFileStorageTest.class})
public class AllStorageTests {
}
