package com.mintgestao.Infrastructure.util;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class Pong {
    private static final String URL = "https://mintgestao-api.onrender.com/ping";

    public static void pong() {
        HttpClient client = HttpClient.newHttpClient();

        Runnable task = () -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(URL))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                System.out.println("Response status code: " + response.statusCode());
                System.out.println("Response body: " + response.body());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.MINUTES);
    }
}
