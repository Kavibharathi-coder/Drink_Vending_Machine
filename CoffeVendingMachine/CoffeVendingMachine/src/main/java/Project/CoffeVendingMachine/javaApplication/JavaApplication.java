package Project.CoffeVendingMachine.javaApplication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class JavaApplication
{

    public static void main(String[] args)
    {
        //"{\"size\": \"small\"}"
        String url = "http://localhost:8080/coffeeVending/v1/drink/makeTea";
        String requestBody = "{\"size\": \"large\"}";
        String username = "kavi";
        String password = "9791";

        try
        {
            String response = sendPostRequest(url,requestBody, username, password);
            System.out.println(response);
        }
        catch(IOException | InterruptedException e)
        {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    private static String sendGetRequest(String urlString, String username, String password) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        String credentials = username + ":" + password;
        String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Authorization" , authHeaderValue)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private static String sendPostRequest(String urlString, String requestBody, String username, String password) throws IOException, InterruptedException
    {
        HttpClient httpClient = HttpClient.newBuilder().build();

        String credentials = username + ":" + password;
        String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Authorization", authHeaderValue)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private static String sendPutRequest(String urlString, String requestBody, String username, String password) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        String credentials = username + ":" + password;
        String authHeaderValue = "Basic " +  Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Authorization", authHeaderValue)
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static String sendDeleteRequest(String urlString, String username, String password) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        String credentials = username + ":" + password;
        String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Authorization", authHeaderValue)
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}