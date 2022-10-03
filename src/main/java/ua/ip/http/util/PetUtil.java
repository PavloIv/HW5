package ua.ip.http.util;

import com.google.gson.Gson;
import ua.ip.http.entity.Pet;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PetUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static final String PET_ADDRESS = "https://petstore.swagger.io/v2/pet";

    public int createPet(Pet pet) {
        URI uri = URI.create(PET_ADDRESS);
        String requestBody = GSON.toJson(pet);

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

    public int updatePet(Pet pet) {
        URI uri = URI.create(PET_ADDRESS);
        String requestBody = GSON.toJson(pet);

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

    public String findPetForId(int id) {
        URI uri = URI.create(PET_ADDRESS + "/" + id);
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
            return "Pet with id " + id + " not found";
        }
    }

    public String findPetForStatus(String status) {
        URI uri = URI.create(PET_ADDRESS + "/" + "findByStatus?status=" + status);
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
            return "Pet with status " + status + " not found";
        }
    }

    public int deletePet(int id) {
        URI uri = URI.create(PET_ADDRESS + "/" + id);
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
}
