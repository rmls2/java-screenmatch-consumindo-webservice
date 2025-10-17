package br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class BuscaApiCoinGecko {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scann = new Scanner(System.in);
        System.out.println("Digite o nome da cripto!");
        String busca = scann.nextLine();

        String endereco = "https://api.coingecko.com/api/v3/search?query=" + busca;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }
}
