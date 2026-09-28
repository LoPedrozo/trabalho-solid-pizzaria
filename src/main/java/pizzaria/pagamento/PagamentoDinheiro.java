package pizzaria.pagamento;

import pizzaria.contrato.MeioDePagamento;

// LSP (Liskov Substitution Principle):
// Pode ser usado em qualquer lugar que espera um MeioDePagamento,
// e cumpre o contrato de verdade (paga e informa o nome).
// SRP: esta classe só sabe registrar o pagamento em dinheiro na entrega.
public class PagamentoDinheiro implements MeioDePagamento {

    @Override
    public void pagar(double valor) {
        String valorFormatado = String.format("%.2f", valor);
        System.out.println("[DINHEIRO] Pagamento de R$ " + valorFormatado + " recebido na entrega.");
    }

    @Override
    public String getNome() {
        return "Dinheiro";
    }
}
