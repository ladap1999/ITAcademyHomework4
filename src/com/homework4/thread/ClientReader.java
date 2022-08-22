package com.homework4.thread;

import java.util.ResourceBundle;

public class ClientReader {
    public static final String CLIENT_FILE_NAME = "client";
    public static final String CLIENTNAME_KEY = "namesOfClients";
    public static final String CLIENTSURNAME_KEY = "surnamesOfClients";

    public static String getProperty(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(CLIENT_FILE_NAME);

        return bundle.getString(key);
    }
}
