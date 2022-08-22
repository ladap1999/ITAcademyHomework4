package com.homework4.util;

import java.util.Random;

import com.homework4.basic.Client;
import com.homework4.thread.ClientReader;

public class GenerateUtil {
    public static Client generationOfClient() {
        String[] namesOfClient = ClientReader.getProperty(ClientReader.CLIENTNAME_KEY).split(", ");
        String[] surnamesOfClient = ClientReader.getProperty(ClientReader.CLIENTSURNAME_KEY).split(", ");
        int ageOfClient = randomAction(18, 70);
        Random random = new Random();

        Client client = new Client(namesOfClient[random.nextInt(namesOfClient.length)], surnamesOfClient[random.nextInt(surnamesOfClient.length)], ageOfClient);
        return client;
    }

    public static int randomAction(int min, int max) {
        return (int)(min + Math.random() * ((max - min) + 1));
    }
}
