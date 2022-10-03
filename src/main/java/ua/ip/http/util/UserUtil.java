package ua.ip.http.util;

import com.google.gson.Gson;
import ua.ip.http.entity.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UserUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    private static final String USER_ADDRESS = "https://petstore.swagger.io/v2/user";

    public int createUser(User user) {
        URI uri = URI.create(USER_ADDRESS);
        String requestBody = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.statusCode();
    }

    public String findUser(String userName) {
        URI uri = URI.create(USER_ADDRESS + "/" + userName);
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        if (response.statusCode() / 100 == 2) {
            return response.body();
        } else {
            return "User with username " + userName + " not found";
        }
    }

    public int updateUser(String userName, User user) {
        URI uri = URI.create(USER_ADDRESS + "/" + userName);
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.statusCode();
    }

    public int deleteUser(String userName) {
        URI uri = URI.create(USER_ADDRESS + "/" + userName);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .DELETE()
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.statusCode();
    }

    public String loginUser(String userName, String password) {
        URI uri = URI.create(USER_ADDRESS + "/login?username=" + userName + "&password=" + password);
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.body();
    }

    public String logoutUser() {
        URI uri = URI.create(USER_ADDRESS + "/logout");
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.body();
    }

    public int createUserFromArray(User[] user) {
        URI uri = URI.create(USER_ADDRESS + "/createWithArray");
        String requestBody = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.statusCode();
    }

    public int createUserFromList(List user) {
        URI uri = URI.create(USER_ADDRESS + "/createWithList");
        String requestBody = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.statusCode();
    }
}
