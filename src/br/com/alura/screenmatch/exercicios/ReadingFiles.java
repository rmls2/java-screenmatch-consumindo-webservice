package br.com.alura.screenmatch.exercicios;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;



public class ReadingFiles {
    public static void readingFiles() throws IOException {
        File meuTexto = new File("/home/robert-miller/repositorios/java-screenmatch-consumindo-webservice/src/br/com/alura/screenmatch/exercicios/teste.txt");
        FileReader reader = new FileReader(meuTexto);
        //lendo o arquivo
        int data = reader.read();
        while (data != -1){
            System.out.print((char) data);
            data = reader.read();
        }
        reader.close();

        //escrevendo no arquivo
        FileWriter writer =new FileWriter(meuTexto);
        writer.write("casa mal assombrada!");
        writer.write("\ncasa acessa!");

        writer.close();
    }
    public static void main(String[] args) {
       try {
           readingFiles();
       } catch (IOException e){
           System.out.println(e.getMessage());
           e.getStackTrace();
       }
    }
}
