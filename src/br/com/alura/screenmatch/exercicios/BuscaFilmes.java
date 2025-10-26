package br.com.alura.screenmatch.exercicios;
import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscaFilmes {
    public static void main(String[] args) throws IOException {
        Scanner leitura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = OmdebApi.gsonBuilder();
        String busca = "";

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite um filme para busca: ");
            busca = leitura.nextLine();
            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=2c2d7a56";
            if(busca.equalsIgnoreCase("sair")) break;

            try {
                System.out.println(endereco);
                String json = OmdebApi.getFilmes(endereco);
                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                titulos.add(meuTitulo);
            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro: ");
                System.out.println(e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("Algum erro de argumento na busca, verifique o endereço");

            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());

            }catch (IOException | InterruptedException e){
                System.out.println("Erro na busca dos titulos na API " + e.getMessage());
                e.printStackTrace();
            }
        }
        OmdebApi.writeMovies("filmes.json", titulos, gson);
        System.out.println("O programa finalizou corretamente!");
    }
}
