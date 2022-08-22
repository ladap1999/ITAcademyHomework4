package com.homework4.basic;

import com.homework4.util.GenerateUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Client implements Callable, Comparable<Client>{
    public static final int amountOfClient =  20;
    private BlockingQueue<Client> queue;
    private String name;
    private String surname;
    private int age;

    public Client() {
    }

    public Client(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Client(BlockingQueue<Client> queue) {
        this.queue = queue;
    }

    public BlockingQueue<Client> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Client> queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (age != client.age) return false;
        if (!queue.equals(client.queue)) return false;
        if (!name.equals(client.name)) return false;
        return surname.equals(client.surname);
    }

    @Override
    public int hashCode() {
        int result = queue.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "queue=" + queue +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public String call() {

        try {
            process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Working time was finished";
    }

    private void process() throws InterruptedException {
        for (int i = 0; i < amountOfClient; i++) {
            Client client = GenerateUtil.generationOfClient();
            System.out.println("Client calls : " + client.getName()+ " " + client.getSurname() + " " + client.getAge() + " years old");
            queue.put(client);
            Thread.sleep(100);
        }
    }

    @Override
    public int compareTo(Client o) {
        return o.getAge() - this.getAge();
    }
}
