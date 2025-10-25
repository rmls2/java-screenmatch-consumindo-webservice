package br.com.alura.screenmatch.exercicios;
import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscaFilmes {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();

        while (!busca.equalsIgnoreCase("sair")) {

            System.out.println("Digite um filme para busca: ");
            busca = leitura.nextLine();

            if(busca.equalsIgnoreCase("sair")){break;}

            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=2c2d7a56";
            System.out.println(endereco);
            try {
                String filme = OmdebApi.getFilmes(endereco);
                TituloOmdb meuTituloOmdb = OmdebApi.serializaJson(filme);
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
        try {
            OmdebApi.writeMovies("filmes.json", titulos);
        } catch (IOException e){
            System.out.println("erro na escrita dos filmes no arquivo json");
            e.printStackTrace();
        }


        System.out.println("O programa finalizou corretamente!");
    }
}
