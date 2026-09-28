package pizzaria.dominio;

// SRP (Single Responsibility Principle):
// Esta classe só guarda os dados de uma pizza do cardápio:
// sabor, tamanho e preço.
public class Pizza {

    private String sabor;
    private Tamanho tamanho;
    private double preco;

    public Pizza(String sabor, Tamanho tamanho, double preco) {
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.preco = preco;
    }

    public String getSabor() {
        return sabor;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public double getPreco() {
        return preco;
    }
}
