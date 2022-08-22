package com.homework4.basic;

import com.homework4.util.GenerateUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CallOperator implements Callable {
    public static final int amountOfCallOperators = 4;
    private BlockingQueue<Client> queue;
    private int id;

    public CallOperator() {
    }

    public CallOperator(BlockingQueue<Client> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    public BlockingQueue<Client> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Client> queue) {
        this.queue = queue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallOperator that = (CallOperator) o;

        if (id != that.id) return false;
        return queue.equals(that.queue);
    }

    @Override
    public int hashCode() {
        int result = queue.hashCode();
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "CallOperator{" +
                "queue=" + queue +
                ", id=" + id +
                '}';
    }

    @Override
    public String call() {
        System.out.println("CallOperator " + this.id + " starts work");

        try {
            int amountOfClientForOneOperator = Client.amountOfClient / amountOfCallOperators;
            for (int i = 0; i < amountOfClientForOneOperator; i++) {
                Client client = queue.poll(2, TimeUnit.SECONDS);
                int age = client.getAge();
                String name = client.getName();
                process(name, age);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Operator " + this.id + " finished work";
    }

    private void process(String name, int age) throws InterruptedException {
        System.out.println("CallOperator " + this.id + " spoke with: " + name + " " + age + " years old");
        Thread.sleep(500);
    }
}
