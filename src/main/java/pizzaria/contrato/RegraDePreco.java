package pizzaria.contrato;

import pizzaria.dominio.Pedido;

// ISP (Interface Segregation Principle):
// Esta interface tem poucos métodos de propósito.
// Quem cria uma regra de preço só precisa saber aplicar a regra
// e dar uma descrição. Nada de pagamento, notificação ou banco de dados.
public interface RegraDePreco {
    // Recebe o valor atual do pedido e devolve o novo valor,
    // já com a regra aplicada (somando taxa ou tirando desconto).
    double aplicar(Pedido pedido, double valorAtual);

    String getDescricao();
}
