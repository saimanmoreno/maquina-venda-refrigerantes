package vendarefrigerantes;

/**
 *
 * @author thesimmons
 */
public class Moedas {

    private int valor;
    private int qtdade;

    // constructor
    public Moedas(int valor, int qtdade) {
        this.valor = valor;
        this.qtdade = qtdade;
    }

    @Override
    public String toString() {
        return valor + "," + qtdade;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getQtdade() {
        return qtdade;
    }

    public void setQtdade(int qtdade) {
        this.qtdade = qtdade;
    }
}