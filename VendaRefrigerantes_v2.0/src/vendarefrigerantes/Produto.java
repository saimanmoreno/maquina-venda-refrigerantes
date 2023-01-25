package vendarefrigerantes;

/**
 *
 * @author thesimmons
 */
public class Produto {

    private String descrição;
    private int preço;
    private int qtdade;

    // constructor
    public Produto(String descrição, int preço, int qtdade) {
        this.descrição = descrição;
        this.preço = preço;
        this.qtdade = qtdade;
    }

    @Override
    public String toString() {
        return descrição + "," + preço + "," + qtdade;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public int getPreço() {
        return preço;
    }

    public void setPreço(int preço) {
        this.preço = preço;
    }

    public int getQtdade() {
        return qtdade;
    }

    public void setQtdade(int qtdade) {
        this.qtdade = qtdade;
    }
}