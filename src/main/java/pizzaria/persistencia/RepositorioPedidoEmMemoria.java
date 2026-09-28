package pizzaria.persistencia;

import java.util.ArrayList;

import pizzaria.contrato.RepositorioDePedidos;
import pizzaria.dominio.Pedido;

// DIP (Dependency Inversion Principle):
// O serviço depende da interface RepositorioDePedidos, e não desta classe.
// Assim dá para trocar a memória por um banco de dados no futuro
// sem alterar o serviço.
public class RepositorioPedidoEmMemoria implements RepositorioDePedidos {

    private ArrayList<Pedido> pedidos;

    public RepositorioPedidoEmMemoria() {
        this.pedidos = new ArrayList<Pedido>();
    }

    @Override
    public void salvar(Pedido pedido) {
        pedidos.add(pedido);
        System.out.println("[REPOSITORIO] Pedido " + pedido.getId() + " salvo.");
    }

    // Retorna o pedido com esse id, ou null se ele não existir.
    @Override
    public Pedido buscarPorId(String id) {
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            if (pedido.getId().equals(id)) {
                return pedido;
            }
        }
        return null;
    }
}
