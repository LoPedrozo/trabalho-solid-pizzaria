package pizzaria.contrato;

import pizzaria.dominio.Pedido;

// ISP (Interface Segregation Principle):
// Esta interface tem poucos métodos de propósito.
// O sistema só precisa salvar um pedido e buscar um pedido pelo id,
// então o contrato tem apenas esses dois métodos.
public interface RepositorioDePedidos {

    void salvar(Pedido pedido);

    Pedido buscarPorId(String id);
}
