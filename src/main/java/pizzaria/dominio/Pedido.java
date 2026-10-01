package pizzaria.dominio;

import java.util.ArrayList;

// SRP (Single Responsibility Principle):
// Esta classe só guarda os dados do pedido, soma o valor das pizzas
// e protege as próprias regras de estado (ex.: não mexer em pedido já pago).
// Ela não aplica taxas, não paga, não salva e não notifica:
// cada uma dessas tarefas está em outra classe.
public class Pedido {

    private String id;
    private Cliente cliente;
    private ArrayList<ItemPedido> itens;
    private boolean pago;

    public Pedido(String id, Cliente cliente) {
        // Todo pedido começa vazio e ainda não pago.
        this.id = id;
        this.cliente = cliente;
        this.itens = new ArrayList<ItemPedido>();
        this.pago = false;
    }

    // Único jeito de colocar um item no pedido.
    public void adicionarItem(ItemPedido item) {
        if (pago) {
            throw new IllegalStateException("Nao e possivel adicionar itens a um pedido ja pago.");
        }
        itens.add(item);
    }

    // Soma o subtotal de cada item, sem nenhuma taxa ou desconto.
    public double getSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = itens.get(i);
            subtotal = subtotal + item.getSubtotal();
        }
        return subtotal;
    }
    // Regra de integridade: um pedido não pode ser pago duas vezes.
    public void marcarComoPago() {
        if (pago) {
            throw new IllegalStateException("O pedido " + id + " ja foi pago.");
        }
        this.pago = true;
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    // Devolve uma CÓPIA da lista. Assim ninguém de fora consegue
    // adicionar ou remover itens sem passar pelo adicionarItem.
    public ArrayList<ItemPedido> getItens() {
        ArrayList<ItemPedido> copia = new ArrayList<ItemPedido>();
        for (int i = 0; i < itens.size(); i++) {
            copia.add(itens.get(i));
        }
        return copia;
    }

    public boolean isPago() {
        return pago;
    }
}
