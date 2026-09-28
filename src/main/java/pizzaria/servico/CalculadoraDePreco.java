package pizzaria.servico;

import java.util.ArrayList;

import pizzaria.contrato.CalculoDePreco;
import pizzaria.contrato.RegraDePreco;
import pizzaria.dominio.Pedido;

// OCP (Open/Closed Principle):
// Esta classe está aberta para extensão e fechada para modificação.
// Para adicionar uma nova regra de preço basta criar uma classe nova
// que implementa RegraDePreco e registrá-la na lista.
// Nenhuma linha desta classe precisa mudar.
// Não existe nenhum if, switch ou instanceof aqui.
public class CalculadoraDePreco implements CalculoDePreco {

    private ArrayList<RegraDePreco> regras;

    // A lista vem de fora (quem monta é o Main).
    public CalculadoraDePreco(ArrayList<RegraDePreco> regras) {
        this.regras = regras;
    }

    @Override
    public double calcular(Pedido pedido) {
        double total = pedido.getSubtotal();
        System.out.println("Subtotal das pizzas: R$ " + String.format("%.2f", total));

        for (int i = 0; i < regras.size(); i++) {
            RegraDePreco regra = regras.get(i);
            double antes = total;

            // Cada regra recebe o total atual e devolve o novo total.
            total = regra.aplicar(pedido, total);

            // Mostra o valor antes e depois: se a regra não se aplicar ao
            // pedido, os dois valores ficam iguais.
            String antesFormatado = String.format("%.2f", antes);
            String depoisFormatado = String.format("%.2f", total);
            System.out.println("Regra avaliada: " + regra.getDescricao() + " | R$ " + antesFormatado + " -> R$ " + depoisFormatado);
        }

        return total;
    }
}
