package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public Properties properties = new Properties();

//    public Config(String configFile) {
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFile)) {
//            if (input == null) {
//                System.out.println("Sorry, unable to find " + configFile);
//                return;
//            }
//            properties.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public String getProperty(String key) {
//        return properties.getProperty(key);
//    }
}
