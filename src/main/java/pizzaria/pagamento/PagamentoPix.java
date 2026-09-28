package pizzaria.pagamento;

import pizzaria.contrato.MeioDePagamento;

// LSP (Liskov Substitution Principle):
// Pode ser usado em qualquer lugar que espera um MeioDePagamento,
// e cumpre o contrato de verdade (paga e informa o nome).
// OCP: um novo meio de pagamento é só uma classe nova.
public class PagamentoPix implements MeioDePagamento {

    @Override
    public void pagar(double valor) {
        String valorFormatado = String.format("%.2f", valor);
        System.out.println("[PIX] Pagamento de R$ " + valorFormatado + " aprovado via chave Pix.");
    }

    @Override
    public String getNome() {
        return "Pix";
    }
}
