package client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class APIclient {


    HttpClient client = HttpClient.newHttpClient();

    HttpRequest requisicao = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
}
