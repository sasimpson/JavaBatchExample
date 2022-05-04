package com.ke5eo;

import com.alibaba.fastjson.JSON;
import com.ke5eo.models.User;
import com.ke5eo.models.UserResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

public class RandomUser {
        public static UserResponse MakeRequest(int count) throws IOException, URISyntaxException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://randomuser.me/api/?results=" + count + "&nat=us&inc=dob,name&noinfo"))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return JSON.parseObject(response.body(), UserResponse.class);
    }

    public static ArrayList<User> MakeUserList(int count) {
        UserResponse resp;
        try {
            resp = RandomUser.MakeRequest(count);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        ArrayList<User> users = new ArrayList<>();
        resp.getResults().forEach(result -> {
            User u = new User(result.getFullName(), result.getDob().getAge());

            users.add(u);
        });

        return users;
    }
}
