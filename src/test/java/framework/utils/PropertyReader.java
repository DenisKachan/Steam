package framework.utils;

import framework.BaseEntity;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader extends BaseEntity {

    private final Properties properties;

    public PropertyReader(String propertiesFile) {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}