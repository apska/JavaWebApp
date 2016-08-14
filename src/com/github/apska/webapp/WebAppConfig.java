package com.github.apska.webapp;

import com.github.apska.webapp.storage.IStorage;
import com.github.apska.webapp.storage.XmlFileStorage;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogManager;

/**
 * Created by APS2
 * on 05.08.2016
 */
public class WebAppConfig {
    private static final WebAppConfig INSTANCE = new WebAppConfig();

    private IStorage storage;
    private Properties appProps;

    public static WebAppConfig get(){
        return INSTANCE;
    }

    public IStorage getStorage(){
        return storage;
    }

    private WebAppConfig(){
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("logging.properties");
             InputStream webAppProperties = getClass().getClassLoader().getResourceAsStream("webapp.properties");
        ) {
            LogManager.getLogManager().readConfiguration(is);
            appProps = new Properties();
            appProps.load(webAppProperties);

            storage = new XmlFileStorage(appProps.getProperty("storage.dir"));

            appProps.getProperty("db.url");
            appProps.getProperty("db.user");
            appProps.getProperty("db.password");

        }catch(Exception e){
            throw new IllegalStateException(e);
        }
    }

}
