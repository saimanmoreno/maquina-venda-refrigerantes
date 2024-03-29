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

        if (moedas.size() == 0 || produtos.size() == 0)
            initialize(); // se não há dados utilizar o predefinido

        int opcao;

        do {
            System.out.print("\n\nCódigo\t\tOpção\n"
                    + "--------------------------------------------------------------\n"
                    + "[1]\t\tSelecionar lista de produtos\n"
                    + "[2]\t\tLogin como administrador (Comming Soon)\n"
                    + "[0]\t\tSAIR DA APLICACAO\n"
                    + "--------------------------------------------------------------\n"
                    + " >> ");
            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;

                case 2:
                    adminMenu();
                    break;

                case 0:
                    // exit
                    break;

                default:
                    System.out.println("\n\nOPCAO INVALIDA!!!");
            }
        } while (opcao != 0);

        System.out.println("\n\nADEUS E OBRIGADO! \n\n");

    }

    /**
     * Imprima a descrição, o preço e a quantidade de cada produto registado na tela
     */
    private static void listarProdutos() {

        int s_opcao;

        do {
            System.out.print("\n\nCódigo\t\tProduto\t\tPreço\t\tQtdd.\n"
                    + "--------------------------------------------------------------\n");

            for (Produto prod : produtos) {
                System.out.print("[" + (produtos.indexOf(prod) + 1) + "]");
                System.out.print("\t\t" + prod.getDescrição());
                System.out.print("\t\t" + prod.getPreço() + "$00");
                System.out.print("\t\t" + prod.getQtdade() + " unid.\n");
            }

            System.out.print("[0]\t\tVOLTAR\n"
                    + "--------------------------------------------------------------\n"
                    + " >> ");

            s_opcao = ler.nextInt();

            // se a opcao escolhida nao for zero e for um codigo existente e for multiplo de
            // 5
            if (s_opcao != 0 && s_opcao < (produtos.size() + 1))
                comprar(s_opcao);
            else
                System.out.println("\n\nOPCAO INVALIDA!!!");

        } while (s_opcao != 0);

    }

    /**
     * Processa a operação de compra
     * 
     * @param opcao
     */
    private static void comprar(int opcao) {

        ArrayList<Integer> moedasTroco = new ArrayList<>();

        Produto produto = new Produto(
                produtos.get(opcao - 1).getDescrição(),
                produtos.get(opcao - 1).getPreço(),
                produtos.get(opcao - 1).getQtdade());

        System.out.println("\n\nProduto escolhido => " + produto.getDescrição().toUpperCase());
        System.out.println("Preço => " + produto.getPreço() + "$00");

        System.out.print("\n\nPor favor, introduza as moedas no valor correspondente na maquina\n >> ");
        int valor = ler.nextInt();

        // se o valor introduzido nao for um multiplo de 5
        // if ([5,10,20,50,100].) {

        // }

        while (valor < produto.getPreço()) {
            System.out.println("\n\nFALTAM MAIS " + (produto.getPreço() - valor) + "$00");
            System.out.print("Por favor, introduza as moedas no valor correspondente restante na maquina\n >> ");
            valor += ler.nextInt();
        }

        System.out.println("\n\n ------ COMPRA EFETUADA COM SUCESSO! ------ \n");
        System.out.println("Total inserido: " + valor + "$00");

        if (valor > produto.getPreço()) {

            int diferenca = valor - produto.getPreço();

            System.out.println("Troco: " + diferenca + "$00");

            moedasTroco = processTroco(diferenca, produto.getPreço());

            System.out.print("Recolha as suas moedas de troco na maquina: ");

            for (Integer troco : moedasTroco) {
                System.out.print(troco + "$00\t");
            }
            
        }

        System.out.println("\n\n ------ Retire o seu produto! Obrigado. ------ \n\n");

        produto.setQtdade(produto.getQtdade() - 1);
        produtos.set(opcao - 1, produto);

        new Ficheiros().escreverFile(produtos, txtProdutos);
    }

    /**
     * Atualiza o total de moedas resultantes da devolução de troco
     * 
     * @param diferenca total de troco a devolver
     * @param preço     preço total do produto comprado
     */
    private static ArrayList<Integer> processTroco(int diferenca, int preço) {

        ArrayList<Integer> moedasTroco = new ArrayList<>();

        do {
            int value = 5; // dado que a moeda de menor valor é 5

            if (diferenca >= 100)
                value = 100;
            else if (diferenca >= 50)
                value = 50;
            else if (diferenca >= 20)
                value = 20;
            else if (diferenca >= 10)
                value = 10;

            // System.out.println("Diferenca == " + diferenca);
            // System.out.println("Valor == " + value);

            for (Moedas moeda : moedas)
                if (moeda.getValor() == value) {
                    moeda.setQtdade(moeda.getQtdade() - 1); // decrementar a quantidade da moeda dado como troco
                    moedas.set(moedas.indexOf(moeda), moeda); // atualizar a moeda na lista de moedas
                    diferenca -= value; // incrementar a diferenca de troco restante
                    moedasTroco.add(value); // guarda a lista de moedas devolvidas
                    break; // nao procurar mais moedas
                }

        } while (diferenca > 0);

        new Ficheiros().escreverFile(moedas, txtMoedas);

        return moedasTroco;
    }

    /**
     * Inicializar as listas de produtos e das moedas com os dados padrão
     */
    private static void initialize() {

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

    /**
     * Menu do administrador para inserção de novas moedas e produtos
     */
    private static void adminMenu() {
        System.out.println("\n\nBrevemente esta funcionalidade!\n");
    }

    /**
     * Apresenta o menu e faz a operação de inserção de moedas na maquina
     */
    private static void addMoedas() {

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

}
