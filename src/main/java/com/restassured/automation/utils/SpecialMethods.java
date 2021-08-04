package com.restassured.automation.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SpecialMethods {

    public static Properties properties;

    public static void configPropertiesRead(){
        properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Confidencial\\conf.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
