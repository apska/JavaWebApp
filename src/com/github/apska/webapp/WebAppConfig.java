package com.github.apska.webapp;

import com.github.apska.webapp.storage.IStorage;
import com.github.apska.webapp.storage.XmlFileStorage;

import java.io.InputStream;
import java.util.logging.LogManager;

/**
 * Created by APS2
 * on 05.08.2016
 */
public class WebAppConfig {
    private static final WebAppConfig INSTANCE = new WebAppConfig();

    private IStorage storage;

    public static WebAppConfig get(){
        return INSTANCE;
    }

    public IStorage getStorage(){
        return storage;
    }

    private WebAppConfig(){
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
            storage = new XmlFileStorage("C:\\WEB_APP_JAVA\\JavaWebApp\\file_storage");
        }catch(Exception e){
            throw new IllegalStateException(e);
        }
    }

}
