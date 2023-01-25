package vendarefrigerantes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Simão Moreno
 *         Curso: Engenharia Informatica e Computadores
 *         Ano: 2º ano
 *         Telefone: 9848806
 *         Email: simaomoreno.unicv@gmail.com
 */
public class Main {

    public static Scanner ler = new Scanner(System.in);

    // declaração de listas
    public static ArrayList<Produto> produtos = new ArrayList<>();
    public static ArrayList<Moedas> moedas = new ArrayList<>();

    // definir a localizacao dos ficheiros com as suas variaveis
    public static File txtProdutos = new File("produtos.txt");
    public static File txtMoedas = new File("moedas.txt");

    public static void main(String[] args) throws IOException {

        // carregar as informacoes sobre as listas de moedas e produtos dados no
        // ficheiro
        moedas = new Ficheiros().lerMoedas(txtMoedas);
        produtos = new Ficheiros().lerProdutos(txtProdutos);

        // initializeProdCoins(); // carregar as informações sobre qtdd de moedas e
        // preço dos produtos

        System.out.print("Código\t\tOpção\n"
                + "--------------------------------------------------------------\n"
                + "[1]\t\tSelecionar lista de produtos\n"
                + "[2]\t\tLogin como administrador\n"
                + "[0]\t\tSAIR DA APLICACAO\n"
                + "--------------------------------------------------------------\n"
                + " >> ");
        int opcao = ler.nextInt();

        switch (opcao) {
            case 1:
                listarProdutos();
                break;

            case 2:
                menuAdministrador();
                break;

            case 0:
                System.out.println("Adeus e obrigado! ");
                System.exit(0);
                break;

            default:
        }

        new Ficheiros().escreverFile(moedas, txtMoedas); // gravar dados no ficheiro
        new Ficheiros().escreverFile(produtos, txtProdutos); // gravar dados no ficheiro
    }

    // adicionar moedas
    private static void addMoedas(ArrayList<Moedas> moedas) {

        boolean valido = true;

        int qtdade = 0;
        int valor = 0;
        do {

            System.out.println("==========Adicionar Moedas============= ");
            System.out.println("Selecione o valor da moeda a introduzir: ");
            System.out.println("(5)       5 escudos");
            System.out.println("(10)     10 escudos");
            System.out.println("(20)     20 escudos");
            System.out.println("(50)     50 escudos");
            System.out.println("(100)   100 escudos");
            valor = ler.nextInt();

            System.out.println("==========Adicionar Moedas============= ");
            System.out.print("Introduza a quantidade a introduzir >> ");
            qtdade = ler.nextInt();

            if (valor == 5 || valor == 10 || valor == 20 || valor == 50 || valor == 100) {
                moedas.add(new Moedas(valor, qtdade));
            } else {
                System.out.println("Valor inválido! Escolha uma moeda.");
                System.out.println("Press any key and press enter... ");
                ler.next();
                valido = false;
            }
        } while (!valido);

        System.out
                .print("Adicionado " + qtdade + " novas moedas de " + valor + ". Digite (1) para voltar ao menu\n>> ");
        ler.next();
    }

    // menu produtos
    private static void listarProdutos() {

        int i = 0;

        System.out.print("\n\nCódigo\t\tProduto\t\tPreço\t\tQuantidade\n"
                + "--------------------------------------------------------------\n");

        for (Produto prod : produtos) {
            System.out.print("[" + ++i + "]");
            System.out.print("\t\t" + prod.getDescrição());
            System.out.print("\t\t" + prod.getPreço() + "$00");
            System.out.print("\t\t" + prod.getQtdade() + "\n");
        }

        System.out.print("[0]\t\tSAIR DA APLICACAO\n"
                + "--------------------------------------------------------------\n"
                + " >> ");

        int opcao = ler.nextInt();

        if (opcao != 0) {
            comprar(opcao);
        } else {
            System.out.println("Adeus e obrigado! ");
            System.exit(0);
        }
    }

    // menu administrador
    private static void menuAdministrador() {

    }

    private static void comprar(int opcao) {

        Produto produto = new Produto(
                produtos.get(opcao - 1).getDescrição(),
                produtos.get(opcao - 1).getPreço(),
                produtos.get(opcao - 1).getQtdade());

        System.out.println("Produto escolhido: " + produto.getDescrição() + "\nPreço: " + produto.getPreço() + "$00");

        System.out.print("Por favor, Introduza a moeda no valor correspondente na maquina\n >> ");
        int valor = ler.nextInt();

        while (valor < produto.getPreço()) {
            System.out.print("Falta mais " + (produto.getPreço() - valor) + "$00"
                    + "Por favor, Introduza a moeda no valor correspondente na maquina\n>");
            valor += ler.nextInt();
        }

        if (valor == produto.getPreço()) {
            System.out.println("Total inserido: " + valor + "\n");
        }

        else if (valor > produto.getPreço()) {
            int troco = valor - produto.getPreço();
            System.out.println("Total inserido: " + valor + "\n"
                    + "Troco: " + troco + "$00");
            darTroco(troco, produto.getPreço());
        }

        System.out.println("Retire o seu produto. Obrigado");

        produto.setQtdade(produto.getQtdade() - 1);
        produtos.set(opcao - 1, produto);

        new Ficheiros().escreverFile(produtos, txtProdutos);
    }

    private static void darTroco(int troco, int preço) {

        do {
            if (troco >= 100) {
                for (Moedas moeda : moedas) {
                    if (moeda.getValor() == 100) {
                        moeda.setQtdade(moeda.getQtdade() - 1);
                        int index = moedas.indexOf(moeda);
                        moedas.set(index, moeda);
                        troco -= 100;
                    }
                }
            } else if (troco >= 50) {
                for (Moedas moeda : moedas) {
                    if (moeda.getValor() == 50) {
                        moeda.setQtdade(moeda.getQtdade() - 1);
                        int index = moedas.indexOf(moeda);
                        moedas.set(index, moeda);
                        troco -= 50;
                    }
                }
            } else if (troco >= 20) {
                for (Moedas moeda : moedas) {
                    if (moeda.getValor() == 20) {
                        moeda.setQtdade(moeda.getQtdade() - 1);
                        int index = moedas.indexOf(moeda);
                        moedas.set(index, moeda);
                        troco -= 20;
                    }
                }
            } else if (troco >= 10) {
                for (Moedas moeda : moedas) {
                    if (moeda.getValor() == 10) {
                        moeda.setQtdade(moeda.getQtdade() - 1);
                        int index = moedas.indexOf(moeda);
                        moedas.set(index, moeda);
                        troco -= 10;
                    }
                }
            } else {
                for (Moedas moeda : moedas) {
                    if (moeda.getValor() == 5) {
                        moeda.setQtdade(moeda.getQtdade() - 1);
                        int index = moedas.indexOf(moeda);
                        moedas.set(index, moeda);
                        troco -= 5;
                    }
                }
            }
        } while (troco > preço);
    }

    // função para inicializar dados dos produtos e das moedas por padrão
    private static void initializeProdCoins() {

        // carregar dados dos ficheiros
        produtos.add(new Produto("Cola", 80, 9));
        produtos.add(new Produto("Fanta", 70, 7));
        produtos.add(new Produto("Sprit", 60, 8));
        produtos.add(new Produto("Sumol", 80, 6));

        moedas.add(new Moedas(5, 10));
        moedas.add(new Moedas(10, 10));
        moedas.add(new Moedas(20, 10));
        moedas.add(new Moedas(50, 10));
        moedas.add(new Moedas(100, 10));

    }
}
