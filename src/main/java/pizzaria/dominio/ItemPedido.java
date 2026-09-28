package pizzaria.dominio;

// SRP (Single Responsibility Principle):
// Esta classe só representa uma linha do pedido:
// qual pizza e quantas unidades.
public class ItemPedido {

    private Pizza pizza;
    private int quantidade;

    public ItemPedido(Pizza pizza, int quantidade) {
        // Regra de integridade: não existe item com zero ou menos unidades.
        if (quantidade < 1) {
            throw new IllegalArgumentException("A quantidade deve ser pelo menos 1.");
        }
        this.pizza = pizza;
        this.quantidade = quantidade;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // Preço da pizza vezes a quantidade.
    public double getSubtotal() {
        return pizza.getPreco() * quantidade;
    }
}
