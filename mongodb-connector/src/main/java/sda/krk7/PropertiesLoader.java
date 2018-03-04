package sda.krk7;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private static final String PROPERTY_FILE_NAME = "connection.properties";

    Properties properties = new Properties();

    PropertiesLoader (){
        this.load();
    }

    public void load() {
        InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
        try {
            properties.load(input);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String getHost() {
        return properties.getProperty("host");
    }

    public int getPort() {
        return Integer.parseInt(properties.getProperty("port"));
    }

    public String getUser() {
        return properties.getProperty("user");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getDB() {
        return properties.getProperty("db");
    }

}
