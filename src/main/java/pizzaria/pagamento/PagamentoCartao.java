package pizzaria.pagamento;

import pizzaria.contrato.MeioDePagamento;

// LSP (Liskov Substitution Principle):
// Pode ser usado em qualquer lugar que espera um MeioDePagamento,
// e cumpre o contrato de verdade (paga e informa o nome).
// OCP: um novo meio de pagamento é só uma classe nova.
public class PagamentoCartao implements MeioDePagamento {

    private String bandeira;

    public PagamentoCartao(String bandeira) {
        this.bandeira = bandeira;
    }

    @Override
    public void pagar(double valor) {
        String valorFormatado = String.format("%.2f", valor);
        System.out.println("[CARTAO] Pagamento de R$ " + valorFormatado + " aprovado no cartao " + bandeira + ".");
    }

    @Override
    public String getNome() {
        return "Cartao";
    }
}
