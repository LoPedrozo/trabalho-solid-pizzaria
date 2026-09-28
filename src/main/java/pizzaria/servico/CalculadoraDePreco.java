package pizzaria.servico;

import java.util.ArrayList;

import pizzaria.contrato.RegraDePreco;
import pizzaria.dominio.Pedido;

// OCP (Open/Closed Principle):
// Esta classe está aberta para extensão e fechada para modificação.
// Para adicionar uma nova regra de preço basta criar uma classe nova
// que implementa RegraDePreco e registrá-la na lista.
// Nenhuma linha desta classe precisa mudar.
// Não existe nenhum if, switch ou instanceof aqui.
public class CalculadoraDePreco {

    private ArrayList<RegraDePreco> regras;

    // A lista vem de fora (quem monta é o Main).
    public CalculadoraDePreco(ArrayList<RegraDePreco> regras) {
        this.regras = regras;
    }

    public double calcular(Pedido pedido) {
        double total = pedido.getSubtotal();

        for (int i = 0; i < regras.size(); i++) {
            RegraDePreco regra = regras.get(i);
            // Cada regra recebe o total atual e devolve o novo total.
            total = regra.aplicar(pedido, total);

            String totalFormatado = String.format("%.2f", total);
            System.out.println("Regra aplicada: " + regra.getDescricao() + " -> total parcial R$ " + totalFormatado);
        }

        return total;
    }
}
