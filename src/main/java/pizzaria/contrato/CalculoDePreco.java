package pizzaria.contrato;

import pizzaria.dominio.Pedido;

// ISP (Interface Segregation Principle):
// Esta interface tem um único método de propósito: calcular o total do pedido.
// DIP: é por causa dela que o ServicoDePedido não depende da classe
// concreta CalculadoraDePreco, e sim de uma abstração.
public interface CalculoDePreco {

    double calcular(Pedido pedido);
}
