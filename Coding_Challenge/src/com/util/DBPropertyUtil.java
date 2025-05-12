package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getConnectionString(String filename) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(filename));
            return prop.getProperty("db.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
