package pizzaria.servico;

import java.util.ArrayList;

import pizzaria.contrato.CalculoDePreco;
import pizzaria.contrato.CanalDeNotificacao;
import pizzaria.contrato.MeioDePagamento;
import pizzaria.contrato.RepositorioDePedidos;
import pizzaria.dominio.Endereco;
import pizzaria.dominio.ItemPedido;
import pizzaria.dominio.Pedido;

// DIP (Dependency Inversion Principle):
// Esta é a classe de alto nível e ela só conhece abstrações (interfaces):
// CalculoDePreco, MeioDePagamento, CanalDeNotificacao e RepositorioDePedidos.
// Ela não sabe se o pagamento é Pix ou cartão,
// nem se a notificação é WhatsApp ou e-mail.
// Quem decide isso é o Main, através da injeção pelo construtor.
// Por isso não existe nenhum "new" dentro desta classe.
public class ServicoDePedido {

    private CalculoDePreco calculadora;
    private MeioDePagamento pagamento;
    private CanalDeNotificacao notificacao;
    private RepositorioDePedidos repositorio;

    public ServicoDePedido(CalculoDePreco calculadora,
                           MeioDePagamento pagamento,
                           CanalDeNotificacao notificacao,
                           RepositorioDePedidos repositorio) {
        this.calculadora = calculadora;
        this.pagamento = pagamento;
        this.notificacao = notificacao;
        this.repositorio = repositorio;
    }

    public void finalizarPedido(Pedido pedido) {
        // 1. Cabeçalho com os dados do pedido
        System.out.println("===== Pedido " + pedido.getId() + " - Cliente: " + pedido.getCliente().getNome() + " =====");

        Endereco endereco = pedido.getCliente().getEndereco();
        System.out.println("Entrega: " + endereco.getRua() + " - " + endereco.getBairro());

        ArrayList<ItemPedido> itens = pedido.getItens();
        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = itens.get(i);
            System.out.println("  " + item.getQuantidade() + "x " + item.getPizza().getSabor()
                    + " (" + item.getPizza().getTamanho() + ") - R$ " + String.format("%.2f", item.getSubtotal()));
        }

        // 2. Calcula o total aplicando as regras de preço
        double total = calculadora.calcular(pedido);

        // 3. Paga (não sabemos qual meio, só que é um MeioDePagamento)
        System.out.println("Forma de pagamento: " + pagamento.getNome());
        pagamento.pagar(total);

        // 4. Marca o pedido como pago
        pedido.marcarComoPago();

        // 5. Salva o pedido
        repositorio.salvar(pedido);

        // 6. Monta a mensagem de confirmação
        String totalFormatado = String.format("%.2f", total);
        String mensagem = "Seu pedido " + pedido.getId() + " foi confirmado! Valor total: R$ " + totalFormatado;

        // 7. Notifica o cliente (não sabemos qual canal, só que é um CanalDeNotificacao)
        notificacao.enviar(pedido.getCliente(), mensagem);

        // 8. Total final
        System.out.println("Total final: R$ " + totalFormatado);
    }
}
