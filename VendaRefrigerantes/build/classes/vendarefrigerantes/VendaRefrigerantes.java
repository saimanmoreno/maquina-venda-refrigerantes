package vendarefrigerantes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * @author Simão Moreno
 * Curso: Engenharia Informatica e Computadores
 * Ano: 2º ano
 * Telefone: 9848806
 * Email: simaomoreno.unicv@gmail.com
 */
public class VendaRefrigerantes {

    public static void main(String[] args) throws IOException {
        
        //definir a localizacao dos ficheiros com as suas variaveis
            File txtAdmin = new File("Username.txt");
            File txtPassword = new File("Passwords.txt");
            File txtProdutos = new File("Produtos.txt");
            File txtPreço = new File("Preço.txt");
            File txtQtdade = new File("Qtdade.txt");
            File txtMoedas = new File("Moedas.txt");
        
        //declaração de listas
            ArrayList <String> admin = new ArrayList <>();
            ArrayList <String> password = new ArrayList <>();
            ArrayList <String> produtos = new ArrayList <>();
            ArrayList <String> preço = new ArrayList <>();
            ArrayList <String> qtdade = new ArrayList <>();
            ArrayList <String> moedas = new ArrayList <>();
        
        //declaração variaveis
            int op = 0, op1 = 0, op2 = 0, oport = 1;
        
        //carregar dados dos ficheiros
            carregarDados(admin, txtAdmin);
            carregarDados(password, txtPassword);
            carregarDados(produtos, txtProdutos);
            carregarDados(preço, txtPreço);
            carregarDados(qtdade, txtQtdade);
            carregarDados(moedas, txtMoedas);
            
            Scanner ler = new Scanner(System.in);
            
        //MENU PRINCIPAL 
        do {            
            for(int i=0; i<100;i++) System.out.println("");
            System.out.print(""
                    + "Código\t\tOpção\n"
                    + "======================================================\n"
                    + "(1)\t\tSelecionar Lista de Produtos\n"
                    + "(2)\t\tLogin Administrador\n\n"
                    + "(0)\t\tSair\n"
                    + "======================================================\n>>> ");
            op = ler.nextInt();
            switch (op) {
                
                
                case 1:
                    for(int i=0; i<100;i++) System.out.println("");
                    //SUB-MENU Produtos
                    do {
                        System.out.print(""
                                + "Código\t\tProduto\t\tPreço\t\tQuantidade\n"
                                + "======================================================\n"
                                + "(1)   \t\tCola   \t\t80$  \t\t" + qtdade.get(0) + "\n"
                                + "(2)   \t\tFanta  \t\t70$  \t\t" + qtdade.get(1) + "\n"
                                + "(3)   \t\tSprit  \t\t60$  \t\t" + qtdade.get(2) + "\n"
                                + "(4)   \t\tSumol  \t\t90$  \t\t" + qtdade.get(3) + "\n\n"
                                + "(0)   \t\tSair da aplicação\n"
                                + "======================================================\n>>> ");
                        op1 = ler.nextInt();

                        if(op1 == 1 || op1 == 2 || op1 == 3 || op1 == 4){
                            pedidos(op1, produtos, preço, qtdade, moedas);
                            //guardar dados nos ficheiros
                                gravarDados(produtos, txtProdutos);
                                gravarDados(qtdade, txtQtdade);
                                gravarDados(moedas, txtMoedas);
                        }else if(op1 ==0 ){
                            exit(1);
                        }else{
                            System.out.print("Selecione uma opção válida!\nDigite (1) para continuar!\n>> ");          
                            String msg = ler.next();
                            for (int i = 0; i < 100; i++)System.out.println("");
                        }
                    }while (op1!=0); 
                    break;
                    
                    
                    
                case 2:
                    //fazer login
                        login(admin, password);
                        
                    do {                 
                        //Sub-Menu Administrador
                        for (int i = 0; i < 100; i++) System.out.println("");
                        System.out.print(""
                                + "Código\t\tOpção\n"
                                + "======================================================\n"
                                + "(1)   \t\tColocar moedas na maquina\n"
                                + "(2)   \t\tAtualizar o Stock(Produto)\n\n"
                                + "(0)   \t\tVoltar ao Menu Principal\n"
                                + "======================================================\n>>> ");
                        op2 = ler.nextInt();
                        switch(op2){
                            case 1: 
                                addMoedas(moedas);
                                //guardar dados nos ficheiros
                                    gravarDados(produtos, txtProdutos);
                                    gravarDados(qtdade, txtQtdade);
                                    gravarDados(moedas, txtMoedas);
                                break;
                            case 2: 
                                atualizarStock(produtos, qtdade);
                                //guardar dados nos ficheiros
                                    gravarDados(produtos, txtProdutos);
                                    gravarDados(qtdade, txtQtdade);
                                    gravarDados(moedas, txtMoedas);
                                break;
                            case 0:
                                break;
                        }
                    } while (op2!=0);
                    break;
                    
                case 0:
                    break;
                    
                    
                default:
                    System.out.print("Selecione uma opção válida!\nDigite (1) para continuar!\n>> ");          
                    String msg = ler.next();
                    for (int i = 0; i < 100; i++)System.out.println("");
            }
        }while (op!=0);
        
        //guardar dados nos ficheiros
            gravarDados(produtos, txtProdutos);
            gravarDados(qtdade, txtQtdade);
            gravarDados(moedas, txtMoedas);
    }

