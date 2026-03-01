package client;

import com.google.gson.Gson;
import models.ExchangeResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIclient {


    HttpClient client = HttpClient.newHttpClient();

    public ExchangeResponse getRates(String baseCurrency) throws IOException, InterruptedException {
        String url = "https://v6.exchangerate-api.com/v6/db5cbf7697f64e146db055f2/latest/"+baseCurrency;

        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();


        HttpResponse<String> response = client.send(
                requisicao,
                HttpResponse.BodyHandlers.ofString()
        );

        String json = response.body();
        Gson gson = new Gson();
        ExchangeResponse exchange = gson.fromJson(json,ExchangeResponse.class);
        return exchange;
    }


}
