package vendarefrigerantes;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Ficheiros {

    /**
     *
     * @param file
     * @return
     */
    public ArrayList<Produto> lerProdutos(File file) {

        ArrayList<Produto> produtos = new ArrayList<>();
        FileReader lerFile;

        try {

            lerFile = new FileReader(file);

            try (BufferedReader buf = new BufferedReader(lerFile)) {

                String frase;

                while ((frase = buf.readLine()) != null) {

                    String[] token = frase.split(",");

                    Produto produto = new Produto(token[0], Integer.parseInt(token[1]), Integer.parseInt(token[2]));
                    produtos.add(produto);

                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro na leitura do ficheiro: " + e.getMessage(), "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
        return produtos;
    }

    public ArrayList<Moedas> lerMoedas(File file) {

        ArrayList<Moedas> moedas = new ArrayList<>();
        FileReader lerFile;

        try {

            lerFile = new FileReader(file);

            try (BufferedReader buf = new BufferedReader(lerFile)) {

                String frase;

                while ((frase = buf.readLine()) != null) {

                    String[] token = frase.split(",");

                    Moedas moeda = new Moedas(Integer.parseInt(token[0]), Integer.parseInt(token[1]));
                    moedas.add(moeda);
                }
            }
        } catch (IOException e) {

            System.out.println("Erro na leitura do ficheiro: " + e.getMessage());
        }
        return moedas;
    }

    public void escreverFile(ArrayList lista, File file) {

        FileWriter arq;

        try {

            arq = new FileWriter(file);

            try (BufferedWriter escreverFile = new BufferedWriter(arq)) {

                for (Object produto : lista)
                    escreverFile.write(produto.toString() + "\n");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro na escrita do ficheiro: " + ex.getMessage(), "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}