    //Login
    public static boolean login(ArrayList admin, ArrayList password) throws IOException{
        
        Scanner ler = new Scanner(System.in);
        boolean entrar = false;
        String userName, userPassword, teste;
        int oport = 0;
        
        while(true) {      
            oport = oport+1;
            for (int i = 0; i < 100; i++)System.out.println("");
            System.out.print("Nome >> "); userName = ler.next();
            System.out.print("Senha>> "); userPassword = ler.next();
            
            if (admin.contains(userName) && password.contains(userPassword)) {
                
                System.out.print("Bem Vindo ao Sistema! Digite (1)\n>> ");
                teste = ler.next();
                entrar = true;
                break;
                
            } else {
                if(oport==3){
                    System.out.print("Numero de Tentativas Excedidos! Digite (1) para sair do programa\n>> ");
                    teste = ler.next();
                    exit(1);
                }
                System.out.print("Username ou Password incorreta! Digite (1) para tentar novamente!\n>> ");
                teste = ler.next();
            }
        } 
        
        return entrar;
    }

            
    //fazer pedidos
    private static void pedidos(int op1, ArrayList<String> produtos, 
            ArrayList<String> preço, ArrayList<String> qtdade, ArrayList<String> moedas) {
        
        Scanner ler = new Scanner (System.in);
        int valor, i=(op1-1), j;
        System.out.print(""
                + "Produto escolhido: "+produtos.get(i)+"\n"
                + "Total a pagar: "+preço.get(i)+"\n"
                + "Introduza o valor a pagar >> "); valor = ler.nextInt();
        
        do {            
            if (valor < Integer.parseInt(preço.get(i))) {
                j = Integer.parseInt(preço.get(i)) - valor;
                System.out.print("Quantidade Insuficiente!Total a pagar: "+preço.get(i)+"\n"
                        + "Falta mais " + j + "$00! "
                        + "Por favor introduza a quantia que falta\n>> ");
                int k = ler.nextInt();
                valor = valor + k;
            }
        }while (valor<Integer.parseInt(preço.get(i)));
        
        if(valor >= Integer.parseInt(preço.get(i))){
            int k = Integer.parseInt(qtdade.get(i));
            k = k-1;
            String msg = Integer.toString(k);
            qtdade.set(i, msg);
            
            troco(valor, preço.get(i), moedas);
                   
            int teste = ler.nextInt();
            for(int a=0; a<100; a++) System.out.println("");
            System.out.print("Produto: "+produtos.get(i)+"\n"
                    + "Preço: "+preço.get(i)+"\n"
                    + "Valor Pago: "+valor+"\n"
                    + "Troco: "+troco(valor, preço.get(i), moedas)+"\nDigite (1) para continuar >> ");
            teste = ler.nextInt();
            for(int a=0; a<100; a++) System.out.println("");
            
        }
    }
    
