package ua.ip.http.util;

import com.google.gson.Gson;
import ua.ip.http.entity.Store;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StoreUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static final String STORE_ADDRESS = "https://petstore.swagger.io/v2/store/order";

    public  int purchasingPet(Store store) {
        URI uri = URI.create(STORE_ADDRESS);
        String requestBody = GSON.toJson(store);

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

    public  String  findPunches(int id) {
        URI uri = URI.create(STORE_ADDRESS + "/" + id);
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        if(response.statusCode()/100 == 2){return response.body();
        }else {
            return "Punches with id " + id + " not found";
        }
    }

    public  int deleteOrder(int id) {
        URI uri = URI.create(STORE_ADDRESS + "/" + id);
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

    public  String inventories() {
        URI uri = URI.create("https://petstore.swagger.io/v2/store/inventory");
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


}
