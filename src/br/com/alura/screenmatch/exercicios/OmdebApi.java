package br.com.alura.screenmatch.exercicios;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class OmdebApi {

    public static String getFilmes(String endereco) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static TituloOmdb serializaJson(String json){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        return gson.fromJson(json, TituloOmdb.class);
    }

    public static void writeMovies(String arquivoPath, List<Titulo> meusTitulos) throws IOException {
        FileWriter escrita = new FileWriter(arquivoPath);
        escrita.write(meusTitulos.toString());
        escrita.close();
    }
}
