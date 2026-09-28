package pizzaria.regra;

import pizzaria.contrato.RegraDePreco;
import pizzaria.dominio.Pedido;

// OCP (Open/Closed Principle):
// A taxa de entrega é uma regra nova que entra no sistema criando
// uma classe nova, sem alterar a calculadora de preço.
// SRP: esta classe só sabe calcular a taxa de entrega.
public class TaxaDeEntrega implements RegraDePreco {

    private double valorPorKm;

    public TaxaDeEntrega(double valorPorKm) {
        this.valorPorKm = valorPorKm;
    }

    @Override
    public double aplicar(Pedido pedido, double valorAtual) {
        double distanciaKm = pedido.getCliente().getEndereco().getDistanciaKm();
        double taxa = distanciaKm * valorPorKm;
        return valorAtual + taxa;
    }

    @Override
    public String getDescricao() {
        return "Taxa de entrega";
    }
}
