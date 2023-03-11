package edu.systemprogrammingwithjava;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StreamAPIMultiThreading {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = Arrays.asList("1","2","3","4","5","6","7","8","9","10");
        System.out.println("WITHOUT MULTITHREADING");
        list.stream().forEach(s -> runItNow(s));

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("\nWITH MULTITHREADING");
        list.stream().forEach(s -> executorService.submit( () -> runItNow(s) ));

        executorService.shutdown();
        executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);

        System.out.println("\nWITH PARALLEL STREAM");
        list.stream().parallel().forEach(s ->runItNow(s));
    }

    public static void runItNow(Object s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss:SSS");
        System.out.println(LocalDateTime.now().format(formatter)+" - "+s);
        try {
            Thread.sleep((long)(new Random().nextDouble()*1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
