package br.com.alura.screenmatch.exercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadingWithScann {
    public static void main(String[] args) {
        try {
            File arquivo = new File("/home/robert-miller/repositorios/java-screenmatch-consumindo-webservice/src/br/com/alura/screenmatch/exercicios/arquivo.json");
            Scanner scanner = new Scanner(arquivo);

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                System.out.println(linha);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    }
}
