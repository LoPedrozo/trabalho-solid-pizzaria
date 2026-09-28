package pizzaria.dominio;

import java.util.ArrayList;

// SRP (Single Responsibility Principle):
// Esta classe só guarda os dados do pedido e soma o valor das pizzas.
// Ela não aplica taxas, não paga, não salva e não notifica:
// cada uma dessas tarefas está em outra classe.
public class Pedido {

    private String id;
    private Cliente cliente;
    private ArrayList<ItemPedido> itens;
    private boolean pago;

    public Pedido(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.itens = new ArrayList<ItemPedido>();
        this.pago = false;
    }

    public void adicionarItem(ItemPedido item) {
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

    public void marcarComoPago() {
        this.pago = true;
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public boolean isPago() {
        return pago;
    }
}
