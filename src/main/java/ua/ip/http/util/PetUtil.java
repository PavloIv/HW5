package ua.ip.http.util;

import com.google.gson.Gson;
import ua.ip.http.entity.Pet;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PetUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static final String PET_ADDRESS = "https://petstore.swagger.io/v2/pet";
    private static final String boundary = Long.toString(System.currentTimeMillis());
    private static HttpURLConnection createConnection(String urlPath) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");

        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        return httpURLConnection;
    }

    public void uploadImage(int id,String fileAdress) {
        File file = new File(fileAdress);

        String url = PET_ADDRESS + "/" + id + "/uploadImage";

        HttpURLConnection connection = null;
        OutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            connection = createConnection(url);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            outputStream = new DataOutputStream(connection.getOutputStream());

            String header = "--" + boundary + "\r\n";

            header += "Content-Disposition: form-data;name=\"file\";"
                    + "filename=\"" + file.getName() + "\"" + "\r\n" + "\r\n";

            outputStream.write(header.getBytes());

            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }

            outputStream.write("\r\n".getBytes());
            String footer = "\r\n--" + boundary + "--\r\n";
            outputStream.write(footer.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                StringBuffer response = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response);
            } else {
                System.err.println("Upload error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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

    public int updateForm(int id, String name, String status) {
        URI uri = URI.create(PET_ADDRESS + "/" + id + "?name=" + name + "&status=" + status );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("Content-type", "application/x-www-form-urlencoded")
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