    private static int troco(int valor, String preço, ArrayList<String> moedas) {
        
        int troco = valor - Integer.parseInt(preço);
        
        while (troco>0) {            
            if (troco > 100) {
                moedas.remove("100");
                System.out.println("Retire o seu troco: ");
                System.out.println("100$00");
                troco = troco - 100;
            } else if (troco > 50) {
                moedas.remove("50");
                System.out.println("Retire o seu troco: ");
                System.out.println("50$00");
                troco = troco - 50;
            } else if (troco > 20) {
                moedas.remove("20");
                System.out.println("Retire o seu troco: ");
                System.out.println("20$00");
                troco = troco - 20;
            } else if (troco > 10) {
                moedas.remove("10");
                System.out.println("Retire o seu troco: ");
                System.out.println("10$00");
                troco = troco - 10;
            } else if (troco > 5) {
                moedas.remove("5");
                System.out.println("5$00");
                troco = troco - 5;
            }
        }
        return troco;    
    }

    //adicionar moedas
    private static void addMoedas(ArrayList<String> moedas) {
        
        Scanner ler = new Scanner(System.in);
        for(int i=0; i<100; i++) System.out.println("");
        System.out.print("Qtas moedas introduzir? >> "); int qt = ler.nextInt();
        for(int i=0; i<qt; i++){
            moedas.add("5");
            moedas.add("10");
            moedas.add("20");
            moedas.add("50");
            moedas.add("100");
        }
        System.out.print("Adicionado "+qt+" novas moedas de 5$, 10$, 20$, 50$ e 100$. Digite (1) para voltar ao menu\n>> ");
        String teste = ler.next();
    }

    //atualizar stock
    private static void atualizarStock(ArrayList<String> produtos, ArrayList<String> qtdade) {
        
        Scanner ler = new Scanner(System.in);
        int op, i, j, k, l;
        String msg, dados;
        
        for(i=0; i<100; i++) System.out.println("");
        
        while (true) {            
            System.out.print(""
                    + "Código\t\tProduto\t\tPreço\t\tQuantidade\n"
                    + "======================================================\n"
                    + "(1)   \t\tCola   \t\t80$  \t\t" + qtdade.get(0) + "\n"
                    + "(2)   \t\tFanta  \t\t70$  \t\t" + qtdade.get(1) + "\n"
                    + "(3)   \t\tSprit  \t\t60$  \t\t" + qtdade.get(2) + "\n"
                    + "(4)   \t\tSumol  \t\t90$  \t\t" + qtdade.get(3) + "\n\n"
                    + "(0)   \t\tSair da aplicação\n"
                    + "======================================================\n>>> ");
            op = ler.nextInt();
            
            if (op == 1 || op == 2 || op == 3 || op == 4) {
                
                System.out.print("Introduza o qtdade >> ");
                msg = ler.next();
                k = op - 1;
                i = Integer.parseInt(msg);
                j = Integer.parseInt(qtdade.get(k));
                dados = Integer.toString(i + j);
                qtdade.set(k, dados);
                System.out.print("Adicionado mais " + msg + " quantidades de " + produtos.get(k) + "\n"
                        + "Quantidade Total: " + qtdade.get(k)+"\nDigite (1) para continuar!\n>> ");msg = ler.next();
                
                for (i = 0; i < 100; i++)System.out.println("");
                
                break;
            } else if (op == 0) {
                break;
            } else {
                System.out.print("Selecione uma opção válida!\nDigite (1) para continuar!\n>> ");          
                msg = ler.next();
                for (i = 0; i < 100; i++)System.out.println("");
            }
        }
    }
    
    //carregar dados
    public static void carregarDados(ArrayList Colecao, File file) throws FileNotFoundException, IOException{
        
        FileReader lerFile = new FileReader(file);
        BufferedReader buf = new BufferedReader(lerFile);
        try{
            String frase;
            while((frase = buf.readLine()) != null) Colecao.add(frase);
            buf.close();
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro na leitura do ficheiro!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //Gravar Dados
    public static void gravarDados(ArrayList Colecao, File file) throws IOException{
        
        FileWriter arq = new FileWriter(file);
        BufferedWriter escreverFile = new BufferedWriter(arq);
        try{
            for (int i = 0; i < Colecao.size(); i++) {
                String msg = (String) Colecao.get(i);
                escreverFile.write(msg+"\n");
            }
            escreverFile.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro na escrita do ficheiro!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
