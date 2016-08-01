package com.github.apska.webapp.storage;

import org.junit.Test;
import com.github.apska.webapp.model.Resume;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.LogManager;

/**
 * APS
 * 02.03.2016.
 */
public class ConcurrencyTest {
    static {
        try (FileInputStream logProps = new FileInputStream(new File("logging.properties"))) {
            LogManager.getLogManager().readConfiguration(logProps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArrayStorage() throws Exception {
        run(new ArrayStorage());
    }

    @Test
    public void testSerializedFileStorage() throws Exception {
        run(new SerializeFileStorage(AbstractStorageTest.FILE_STORAGE));
    }

    @Test
    public void testSynchronizedMapStorage() throws Exception {
        run(new SynchronizedMapStorage());
    }

    @Test
    public void testConcurrentMapStorage() throws Exception {
        run(new ConcurrentMapStorage());
    }

    @Test
    public void testMapStorage() throws Exception {
        run(new MapStorage());
    }


    private void run(IStorage storage) throws Exception {
        for (int i = 1; i < 5000; i++) {
            new Thread(() -> {
                Resume r = new Resume("name", "location");
                storage.save(r);
                storage.load(r.getUuid());
                storage.delete(r.getUuid());
                storage.getAllSorted();
            }).start();
        }
        Thread.sleep(5000);
    }
}
