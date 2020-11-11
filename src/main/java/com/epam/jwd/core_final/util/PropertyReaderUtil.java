package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {
    private static PropertyReaderUtil propertyReaderUtil;
    private static final Properties properties = new Properties();
    static {
        loadProperties();
    }
    private PropertyReaderUtil() {
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */

    public static void loadProperties() {
        final String propertiesFileName = "resources/application.properties";
        try(FileInputStream fileInputStream = new FileInputStream("src/main/" + propertiesFileName)){
            properties.load(fileInputStream);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Properties getProperties(){
        return properties;
    }
    public static PropertyReaderUtil getPropertyReaderUtil(){
        if(propertyReaderUtil == null){
            propertyReaderUtil = new PropertyReaderUtil();
        }
        return propertyReaderUtil;
    }
}
