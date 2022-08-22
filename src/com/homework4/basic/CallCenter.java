package com.homework4.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallCenter {
    private String nameOfCallCenter = "21 CENTURY";

    public CallCenter() {
    }

    public CallCenter(String nameOfCallCenter) {
        this.nameOfCallCenter = nameOfCallCenter;
    }

    public String getNameOfCallCenter() {
        return nameOfCallCenter;
    }

    public void setNameOfCallCenter(String nameOfCallCenter) {
        this.nameOfCallCenter = nameOfCallCenter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallCenter that = (CallCenter) o;

        return nameOfCallCenter != null ? nameOfCallCenter.equals(that.nameOfCallCenter) : that.nameOfCallCenter == null;
    }

    @Override
    public int hashCode() {
        return nameOfCallCenter != null ? nameOfCallCenter.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CallCenter{" +
                "nameOfCallCenter='" + nameOfCallCenter + '\'' +
                '}';
    }

    public static void runCallCenter() throws InterruptedException, ExecutionException {
        CallCenter callCenter = new CallCenter();
        System.out.println("CallCenter " + callCenter.getNameOfCallCenter() + " starts work");

        BlockingQueue<Client> queue = new PriorityBlockingQueue<>(10);
        List<Callable<String>> tasks = new ArrayList();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        tasks.add(new Client(queue));

        for (int i = 1; i <= CallOperator.amountOfCallOperators; i++) {
            tasks.add(new CallOperator(queue, i));
        }

        List<Future<String>> futures = executor.invokeAll(tasks);

        for (Future<String> future : futures) {
            if (future.isDone()) {
                System.out.println(future.get());
            }
        }

        executor.shutdown();
        System.out.println("CallCenter " + callCenter.getNameOfCallCenter() + " finished work");
    }
}
