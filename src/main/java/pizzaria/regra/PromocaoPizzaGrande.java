package pizzaria.regra;

import java.util.ArrayList;

import pizzaria.contrato.RegraDePreco;
import pizzaria.dominio.ItemPedido;
import pizzaria.dominio.Pedido;
import pizzaria.dominio.Tamanho;

// OCP (Open/Closed Principle):
// A promoção é uma regra nova adicionada com uma classe nova,
// sem alterar a calculadora de preço.
// SRP: esta classe só sabe aplicar a promoção da pizza grande.
public class PromocaoPizzaGrande implements RegraDePreco {

    private double valorDesconto;

    public PromocaoPizzaGrande(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    @Override
    public double aplicar(Pedido pedido, double valorAtual) {
        ArrayList<ItemPedido> itens = pedido.getItens();
        boolean temPizzaGrande = false;

        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = itens.get(i);
            // Este if verifica um DADO do pedido (o tamanho da pizza),
            // e não o TIPO de uma classe (como instanceof).
            // Por isso não viola o OCP: a regra de negócio é esta mesma.
            if (item.getPizza().getTamanho() == Tamanho.GRANDE) {
                temPizzaGrande = true;
            }
        }

        if (temPizzaGrande) {
            return valorAtual - valorDesconto;
        }
        return valorAtual;
    }

    @Override
    public String getDescricao() {
        return "Promocao pizza grande";
    }
}
