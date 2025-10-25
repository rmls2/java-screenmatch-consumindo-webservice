package br.com.alura.screenmatch.exercicios;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsultarUsuario {

    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);
        System.out.println("Digite seu login: ");
        String usuario = scann.nextLine();
        String busca = "https://api.github.com/users/" + usuario;

        try{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request  = HttpRequest.newBuilder()
                .uri(URI.create(busca))
                .build();
        HttpResponse <String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        if (response.statusCode() == 404) {
            throw new ErroConsultaGitHubException("Usuario não encontrado no gitHub");
        }
        Gson gson = new Gson();
        UserGitHub user = gson.fromJson(json, UserGitHub.class);
        System.out.println(user);

        } catch (ErroConsultaGitHubException e){
            System.out.println(e.getMessage());
        } catch (IOException | InterruptedException e){
            System.out.println("Houve um erro durante a consulta na api do github");
            e.getStackTrace();
        }

    }
}
