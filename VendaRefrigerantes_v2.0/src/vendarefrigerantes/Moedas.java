package vendarefrigerantes;

/**
 *
 * @author pc
 */
public class Moedas {
    
    private int valor;
    private int qtdade;

    @Override
    public String toString() {
        return valor + " | " + qtdade;
    }

    public Moedas(int valor, int qtdade) {
        this.valor = valor;
        this.qtdade = qtdade;
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


