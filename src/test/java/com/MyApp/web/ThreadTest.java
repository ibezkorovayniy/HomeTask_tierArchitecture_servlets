package com.MyApp.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;

public class ThreadTest {
    private long first;
    private String url1 = "https://www.google.com.ua/";
    private String url2 = "https://www.youtube.com/";
    private String url3 = "https://github.com/";
    private String url4 = "https://www.facebook.com/profile.php?id=100011863786752";
    private String url5 = "https://medium.com/";
    private String url6 = "http://localhost:8080/servlet/login";


    private StringBuilder handleWebPage(String url) {
        try {
            URL targetUrl = new URL(url);
            first = System.nanoTime();
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) targetUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome/41.0.2228.0");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String nextString;

            while ((nextString = reader.readLine()) != null) {
                result.append(nextString);
            }

            long last = System.nanoTime();
            System.out.println(url + " - " + ((last - first) / 1000000));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void getTimeConnection1() {
        List<String> list = new ArrayList<>();
        list.add(url1);
        //list.add(url2);
        //list.add(url3);
        list.add(url4);
        //list.add(url5);
        //list.add(url6);

        for(int i=0; i<10;i++) {
            long a =System.nanoTime();
            list.stream()
                    .map(this::handleWebPage)
                    .parallel().collect(Collectors.toList());
            long b = System.nanoTime();
            System.out.println((b - a) / 1000000);
        }
    }

    @Test
    public void getTimeConnection2() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        executorService.execute(() -> handleWebPage(url1));
        executorService.execute(() -> handleWebPage(url2));
        executorService.execute(() -> handleWebPage(url3));
        executorService.execute(() -> handleWebPage(url4));
        executorService.execute(() -> handleWebPage(url5));
        //executorService.execute(() -> handleWebPage(url6));
        executorService.shutdown();
    }

    @Test
    public void getTimeConnection3() {
        Thread th1 = new Thread() {
            public void run() {
                handleWebPage(url1);
            }
        };
        th1.start();
    }

    public static void main(String[] args) {
        new ThreadTest().getTimeConnection2();
    }
}
